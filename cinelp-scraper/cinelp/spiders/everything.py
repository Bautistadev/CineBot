#Primero hacer cd al directorio cinelp-scraper
#Ejecutar con:
#scrapy crawl everything -o (nombre archivo).json
import scrapy
from scrapy_selenium import SeleniumRequest
import time
import re
from selenium.webdriver.common.by import By

class EverythingSpider(scrapy.Spider):
    name = "everything"
    allowed_domains = ["www.cinemalaplata.com"]

    def start_requests(self):
        url = "https://www.cinemalaplata.com/#cartelera"
        yield SeleniumRequest(url=url, callback=self.parse)

    def parse(self, response):
        time.sleep(2) #Me tomo dos segundos de chill
        driver = response.request.meta["driver"]
        movie_ids = []
        for movie in driver.find_elements(By.CSS_SELECTOR, "a.prd-img.image-container"):
            link = movie.get_attribute("href")
            movie_id = re.sub(r'[^\d]+', '', link)
            if(movie_id in movie_ids):
                continue    #Pueden repetirse peliculas en la cartelera
            else:
                movie_ids.append(movie_id)
        
        for movie_id in movie_ids:
            movie_url = "https://www.cinemalaplata.com/Titulos/Index/" + movie_id
            driver.get(movie_url)
            time.sleep(2) #Me tomo dos segundos de chill
            title = driver.find_element(By.CSS_SELECTOR, "div.prd-block_price--actual").text
            image_link = driver.find_element(By.CSS_SELECTOR, "img.js-prd-img").get_attribute("src")
            length_minutes = driver.find_element(By.CSS_SELECTOR, ".prd-block_links li:nth-child(2)").text
            genre = driver.find_element(By.CSS_SELECTOR, "div.two-column :nth-child(4) span").text
            director = driver.find_element(By.CSS_SELECTOR, "div.two-column :nth-child(5) span").text
            cast = driver.find_element(By.CSS_SELECTOR, "div.two-column :nth-child(6) span").text
            
            for day_button in driver.find_elements(By.CSS_SELECTOR, "button.btn_dia"):
                day_button.click()
                time.sleep(2) #Me tomo dos segundos de chill
                date = day_button.text
                for hour_button in driver.find_elements(By.CSS_SELECTOR, 'div.pn_horarios[style=""]'):
                    booking_id = hour_button.get_attribute("id").replace("div_","")
                    timeOfDay = hour_button.find_element(By.CSS_SELECTOR, "a").text
                    book_link = hour_button.find_element(By.CSS_SELECTOR, "a").get_attribute("href") #Book en el sentido de reservar, no de libro xd
                    type_and_language_class = driver.find_element(By.CSS_SELECTOR, '.prd-block_info-box.pn_tipo:has([href*="' + booking_id + '"])').get_attribute("class")
                    type_and_language = type_and_language_class.replace("prd-block_info-box","").replace("prd-block_info_item","").replace("pn_tipo","").replace(" ","") #Es tremenda chanchada, tuve que hacer asi porque los selectores css no pueden agarrar a un elemento dom hermano que esta arriba del que tengo. Seguro no me entendiste pero no importa, relajate y goza
                    cinema = driver.find_element(By.CSS_SELECTOR, 'div.panel.pncomplejos:has([href*="' + booking_id + '"]) a[data-toggle="collapse"]').text
                    yield {
                        "movieId": movie_id,
                        "title": title,
                        "imageLink": image_link,
                        "lengthMinutes": length_minutes,
                        "genre": genre,
                        "director": director,
                        "cast": cast,
                        "bookingId": booking_id,
                        "date": date,
                        "time": timeOfDay,
                        "cinema": cinema,
                        "typeAndLanguage": type_and_language,
                        "bookLink": book_link 
                    }
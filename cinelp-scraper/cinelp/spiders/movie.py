#Primero hacer cd al directorio cinelp-scraper
#Ejecutar con:
#scrapy crawl movie -a "id=(id de la pelicula)" -o (nombre archivo).json
import scrapy
from scrapy_selenium import SeleniumRequest
import time
from selenium.webdriver.common.by import By

class MovieSpider(scrapy.Spider):
    name = "movie"
    allowed_domains = ["www.cinemalaplata.com"]

    def start_requests(self):
        url = "https://www.cinemalaplata.com/Titulos/Index/" + self.id
        yield SeleniumRequest(url=url, callback=self.parse)

    def parse(self, response):
        time.sleep(2) #Me tomo dos segundos de chill
        driver = response.request.meta["driver"]

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
                    "movieId": self.id,
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
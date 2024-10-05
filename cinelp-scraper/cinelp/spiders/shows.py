#Primero hacer cd al directorio cinelp-scraper
#Ejecutar con:
#scrapy crawl shows -o (nombre archivo).json
import scrapy
from scrapy_selenium import SeleniumRequest
import time
import re
from selenium.webdriver.common.by import By

class ShowsSpider(scrapy.Spider):
    name = "shows"
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
            title = movie.find_element(By.CSS_SELECTOR, "img").get_attribute("title")
            image_link = movie.find_element(By.CSS_SELECTOR, "img").get_attribute("src")
            yield {
                "movieId": movie_id,
                "title": title,
                "link": link,
                "imageLink": image_link
            }
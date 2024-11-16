import requests
from config import initialize_notifications
from models import Genero, Pelicula

from database import get_session
import time


def send_notifications():
    db = get_session()
    initialize_notifications(db)
    peliculas: list[Pelicula] = db.query(Pelicula).all()

    for pelicula in peliculas:
        genero_pelicula = (
            db.query(Genero).filter(Genero.id == pelicula.Genero_id).first()
        )
        for usuario in genero_pelicula.usuarios:
            payload = {
                "chatID": int(usuario.Telefono),
                "movieName": pelicula.nombre,
            }
            response = requests.post(
                "http://cinebot-telegram-bot-1:8080/sendMovie", json=payload
            )
            if response.status_code != 200:
                print(f"Failed to send TELEGRAM notification to user {usuario.id}")

            email_response = requests.get(
                f"http://frontend:3000/api/send-mail?email={usuario.email}&pelicula={pelicula.nombre}"
            )
            time.sleep(0.1)
            if email_response.status_code != 200:
                print(f"Failed to send EMAIL notification to user {usuario.id}")
                print(email_response.text)


if __name__ == "__main__":
    print("Sending notifications...")
    send_notifications()
    print("Notifications sent!")

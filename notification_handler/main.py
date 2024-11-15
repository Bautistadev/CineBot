from database import get_session
from models import Genero, Pelicula


def send_notifications():
    db = get_session()
    generos = db.query(Genero).all()
    peliculas: list[Pelicula] = db.query(Pelicula).all()

    for pelicula in peliculas:
        genero_pelicula = db.query(Genero).filter(Genero.id == pelicula.Genero_id).first()
        for usuario in genero_pelicula.usuarios:
            print(f"Enviando notificación a {usuario.nombre} {usuario.apellido} sobre la película {pelicula.nombre}")

def initialize_notifications(db):
    from models import Genero, Pelicula, Usuario

    new_user = Usuario(
        nombre="Tomas",
        apellido="Z",
        email="zubik.tomas@gmail.com",
        password="securepassword",
        Telefono="7352272862",
        generos=[],
    )

    new_genre = Genero(nombre="Accion", usuarios=[new_user])
    new_movie = Pelicula(
        nombre="The Dark Knight",
        genero=new_genre,
        director="Christopher Nolan",
        duracion=152,
    )
    db.add(new_user)
    db.add(new_genre)
    db.add(new_movie)
    db.commit()
    db.refresh(new_user)

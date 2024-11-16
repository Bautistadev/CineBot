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

    genre = db.query(Genero).filter(Genero.nombre == "Drama").first()
    genre.usuarios.append(new_user)
    new_movie = Pelicula(
        nombre="The Dark Knight",
        genero=genre,
        director="Christopher Nolan",
        duracion=152,
    )
    db.add(new_user)
    db.add(genre)
    db.add(new_movie)
    db.commit()
    db.refresh(new_user)

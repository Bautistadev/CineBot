from typing import List, Optional

from sqlmodel import Field, Relationship, SQLModel


class GeneroXUsuario(SQLModel, table=True):
    __tablename__ = "generoXUsuario"
    id: int | None = Field(default=None, primary_key=True)
    Usuario_id: int = Field(foreign_key="usuario.id")
    Genero_id: int = Field(foreign_key="genero.id")


class Usuario(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    nombre: str
    apellido: str
    email: str
    password: str
    Telefono: str
    generos: List["Genero"] = Relationship(
        back_populates="usuarios", link_model=GeneroXUsuario
    )


class Genero(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    nombre: str
    peliculas: List["Pelicula"] = Relationship(back_populates="genero")
    usuarios: List["Usuario"] = Relationship(
        back_populates="generos", link_model=GeneroXUsuario
    )


class Pelicula(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    nombre: str
    Genero_id: int = Field(foreign_key="genero.id")
    director: str
    duracion: int
    genero: Optional[Genero] = Relationship(back_populates="peliculas")
    carteleras: List["Cartelera"] = Relationship(back_populates="pelicula")


class Cine(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    nombre: str
    calle: str
    numero: str
    carteleras: List["Cartelera"] = Relationship(back_populates="cine")


class Cartelera(SQLModel, table=True):
    id: int | None = Field(default=None, primary_key=True)
    fecha: str
    hora: str
    Pelicula_id: int = Field(foreign_key="pelicula.id")
    Cine_id: int = Field(foreign_key="cine.id")
    pelicula: Optional[Pelicula] = Relationship(back_populates="carteleras")
    cine: Optional[Cine] = Relationship(back_populates="carteleras")

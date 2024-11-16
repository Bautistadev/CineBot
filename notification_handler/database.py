import os

from sqlmodel import Session, SQLModel, create_engine

DATABASE_URL = (
    f"mysql+mysqlconnector://{os.getenv('MYSQL_USER', 'master')}:"
    f"{os.getenv('MYSQL_PASSWORD', 'master')}@"
    f"cinebot-database-1/{os.getenv('MYSQL_DATABASE', 'cinebotdb')}"
)

engine = create_engine(DATABASE_URL)


def get_session():
    return Session(engine)

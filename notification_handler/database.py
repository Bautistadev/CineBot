from sqlmodel import SQLModel, create_engine, Session
import os

DATABASE_URL = (
    f"mysql+mysqlconnector://{os.getenv('MYSQL_USER', 'master')}:"
    f"{os.getenv('MYSQL_PASSWORD', 'master')}@"
    f"localhost/{os.getenv('MYSQL_DATABASE', 'cinebotdb')}"
)

engine = create_engine(DATABASE_URL)

def get_session():
    return Session(engine)
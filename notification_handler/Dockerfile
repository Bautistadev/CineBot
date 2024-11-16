FROM python:3.12-slim

# Set the working directory
WORKDIR /app

COPY requirements.txt requirements.txt

# Install dependencies
RUN pip install -r requirements.txt --no-cache-dir

# Copy the current directory contents into the container at /app
COPY . /app

# Run main.py
CMD ["python", "main.py"]
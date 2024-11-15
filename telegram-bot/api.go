package main

import (
	"io"
	"net/http"
)

func getMovies() (string, error) {
	resp, err := http.Get(cfg.BackendURL + "/movies")
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	return string(body), nil
}

func getMoviesByGenre(genre string) (string, error) {
	resp, err := http.Get(cfg.BackendURL + "/movies-by-genre/?genre=" + genre)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	return string(body), nil
}

func getMovieByName(name string) (string, error) {
	resp, err := http.Get(cfg.BackendURL + "/movie-by-name/?movie_name=" + name)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	return string(body), nil
}

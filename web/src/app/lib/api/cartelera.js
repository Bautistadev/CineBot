'use server'

import { apiRequest } from "../apiRequest";

export async function getMovies(){
    return await apiRequest(`/Cartelera/movies`, 'GET', null, 'application/json')
}

export async function getMoviesByName(name){
    return await apiRequest(`/Cartelera/movies-by-name?name=${name}`, 'GET', null, 'application/json')
}


export async function getMoviesByGenre(gender){
    return await apiRequest(`/Cartelera/movies-by-genre/?genreId=${gender}`, 'GET', null, 'application/json')
}

'use server'

import { apiRequest } from "../apiRequest";

export async function subscribeToGender(userId, genderId){
    return await apiRequest(`/Genero/suscribe?userId=${userId}&genderId=${genderId}`, 'GET', null, 'application/json')
}

'use server'

import { apiRequest } from "../apiRequest";

export async function createUser(data){
    return await apiRequest(`/Usuario/save`, 'POST', data, 'application/json')
}

export async function loginUser(data){
    return await apiRequest(`/Login`, 'POST', data, 'application/json')
}

export async function findById(id){
    return await apiRequest(`/Usuario/findById?id=${id}`, 'GET', null, 'application/json')
}

export async function findByEmail(email){
    return await apiRequest(`/Usuario/findByEmail?email=${email}`, 'GET', null, 'application/json')
}

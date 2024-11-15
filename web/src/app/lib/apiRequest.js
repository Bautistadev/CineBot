export async function apiRequest(endpoint, method = 'GET', body = null, contentType = 'application/json') {

    const url = new URL(`http://localhost:8080/api/v1/cineBot${endpoint}`)
  
    const options = {
      method,
      headers: {}
    }

    if(contentType == 'application/json'){
      options.headers['Content-Type'] = 'application/json'
    }

    if (body) {
      options.body = contentType === 'application/json' ? JSON.stringify(body) : body
    }
    const res = await fetch(url, options)
  
    if (!res.ok) {
      const errorData = await res.json()
      console.log(errorData)
      throw new Error(`Error en el request`)
    }

    return await res.json()
  }
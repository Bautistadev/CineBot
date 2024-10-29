'use client'

import React, { useState } from 'react';
import { Button } from "../components/button"
import Link from "next/link"

function Page() {
  // Estado inicial con los campos del UsuarioDTO
  const [formData, setFormData] = useState({
    nombre: '',
    apellido: '',
    email: '',
    password: '',
    telefono: '',
  });

  // Manejo de cambios en los campos del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // Envío de datos al backend
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Solicitud POST al endpoint del backend
      const response = await fetch('', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        console.log('Usuario autenticado:', data);
        // Lógica adicional para manejar el login exitoso
      } else {
        console.error('Error en el inicio de sesión');
      }
    } catch (error) {
      console.error('Error al conectar con el servidor:', error);
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', padding: '2rem' }}>
      <h2>Iniciar Sesión</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre:</label>
          <input
                  type="text"
                  name="nombre"
                  placeholder="Escribe un mensaje..."
                  className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
                  value={formData.nombre}
                  onChange={handleChange}
                  required
                />
          
        </div>
        <div>
          <label>Apellido:</label>
          <input
              type="text"
              name="apellido"
              placeholder="Escribe tu apellido..."
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
              value={formData.apellido}
              onChange={handleChange}
              required
            />
        </div>
        <div>
          <label>Email:</label>
          <input
              type="email"
              name="email"
              placeholder="Escribe tu email..."
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
              value={formData.email}
              onChange={handleChange}
              required
            />
        </div>
        <div>
          <label>Contraseña:</label>
          <input
              type="password"
              name="password"
              placeholder="Escribe tu contraseña..."
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
              value={formData.password}
              onChange={handleChange}
              required
            />
        </div>
        <div>
          <label>Teléfono:</label>
          <input
              type="tel"
              name="telefono"
              placeholder="Escribe tu teléfono..."
              className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
              value={formData.telefono}
              onChange={handleChange}
              required
            />
        </div>
        <div className="space-x-4">
                        <Button>Inicio de sesion</Button>
                    </div>
      </form>
    </div>
  );
}

export default Page;
'use client'

import { createUser } from "../lib/api/user";
import { useRouter } from "next/navigation";
import { errorToast } from "../util/toast";


export default function Register() {

  const router = useRouter();

    const handleSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData(e.target);
      const data = {
        email: formData.get("email"),
        password: formData.get("password"),
        nombre: formData.get("nombre"),
        apellido: formData.get("apellido"),
        telefono: formData.get("telefono"),
      };
      try{
        await createUser(data)
        router.push("/login")
        successToast("Registro exitoso!")
      }catch(err){
        errorToast()
      }
    };

  
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-100">
        <div className="bg-white p-8 rounded shadow-md w-full max-w-md">
          <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">Registro</h2>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label htmlFor="nombre" className="block text-sm font-medium text-gray-600">
                Nombre
              </label>
              <input
                type="text"
                name="nombre"
                id="nombre"
                required
                className="w-full mt-1 p-2 border rounded bg-gray-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400"
                placeholder="Tu nombre"
              />
            </div>
            <div>
              <label htmlFor="apellido" className="block text-sm font-medium text-gray-600">
                Apellido
              </label>
              <input
                type="text"
                name="apellido"
                id="apellido"
                required
                className="w-full mt-1 p-2 border rounded bg-gray-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400"
                placeholder="Tu apellido"
              />
            </div>
            <div>
              <label htmlFor="telefono" className="block text-sm font-medium text-gray-600">
                Teléfono
              </label>
              <input
                type="tel"
                name="telefono"
                id="telefono"
                required
                className="w-full mt-1 p-2 border rounded bg-gray-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400"
                placeholder="1234567890"
              />
            </div>
            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-600">
                Email
              </label>
              <input
                type="email"
                name="email"
                id="email"
                required
                className="w-full mt-1 p-2 border rounded bg-gray-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400"
                placeholder="you@example.com"
              />
            </div>
            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-600">
                Contraseña
              </label>
              <input
                type="password"
                name="password"
                id="password"
                required
                className="w-full mt-1 p-2 border rounded bg-gray-50 text-black focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-blue-400"
                placeholder="********"
              />
            </div>
            <button
              type="submit"
              className="w-full py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-offset-2"
            >
              Registrarse
            </button>
          </form>
          <p className="mt-4 text-center text-sm text-gray-600">
            ¿Ya tienes una cuenta?{" "}
            <a href="/login" className="text-blue-500 hover:underline">
              Inicia sesión aquí
            </a>
          </p>
        </div>
      </div>
    );
  }
  
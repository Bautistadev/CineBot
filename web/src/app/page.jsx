import { Button } from "./components/button"
import { Input } from "./components/input"
import { Film, MessageCircle, Calendar, Ticket, Menu } from "lucide-react"
import Link from "next/link"

export default function Landing() {
  return (
    <div className="flex flex-col min-h-screen">
      <header className="px-4 lg:px-6 h-14 flex items-center">
        <Link className="flex items-center justify-center" href="#">
          <Film className="h-6 w-6" />
          <span className="sr-only">Cinema Bot</span>
        </Link>
        <nav className="ml-auto flex gap-4 sm:gap-6">
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="#">
            Chatbot
          </Link>
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="#">
            Nosotros
          </Link>
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="#">
            Login
          </Link>
        </nav>
      </header>
      <main className="flex-1">
        <section className="w-full py-12 md:py-24 lg:py-32 xl:py-48">
          <div className="px-4 md:px-6">
            <div className="flex flex-col items-center space-y-4 text-center">
              <div className="space-y-2">
                <h1 className="text-3xl font-bold tracking-tighter sm:text-4xl md:text-5xl lg:text-6xl/none">
                  CinemaBot
                </h1>
                <p className="mx-auto max-w-[700px] text-gray-500 md:text-xl dark:text-gray-400">
                  Tu asistente personal para no perderte ningun estreno.
                </p>
                <p className="mx-auto max-w-[700px] text-gray-500 md:text-xl dark:text-gray-400">
                  Revisar la cartelera y recibir recomendaciones de peliculas nunca fue tan facil!
                </p>
              </div>
              <div className="space-x-4">
                <Button>Chatear</Button>
              </div>
            </div>
          </div>
        </section>
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
          <div className=" px-4 md:px-6">
            <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl text-center mb-12">CinemaBot te brinda</h2>
            <div className="grid gap-10 sm:grid-cols-2 md:grid-cols-4">
              <div className="flex flex-col items-center space-y-3 text-center">
                <MessageCircle className="h-12 w-12 text-primary" />
                <h3 className="text-xl font-bold">Asistencia 24/7</h3>
                <p className="text-gray-500 dark:text-gray-400">Siempre disponible para ayudarte a encontrar tu pelicula.</p>
              </div>
              <div className="flex flex-col items-center space-y-3 text-center">
                <Calendar className="h-12 w-12 text-primary" />
                <h3 className="text-xl font-bold">Notificaciones</h3>
                <p className="text-gray-500 dark:text-gray-400">Te mantiene al tanto de los nuevos estrenos.</p>
              </div>
              <div className="flex flex-col items-center space-y-3 text-center">
                <Ticket className="h-12 w-12 text-primary" />
                <h3 className="text-xl font-bold">Personalizacion</h3>
                <p className="text-gray-500 dark:text-gray-400">Cinemabot conoce tus gustos, esto mejora las recomendaciones.</p>
              </div>
              <div className="flex flex-col items-center space-y-3 text-center">
                <Ticket className="h-12 w-12 text-primary" />
                <h3 className="text-xl font-bold">Multi-plataforma</h3>
                <p className="text-gray-500 dark:text-gray-400">Utilizalo por la web o en telegram. Proximamente en nuevas plataformas...</p>
              </div>
            </div>
          </div>
        </section>
        <section className="w-full py-12 md:py-24 lg:py-32">
          <div className="px-4 md:px-6">
            <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl text-center mb-12">Prueba CinemaBot</h2>
            <div className="mx-auto max-w-sm space-y-4 bg-slate-600 rounded-lg p-4">
              <div className="space-y-4">
                <div className="flex justify-start">
                  <div className="bg-slate-50 text-black rounded-lg px-4 py-2 max-w-[75%]">
                    Hola! Aca CinemaBot. En que puedo ayudarte?
                  </div>
                </div>
                <div className="flex justify-end">
                  <div className="bg-slate-50 text-black rounded-lg px-4 py-2 max-w-[75%]">
                    Hola, que peliculas de super heroes hay hoy?
                  </div>
                </div>
                <div className="flex justify-start">
                  <div className="bg-slate-50 text-black rounded-lg px-4 py-2 max-w-[75%]">
                    Aqui estan las peliculas de super heroes:
                    <br />BATMAN - 22:00hs
                    <br />DEADPOOL Y WOLVERINE - 23:10hs
                    <br />GUASÓN 2: FOLIE À DEUX - 18:00hs
                  </div>
                </div>
              </div>
              <div className="space-x-2">
                <Input placeholder="Escribe un mensaje..." />
                <Button size="icon">
                  Enviar
                  <span className="sr-only">Enviar</span>
                </Button>
              </div>
            </div>
          </div>
        </section>
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-100 dark:bg-gray-800">
          <div className="px-4 md:px-6">
            <div className="flex flex-col items-center justify-center space-y-4 text-center">
              <div className="space-y-2">
                <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl">
                  Esta al tanto de los nuevos estrenos con CinemaBot
                </h2>
                <p className="mx-auto max-w-[600px] text-gray-500 md:text-xl/relaxed lg:text-base/relaxed xl:text-xl/relaxed dark:text-gray-400">
                  Comienza a chatear ahora y recibe las mejores recomendaciones para ir al cine.
                </p>
              </div>
              <Button size="lg">Comenzar ahora</Button>
            </div>
          </div>
        </section>
      </main>
      <footer className="flex flex-col gap-2 sm:flex-row py-6 w-full shrink-0 items-center px-4 md:px-6 border-t">
        <p className="text-xs text-gray-500 dark:text-gray-400">© 2024 Cinema Bot. Todos los derechos reservados</p>
        <nav className="sm:ml-auto flex gap-4 sm:gap-6">
          <Link className="text-xs hover:underline underline-offset-4" href="#">
            Terminos de servicio
          </Link>
          <Link className="text-xs hover:underline underline-offset-4" href="#">
            Privacidad
          </Link>
        </nav>
      </footer>
    </div>
  )
}
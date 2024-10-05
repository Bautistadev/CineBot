import { Calendar, MessageCircle, Ticket } from "lucide-react"

export const SectionB = () => {
    return(
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-800 dark:bg-gray-800">
          <div className=" px-4 md:px-6">
            <h2 className="text-3xl font-bold tracking-tighter sm:text-5xl text-center mb-12">CineBot te brinda</h2>
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
                <p className="text-gray-500 dark:text-gray-400">Cinebot conoce tus gustos, esto mejora las recomendaciones.</p>
              </div>
              <div className="flex flex-col items-center space-y-3 text-center">
                <Ticket className="h-12 w-12 text-primary" />
                <h3 className="text-xl font-bold">Multi-plataforma</h3>
                <p className="text-gray-500 dark:text-gray-400">Utilizalo por la web o en telegram. Proximamente en nuevas plataformas...</p>
              </div>
            </div>
          </div>
        </section>
    )
}
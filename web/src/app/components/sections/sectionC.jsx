import { Button } from "../button"
import { Input } from "../input"

export const SectionC = () => {
    return(
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-200 dark:bg-gray-200">
          <div className="px-4 md:px-6">
            <h2 className="text-3xl text-black font-bold tracking-tighter sm:text-5xl text-center mb-12">Prueba CinemaBot</h2>
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
              <div className="flex space-x-2 justify-end">
                <Input placeholder="Escribe un mensaje..."/>
                <Button size="icon">
                  Enviar
                  <span className="sr-only">Enviar</span>
                </Button>
              </div>
            </div>
          </div>
        </section>
    )
}
import { Button } from "../button"
import { Input } from "../input"

export const SectionE = () => {
    return(
        <section className="w-full py-12 md:py-24 lg:py-32 bg-gray-200 dark:bg-gray-200">
          <div className="px-4 md:px-6">
            <h2 className="text-3xl text-black font-bold tracking-tighter sm:text-5xl text-center mb-12">Suscribete</h2>
            <p className="mx-auto max-w-[700px] text-slate-950 md:text-xl dark:text-slate-950">
                  Suscribete ahora para no perderte ninguna de las funciones</p>
                <Input placeholder="Escribe un email..."/>
                <Button size="lg">Suscribirse</Button>
            </div>
        </section>
    )
}
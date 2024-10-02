import { Button } from "../button"

export const SectionD = () => {
    return(
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
    )
}
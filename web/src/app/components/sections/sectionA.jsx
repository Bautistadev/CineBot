import Image from "next/image"
import { Button } from "../button"

export const SectionA = () => {
    return(
        <section className="w-full py-10 md:py-16 bg-gray-200 dark:bg-gray-200">
            <div className="">
                <div className="flex flex-col items-center space-y-4 text-center">
                    <div className="space-y-2">
                    <Image src='/images/cinema-bot-logo.png' className="mx-auto" width={220} height={220}/>
                    <h1 className="text-3xl font-bold text-black tracking-tighter sm:text-4xl md:text-5xl lg:text-6xl/none">
                        CinemaBot
                    </h1>
                    <p className="mx-auto max-w-[700px] text-slate-950 md:text-xl dark:text-slate-950">
                        Tu asistente personal para no perderte ningun estreno.
                    </p>
                    <p className="mx-auto max-w-[700px] text-slate-950 md:text-xl dark:text-slate-950">
                        Revisar la cartelera y recibir recomendaciones de peliculas nunca fue tan facil!
                    </p>
                    </div>
                    <div className="space-x-4">
                    <Button>Chatear</Button>
                    </div>
            </div>
            </div>
        </section>
    )
}
import { Film } from "lucide-react"
import Link from "next/link"

export const Navbar = () => {
    return(
        <header className="px-4 lg:px-6 h-14 flex items-center">
            <Link className="flex items-center justify-center" href="/">
                <Film className="h-6 w-6" />
                <span className="sr-only">Cine Bot</span>
            </Link>
            <nav className="ml-auto flex gap-4 sm:gap-6">
                <Link className="text-sm font-medium hover:underline underline-offset-4" href="/chat">
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
    )
}
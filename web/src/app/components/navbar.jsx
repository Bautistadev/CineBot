"use client";

import { Film } from "lucide-react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useAuthStore } from "../store/authStore";


export const Navbar = () => {
  const isLoggedIn = useAuthStore((state) => state.isLoggedIn);
  const logout = useAuthStore((state) => state.logout);
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem("userId");
    logout(); // Actualiza el estado global
    router.push("/");
  };

  return (
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
        {isLoggedIn ? (
          <button
            onClick={handleLogout}
            className="text-sm font-medium text-red-500 hover:underline underline-offset-4"
          >
            Salir
          </button>
        ) : (
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="/login">
            Login
          </Link>
        )}
      </nav>
    </header>
  );
};

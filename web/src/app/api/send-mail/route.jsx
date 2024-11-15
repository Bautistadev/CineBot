import { Resend } from "resend";
import { CineNotificationEmail  } from "../../components/email/emailSubs";
import { NextResponse } from "next/server";

const resend = new Resend(process.env.RESEND_API_KEY);

export async function GET(request) {
  const { searchParams } = new URL(request.url);

  // Obtener los parámetros de la URL
  const email = searchParams.get("email");
  const pelicula = searchParams.get("pelicula");

 
  if (!email || !pelicula) {
    return NextResponse.json(
      { error: "Faltan parámetros 'email' y/o 'pelicula'" },
      { status: 400 }
    );
  }

  try {
    const emailResponse = await resend.emails.send({
      to: email,
      from: "CineBot <onboarding@resend.dev>",
      subject: "Cinebot notifica que una pelicula de tu gusto está en cartelera",
      react: <CineNotificationEmail  pelicula={pelicula} />,
    });

    return NextResponse.json(emailResponse);
  } catch (error) {
    return NextResponse.json({ error: error.message }, { status: 500 });
  }
}

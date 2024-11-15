import { Resend } from "resend";
import { CineNotificationEmail } from "../../components/email/email";
import { EmailSubs } from "../../components/email/emailSubs"; // Asegúrate de importar este componente
import { NextResponse } from "next/server";

const resend = new Resend(process.env.RESEND_API_KEY);

export async function GET(request) {
  const { searchParams } = new URL(request.url);

  // Obtener los parámetros de la URL
  const email = searchParams.get("email");
  const pelicula = searchParams.get("pelicula");

  // Validar los parámetros y enviar el correo correspondiente
  if (!email && !pelicula) {
    return NextResponse.json(
      { error: "Faltan parámetros 'email' y/o 'pelicula'" },
      { status: 400 }
    );
  }

  try {
    let emailResponse;

    if (email && !pelicula) {
      // Caso donde solo hay email
      emailResponse = await resend.emails.send({
        to: email,
        from: "CineBot <onboarding@resend.dev>",
        subject: "Cinebot te da la bienvenida",
        react: <CineNotificationEmail />, // Usamos el componente CineNotificationEmail
      });
    } else if (email && pelicula) {
      // Caso donde hay email y pelicula
      emailResponse = await resend.emails.send({
        to: email,
        from: "CineBot <onboarding@resend.dev>",
        subject: "Cinebot notifica que una película de tu gusto está en cartelera",
        react: <EmailSubs pelicula={pelicula} />, // Usamos EmailSubs
      });
    }

    return NextResponse.json(emailResponse);
  } catch (error) {
    return NextResponse.json({ error: error.message }, { status: 500 });
  }
}

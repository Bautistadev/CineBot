import { Resend } from "resend";
import { CodepenChallengersEmail } from "../../components/email/email"
import { NextResponse } from "next/server";

const resend = new Resend(process.env.RESEND_API_KEY);

export async function GET() {
  const emailResponse = await resend.emails.send({
    to: "fblancocavallero@gmail.com",
    from: "CineBot <onboarding@resend.dev>",
    subject: "Cinebot notifica que una pelicula de tu gusto esta en cartelera",
    react: <CodepenChallengersEmail />,
  });

  return NextResponse.json(emailResponse);
}

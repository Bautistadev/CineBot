import {
  Body,
  Button,
  Column,
  Container,
  Head,
  Heading,
  Html,
  Img,
  Link,
  Preview,
  Row,
  Section,
  Text,
  img
} from "@react-email/components";

const baseUrl = process.env.VERCEL_URL
  ? `https://${process.env.VERCEL_URL}/images`
  : "/images";

export const CineNotificationEmail = () => (
  <Html>
      <Head />
      <Preview>Cinebot: ¡No te pierdas tu próxima película favorita!</Preview>
      <Body style={main}>
          <Section style={header}>

          </Section>
          <Container style={container}>
              <Text style={welcomeText}>
                  <Link style={link}>¡Hola cinéfilo! 🎬</Link>
              </Text>

              <Heading style={heading}>Cinebot: Notificación de Cartelera</Heading>
              

              <Section style={section}>
                  <Text style={text}>
                      Te avisamos que estaremos atentos a las películas de tu preferencia.
                  </Text>

                  <Text style={text}>
                      A partir de ahora, recibirás notificaciones de nuevas películas en
                      cartelera que coincidan con tus intereses, ya sea por género,
                      autor, o incluso por el título específico.
                  </Text>

                  <Text style={highlightText}>
                      🎥 Próxima notificación para: <strong>Spiderman: No Way Home</strong>
                  </Text>


                 
                  <Link href={`${baseUrl}/movie/spiderman-no-way-home`} style={buttonLink}>
                      Ver más detalles
                  </Link>
                  

                  <Section style={tipsSection}>
                      <Heading style={tipsHeading}>Tips para mejorar tu experiencia:</Heading>
                      <Text style={tipsText}>💡 Configura tus preferencias de notificación.</Text>
                      <Text style={tipsText}>💡 Marca tus géneros favoritos para recibir más recomendaciones.</Text>
                  </Section>

                  <Text style={footerText}>
                      Puedes ajustar tus{" "}
                      <Link style={footerLink}>preferencias de notificación</Link> en
                      cualquier momento o <Link style={footerLink}>optar por salir</Link> de estas notificaciones.
                  </Text>
              </Section>
          </Container>
      </Body>
  </Html>
);

export default CineNotificationEmail;

const main = {
  fontFamily: '"Google Sans", Roboto, Arial, sans-serif',
  backgroundColor: "#f5f5f5",
  margin: "0",
  padding: "0",
};

const imgHeader = {
  margin: "auto",
  maxWidth: "100%",
};

const header = {
  width: "100%",
  backgroundColor: "#333",
  padding: "20px 0",
  textAlign: "center",
};

const container = {
  margin: "0 auto",
  width: "600px",
  backgroundColor: "#ffffff",
  padding: "20px",
  borderRadius: "8px",
};

const welcomeText = {
  fontSize: "14px",
  textAlign: "center",
  color: "#888",
};

const link = {
  color: "#007BFF",
  textDecoration: "none",
};

const heading = {
  fontSize: "24px",
  color: "#333",
  margin: "20px 0",
  textAlign: "center",
};

const imgCinemaBotLogo = {
  display: "block",
  margin: "20px auto",
  width: "200px",
  height: "auto",
  borderRadius: "8px",
};

const section = {
  padding: "20px",
  textAlign: "center",
};

const text = {
  fontSize: "16px",
  color: "#555",
  marginBottom: "10px",
};

const highlightText = {
  fontSize: "18px",
  fontWeight: "bold",
  color: "#D9534F",
  marginTop: "20px",
};

const imgMovie = {
  maxWidth: "100%",
  marginTop: "10px",
  borderRadius: "8px",
};


const buttonLink = {
  marginTop: "40px",
  color: "black",
  textDecoration: "none",
  fontWeight: "bold",
  backgroundColor: "#007BFF",
  padding: "12px 24px",
  borderRadius: "5px",
};

const tipsSection = {
  marginTop: "30px",
  backgroundColor: "#F0F8FF",
  padding: "20px",
  borderRadius: "8px",
  textAlign: "left",
};

const tipsHeading = {
  fontSize: "18px",
  color: "#333",
  marginBottom: "10px",
};

const tipsText = {
  fontSize: "14px",
  color: "#555",
  marginBottom: "6px",
};

const footerText = {
  fontSize: "12px",
  color: "#888",
  textAlign: "center",
  marginTop: "20px",
};

const footerLink = {
  color: "#007BFF",
  textDecoration: "underline",
  cursor: "pointer",
};

'use client'

import { useState, useEffect } from 'react';
import { getMovies, getMoviesByGenre, getMoviesByName } from '../lib/api/cartelera';
import { subscribeToGender } from '../lib/api/genero';
import { successToast } from '../util/toast';

const ChatPage = () => {
  const [messages, setMessages] = useState([]);
  const [inputValue, setInputValue] = useState('');
  const [isStartMenu, setIsStartMenu] = useState(true)
  const [isSelectingGender, setIsSelectingGender] = useState(false)
  const [isSelectingName, setIsSelectingName] = useState(false)
  const [isSubscribing, setIsSubscribing] = useState(false)

  const menuText = 'Que le gustaria hacer?\n' +
  '1. Ver cartelera 🍿\n' +
  '2. Ver peliculas por genero 🎭\n' +
  '3. Ver peliculas por titulo 📀\n' +
  '4. Suscribirse a un genero 👀\n' +
  '5. Nada';

  useEffect(() => {
    setMessages([
      {
        text: 'Hola! 👋 bienvenido a CineBot 🤖',
        sender: 'bot',
      },
      {
        text: menuText,
        sender: 'bot',
      },
    ]);
  }, []);

  const carteleraOption = async (newMessages) => {
    setIsStartMenu(false)
    const data = await getMovies()
    const text = "---------ESTRENOS---------\n" + data.map((item) => {
      return `Pelicula: ${item.pelicula.nombre}\n` +
             `Cine: ${item.cine.nombre}\n` +
             `Fecha: ${item.fecha}\n` +
             `Hora: ${item.hora}\n` +
             `Director: ${item.pelicula.director}\n` +
             `Duracion: ${item.pelicula.duracion} min\n` +
             `Genero: ${item.pelicula.genero.nombre}\n` +
             '----------------------------------\n';
    });
    setMessages([
      ...newMessages,
      {
        text: text,
        sender: 'bot',
      },
    ]);
  }

  const genderOption = async (newMessages) => {
    setIsSelectingGender(true)
    setIsStartMenu(false)
    const message = 'De que genero desea ver?\n' +
                    '1. Accion\n' +
                    '2. Emocion\n' +
                    '3. Terror\n' +
                    '4. Drama\n' +
                    '5. Comedia';
    setMessages([
      ...newMessages,
      {
        text: message,
        sender: 'bot',
      },
    ]);
  }

  const subscribeOption = async (newMessages) => {
    setIsSubscribing(true)
    setIsStartMenu(false)
    const message = 'A que genero desea subscribirse?\n' +
                    '1. Accion\n' +
                    '2. Emocion\n' +
                    '3. Terror\n' +
                    '4. Drama\n' +
                    '5. Comedia';
    setMessages([
      ...newMessages,
      {
        text: message,
        sender: 'bot',
      },
    ]);
  }


  const selectGenderOption = async (newMessages, gender) => {
    setIsSelectingGender(false)
    setIsStartMenu(false)
    let text
    try{
      const data = await getMoviesByGenre(Number(gender))
      if(data.length == 0){
        text = "No hay funciones para esa pelicula :/"
      }else{
        text = "---------PELICULAS---------\n" + data.map((item) => {
          return `Pelicula: ${item.pelicula.nombre}\n` +
                 `Cine: ${item.cine.nombre}\n` +
                 `Fecha: ${item.fecha}\n` +
                 `Hora: ${item.hora}\n` +
                 `Director: ${item.pelicula.director}\n` +
                 `Duracion: ${item.pelicula.duracion} min\n` +
                 `Genero: ${item.pelicula.genero.nombre}\n` +
                 '----------------------------------\n';
        });
      }

    }catch(err){
      text = 'Ocurrio un error al consultar el genero :('
      setIsStartMenu(true)
    }
    setMessages([
      ...newMessages,
      {
        text: text,
        sender: 'bot',
      },
    ]);
  }


  const selectSubscribeGenderOption = async (newMessages, gender) => {
    setIsSubscribing(false)
    setIsStartMenu(false)
    let text
    try{
      await subscribeToGender(Number(localStorage.getItem("userId")),Number(gender))
      successToast("Suscripción exitosa")
      text = "Te subscribiste exitosamente al genero, pronto recibiras emails con recomendaciones!"
    }catch(err){
      text = 'Ya esta subscripto al genero'
      setIsStartMenu(true)
    }
    setMessages([
      ...newMessages,
      {
        text: text,
        sender: 'bot',
      },
    ]);
  }

  const movieByNameOption = async (newMessages) => {
    setIsSelectingName(true)
    const message = 'Ingrese el nombre de la pelicula' 
    setMessages([
      ...newMessages,
      {
        text: message,
        sender: 'bot',
      },
    ]);
  }

  const selectByNameOption = async (newMessages, name) => {
    setIsSelectingName(false)
    setIsStartMenu(false)
    let text
    try{
      const data = await getMoviesByName(name.toUpperCase())
      if(data.length == 0){
        text = "No hay funciones para esa pelicula :/"
      }else{
        text = "---------FUNCIONES---------\n" + data.map((item) => {
          return `Pelicula: ${item.pelicula.nombre}\n` +
                 `Cine: ${item.cine.nombre}\n` +
                 `Fecha: ${item.fecha}\n` +
                 `Hora: ${item.hora}\n` +
                 `Director: ${item.pelicula.director}\n` +
                 `Duracion: ${item.pelicula.duracion} min\n` +
                 `Genero: ${item.pelicula.genero.nombre}\n` +
                 '----------------------------------\n';
        });
      }

    }catch(err){
      text = 'No se encontro la pelicula'
    }
    setMessages([
      ...newMessages,
      {
        text: text,
        sender: 'bot',
      },
    ]);
  }

  const defaultOption = async (newMessages) => {
    setMessages([
      ...newMessages,
      {
        text: menuText,
        sender: 'bot',
      },
    ]);
  }

  const exitOption = async (newMessages) => {
    const text = "Oki! Vuelva pronto :)"
    setMessages([
      ...newMessages,
      {
        text: text,
        sender: 'bot',
      },
    ]);
  }

  const handleSendMessage = async () => {
    if (inputValue.trim()) {
      const newMessages = [...messages, { text: inputValue, sender: 'user' }];
      setMessages(newMessages);
      if(isSelectingGender){
        await selectGenderOption(newMessages, inputValue)
        setInputValue('');
        return
      }
      if(isSelectingName){
        await selectByNameOption(newMessages, inputValue)
        setInputValue('');
        return
      }
      if(isSubscribing){
        await selectSubscribeGenderOption(newMessages, inputValue)
        setInputValue('');
        return
      }

      if (inputValue.trim().toLowerCase() === '1' && isStartMenu) {
        await carteleraOption(newMessages)
        setInputValue('');
        return
      }
      if (inputValue.trim().toLowerCase() === '2' && isStartMenu) {
        genderOption(newMessages)
        setInputValue('');
        return
      }
      if (inputValue.trim().toLowerCase() === '3' && isStartMenu) {
        movieByNameOption(newMessages)
        setInputValue('');
        return
      }
      if(inputValue.trim().toLowerCase() === '4' && isStartMenu){
        subscribeOption(newMessages)
        setInputValue('');
        return
      }
      if(inputValue.trim().toLowerCase() === '5' && isStartMenu){
        setInputValue('');
        setIsStartMenu(false)
        exitOption(newMessages)
        return
      }
      defaultOption(newMessages)
      setIsStartMenu(true)
      setInputValue('');
    }
  };

  // Detectar la tecla "Enter" para enviar el mensaje
  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      e.preventDefault(); 
      handleSendMessage();
    }
  };

  return (
    <>
      <section className="w-full bg-gray-800 dark:bg-gray-800">
        <div className="flex justify-center items-center min-h-screen">
          <div className="bg-white rounded-lg shadow-lg w-full max-w-4xl p-6">
            <div className="space-y-3">
              <h2 className="text-3xl text-gray-700 font-bold text-center mb-6">CineBot</h2>
              <div className="border p-4 h-80 overflow-y-scroll bg-gray-100 rounded-md">
                {messages.length > 0 ? (
                  messages.map((message, index) => (
                    <div
                      key={index}
                      className={`flex ${
                        message.sender === 'user' ? 'justify-end' : 'justify-start'
                      }`}
                    >
                      <div
                        className={`p-2 my-2 max-w-[75%] whitespace-pre-line ${
                          message.sender === 'user'
                            ? 'bg-blue-500 text-white rounded-l-lg rounded-br-lg'
                            : 'bg-gray-300 text-black rounded-r-lg rounded-bl-lg'
                        }`}
                      >
                        {message.text}
                      </div>
                    </div>
                  ))
                ) : (
                  <p className="text-gray-700">Empieza una conversación con CineBot...</p>
                )}
              </div>

              <div className="flex space-x-4 mt-4">
                <input
                  type="text"
                  value={inputValue}
                  onChange={(e) => setInputValue(e.target.value)}
                  onKeyDown={handleKeyDown} 
                  placeholder="Escribe un mensaje..."
                  className="w-full p-3 border border-gray-300 rounded-md focus:outline-none text-gray-700 focus:ring-2 focus:ring-blue-500"
                />
                <button
                  onClick={handleSendMessage}
                  className="px-6 py-3 bg-blue-600 text-white font-bold rounded-md hover:bg-blue-500 transition"
                >
                  Enviar
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default ChatPage;

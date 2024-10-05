'use client'

import { useState, useEffect } from 'react';

const ChatPage = () => {
  const [messages, setMessages] = useState([]);
  const [inputValue, setInputValue] = useState('');

  useEffect(() => {
    setMessages([
      {
        text: 'Hola! Actualmente estoy en construcción.\n\n Puedes utilizar el comando "estrenos" para obtener algunas recomendaciones.',
        sender: 'bot',
      },
    ]);
  }, []);

  const handleSendMessage = () => {
    if (inputValue.trim()) {
      const newMessages = [...messages, { text: inputValue, sender: 'user' }];
      setMessages(newMessages);

      // Verificar si el usuario escribe la palabra "funciones"
      if (inputValue.trim().toLowerCase() === 'estrenos') {
        setMessages([
          ...newMessages,
          {
            text: `JUNG KOOK: I AM STILL\n\nFUNCIONES\n\tCINE: CINEMA CITY \n\t- 20:10 - HD - Subtitulada\n\n-------------------------------------\n\nGUASÓN 2: FOLIE À DEUX\n\nFUNCIONES\n\n\tCINE: PARADISO\n\t- 12:00 - HD - Castellano\n\t- 18:00 - HD - Castellano\n\t- 15:00 - HD - Subtitulada\n\t- 20:00 - HD - Subtitulada\n\t- 23:00 - HD - Subtitulada\n\n\tCINE: CINEMA OCHO\n\t- 12:30 - HD - Castellano\n\t- 15:30 - HD - Castellano\n\t- 18:30 - HD - Castellano\n\t- 21:30 - HD - Castellano\n\n\tCINE: CINEMA CITY\n\t- 12:45 - 4D - Castellano\n\t- 15:45 - 4D - Castellano\n\t- 13:10 - HD - Subtitulada\n\t- 19:10 - HD - Subtitulada\n\t- 22:10 - HD - Subtitulada\n\t- 16:10 - HD - Castellano\n\t- 18:45 - 4D - Subtitulada\n\t- 21:45 - 4D - Subtitulada`,
            sender: 'bot',
          },
        ]);
      }

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
          <div className="bg-white rounded-lg shadow-lg w-full max-w-2xl p-6">
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

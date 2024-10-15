package main

import (
	"context"
	"log"
	"os"
	"os/signal"

	"github.com/go-telegram/bot"
	"github.com/go-telegram/bot/models"
)

var userState = make(map[int64]string)

func main() {

	LoadConfig()
	ctx, cancel := signal.NotifyContext(context.Background(), os.Interrupt)
	defer cancel()

	opts := []bot.Option{
		bot.WithDefaultHandler(handler),
	}

	b, err := bot.New(cfg.TelegramBotToken, opts...)
	if err != nil {
		panic(err)
	}

	b.Start(ctx)
}

func handler(ctx context.Context, b *bot.Bot, update *models.Update) {
	state := getUserState(ctx, b, update)
	switch state {
	case "menu":
		b.SendMessage(ctx, &bot.SendMessageParams{
			ChatID: update.Message.Chat.ID,
			Text: `Que le gustaria hacer?
				1- Ver cartelera
				2- Ver peliculas por genero
				3- Ver peliculas por titulo
				4- Finalizar`,
		})

	case "1":
		movies, err := getMovies()
		if err != nil {
			b.SendMessage(ctx, &bot.SendMessageParams{
				ChatID: update.Message.Chat.ID,
				Text:   "Error al obtener la cartelera. Por favor, intente nuevamente más tarde.",
			})
			log.Printf("Error getting movies: %v", err)
		}
		b.SendMessage(ctx, &bot.SendMessageParams{
			ChatID: update.Message.Chat.ID,
			Text:   movies,
		})
	case "2":
		if update.Message.Text != "2" {
			movies, err := getMoviesByGenre(update.Message.Text)
			if err != nil {
				b.SendMessage(ctx, &bot.SendMessageParams{
					ChatID: update.Message.Chat.ID,
					Text:   "Error al obtener la cartelera. Por favor, intente nuevamente más tarde.",
				})
				log.Printf("Error getting movies: %v", err)
			}
			b.SendMessage(ctx, &bot.SendMessageParams{
				ChatID: update.Message.Chat.ID,
				Text:   movies,
			})
		} else {
			b.SendMessage(ctx, &bot.SendMessageParams{
				ChatID: update.Message.Chat.ID,
				Text:   "Por favor, ingrese el genero de la pelicula que desea buscar",
			})
		}
	case "3":
		if update.Message.Text != "3" {
			movie, err := getMovieByName(update.Message.Text)
			if err != nil {
				b.SendMessage(ctx, &bot.SendMessageParams{
					ChatID: update.Message.Chat.ID,
					Text:   "Error al obtener la cartelera. Por favor, intente nuevamente más tarde.",
				})
				log.Printf("Error getting movies: %v", err)
			}
			b.SendMessage(ctx, &bot.SendMessageParams{
				ChatID: update.Message.Chat.ID,
				Text:   movie,
			})
		} else {
			b.SendMessage(ctx, &bot.SendMessageParams{
				ChatID: update.Message.Chat.ID,
				Text:   "Por favor, ingrese el titulo de la pelicula que desea buscar",
			})
		}
	case "4":
		delete(userState, update.Message.Chat.ID)
		b.SendMessage(ctx, &bot.SendMessageParams{
			ChatID: update.Message.Chat.ID,
			Text:   "Gracias por usar CineBot!",
		})
	}
}

func getUserState(ctx context.Context, b *bot.Bot, update *models.Update) string {
	if state, exists := userState[update.Message.Chat.ID]; exists {
		if update.Message.Text == "1" || update.Message.Text == "2" || update.Message.Text == "3" || update.Message.Text == "4" {
			userState[update.Message.Chat.ID] = update.Message.Text
		} // if user chooses a number option, change state, else, user is giving input for the option chosen before
		return state
	}
	// Initialize user state to "menu" if it doesn't exist
	b.SendMessage(ctx, &bot.SendMessageParams{
		ChatID: update.Message.Chat.ID,
		Text:   `Hola, bienvenido a CineBot!`,
	})
	userState[update.Message.Chat.ID] = "menu"
	return "menu"
}

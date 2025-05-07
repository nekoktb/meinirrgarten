#encoding: utf-8

require 'io/console'
require_relative '../Directions'
require_relative '../Player'

module UI

  class TextUI


    #https://gist.github.com/acook/4190379
    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!
    
      return input
    end

    

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "\e[A"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "\e[B"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "\e[C"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "\e[D"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            #Error
        end
      end
      output
    end
   

    # Método para mostrar el estado del juego
    def show_game(game_state)
      #system("clear") || system("cls")  # Limpia la pantalla (UNIX o Windows)
      puts
      puts "========== IRRGARTEN =========="
      puts "¿Ganador?: #{game_state.winner}"
      puts "Jugador actual: #{game_state.current_player}"
      puts
    
      puts "------ Laberinto ------"
      puts game_state.labyrinth.to_s  # Asumimos que imprime el tablero o mapa
    
      puts
      puts "------ Jugadores ------"
      puts game_state.players.to_s  # Asumimos que ya es string formateado


      puts
      puts "------ Monstruos ------"
      puts game_state.monsters.to_s  # Asumimos que ya es string formateado
      
    
      puts
      puts "------ Registro ------"
      puts game_state.log

      if game_state.winner
        puts "\n¡Ganador de la partida: Player " + game_state.current_player.to_s + "!"
      end
    
      puts "==============================="
      puts 

    end # show_game

  end # class TextUI


end # module   



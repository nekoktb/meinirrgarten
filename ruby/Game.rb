#encoding: UTF-8

require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'GameState'


require_relative 'Player' 
require_relative 'Labyrinth'
require_relative 'Monster'
require_relative 'FuzzyPlayer'



module Irrgarten

  class Game

    #Atributos de clase
    @@MAX_ROUNDS = 10

    #Atributos de instancia
    def initialize (nplayers)
      @current_player_index = Dice.who_starts(nplayers)
      @log = ""
      @players = []  # Contenedor de jugadores

      nplayers.times do |i|
        player = Player.new((i).to_s, Dice.random_intelligence, Dice.random_strength)
        @players << player
      end

      @current_player = @players[@current_player_index]

      @monsters = [] # Contenedor de monstruos

      configure_labyrinth
      
    end

    #Métodos de instancia públicos
    public

    def finished
      return(@labyrinth.have_a_winner)
    end


    # Método para gestionar el movimiento del jugador
    # Si el jugador no está muerto, se mueve en la dirección preferida (salvo que no pueda)
    #     Si en elsa posicion hay un monstruo, se combate
    #     Si el jugador gana, se le da una recompensa
    # Si el jugador está muerto, se intenta resucitar
    # Entonces se llama a finished para ver si el juego ha terminado
    #     Si el juego ha terminado, se devuelve true
    #     Si el juego no ha terminado, se pasa al siguiente jugador y se devuelve false
    def next_step(preferred_direction)
      # P3
      @log = ""
      if !@current_player.dead()

        direction = actual_direction(preferred_direction)

        if direction != preferred_direction
          log_player_no_orders
        end

        monster = @labyrinth.put_player(direction, @current_player)

        if monster == nil
          log_no_monster
        else
          winner = combat(monster)
          manage_reward(winner)
        end

      else
        manage_resurrection
      end

      end_game = finished

      if !end_game
        next_player
      end

      return(end_game)

    end

    def game_state
      players_s = @players.map(&:to_s).join("\n")
      monsters_s = @monsters.map(&:to_s).join("\n")
      game_state = GameState.new(@labyrinth.to_s,players_s, monsters_s, @current_player_index, finished, @log)
      return(game_state)
    end

    # Métodos de instancia privados

    private

    def configure_labyrinth
      n_rows = 6
      n_cols = 7
      exit_row = 5
      exit_col = 6

      @labyrinth = Labyrinth.new(n_rows, n_cols, exit_row, exit_col)

      base = [
          ['-', 'X', '-', 'M', 'X', 'X', 'X'],
          ['-', 'X', 'M', '-', '-', '-', '-'],
          ['-', 'X', 'X', 'X', '-', 'X', '-'],
          ['M', '-', '-', '-', 'M', '-', '-'],
          ['X', 'X', 'X', 'X', '-', 'X', 'M'],
          ['X', '-', 'M', '-', '-', 'X', 'E']
      ]
      monstruos = ["Bombardino Crocodilo", "Bombardini Guzzini", "Tun Tun Tun Sahur", "Tralalero Tralala"]
      random = Random.new

      base.each_with_index do |row, r|
          row.each_with_index do |cell, c|
              case cell
              when 'X' # Muro
                  @labyrinth.add_block(Orientation::HORIZONTAL, r, c, 1)
              when 'M' # Monstruo
                  monster = Monster.new(monstruos[random.rand(4)], Dice.random_intelligence, Dice.random_strength)
                  @labyrinth.add_monster(r, c, monster)
                  @monsters << monster
              end
          end
      end

      unless @players.empty?
          @labyrinth.spread_players(@players)
      end
    end

    def next_player
      @current_player_index = (@current_player_index + 1) % @players.size
      @current_player = @players[@current_player_index]
    end

    # Método para gestionar el movimiento del jugador:
    # Si el jugador no se puede mover, devuelve la dirección preferida
    # Si el jugador se mueve, devuelve la dirección real
    def actual_direction(preferred_direction)
      #P3
      current_row = @current_player.row
      current_col = @current_player.col
      valid_moves = @labyrinth.valid_moves(current_row, current_col) # Devuelve un array con las direcciones válidas
      output = @current_player.move(preferred_direction, valid_moves)
      return output
    end

    # Método para gestionar el combate entre el jugador y el monstruo
    # Devuelve el ganador del combate
    def combat(monster)
      rounds = 0
      winner = GameCharacter::PLAYER

      player_attack = @current_player.attack
      lose = monster.defend(player_attack)

      while !lose && rounds < @@MAX_ROUNDS do
        rounds += 1
        monster_attack = monster.attack
        lose = @current_player.defend(monster_attack)
        if !lose
          player_attack = @current_player.attack
          winner = GameCharacter::PLAYER
          lose = monster.defend(player_attack)
        else
          winner = GameCharacter::MONSTER
          break
        end
      end

      log_rounds(rounds, @@MAX_ROUNDS)
      return winner
    end

    # Método para gestionar la recompensa del jugador al ganar el combate
    def manage_reward(winner)
      #P3

      if winner == GameCharacter::PLAYER
        @current_player.receive_reward
        log_player_won
      else
        log_monster_won
      end

    end

    # Método para gestionar la resurrección del jugador
    def manage_resurrection
      #P3

      resurrect = Dice.resurrect_player

      if resurrect
        fuzzy_player = FuzzyPlayer.new(@current_player)
        fuzzy_player.resurrect
        @players[@current_player_index] = fuzzy_player
        @current_player = fuzzy_player
        @labyrinth.set_player(@current_player.row, @current_player.col, fuzzy_player)
        log_resurrected
      else
        log_player_skip_turn
      end

    end


    # Métodos de instancia privados para el log:
    def log_player_won
      @log += "Player#{@current_player.number} ha ganado el combate\n"
    end

    def log_monster_won
      @log += "Player#{@current_player.number} ha perdido el combate\n"
    end

    def log_resurrected
      @log += "Player#{@current_player.number} ha resucitado\n"
    end

    def log_player_skip_turn
      @log += "Player#{@current_player.number} sigue muerto, ha perdido su turno\n"
    end

    def log_player_no_orders
      @log += "Player#{@current_player.number} no se ha seguido la orden\n"
    end

    def log_no_monster
      @log += "Player#{@current_player.number} no hay monstruo en la posición o no se ha podido mover\n"
    end

    def log_rounds(rounds, max)
      @log += "Player#{@current_player.number} se han producido #{rounds} de #{max} rondas de combate\n"
    end
  end # class Game
end # module Irrgarten


#encoding : UTF−8
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
        player = Player.new((i+1).to_s, Dice.random_intelligence, Dice.random_strength)
        @players << player
      end

      @current_player = @players[@current_player_index]

      @monsters = [] # Contenedor de monstruos

      @labyrinth = Labirinth.new

      
      



    end

    #Métodos de instancia públicos
    public

    def finished
      return(@labyrinth.have_a_winner)
    end


    def next_step(preferred_direction)
      # P3
      if !current_player.dead()

        direccion = actual_direction(preferred_direction)

        if direction != preferred_direction
          log_player_no_orders
        end

        monster = labyrinth.putPlayer(direction, @current_player)

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

    def get_game_state
      players_s = @players.map(&:to_s).join(", ")
      monsters_s = @monsters.map(&:to_s).join(", ")
      game_state = GameState.new(@labyrinth.to_s,players_s, monsters_s, @current_player_index, finished, @log)
      return(game_state)
    end

    # Métodos de instancia privados

    private

    def configure_labyrinth

    end

    def next_player
      @current_player_index = (@current_player_index + 1) % @players.size
      @current_player = @players[@current_player_index]
    end

    def actual_direction(preferred_direction)
      #P3
    end

    def combat(monster)
      #P3
    end

    def manage_reward(winner)
      #P3
    end

    def manage_resurrection
      #P3
    end

    def log_player_won
      @log += "#{@current_player.name} ha ganado el combate\n"
    end

    def log_monster_won
      @log += "#{@current_player.name} ha perdido el combate\n"
    end

    def log_resurrected
      @log += "#{@current_player.name} ha resucitado\n"
    end

    def log_player_skip_turn
      @log += "#{@current_player.name} sigue muerto, ha perdido su turno\n"
    end

    def log_player_no_orders
      @log += "#{@current_player.name} no se ha seguido la orden\n"
    end

    def log_no_monster
      @log += "#{@current_player.name} no hay monstruo en la posición o no se ha podido mover\n"
    end

    def log_no_rounds(rounds, max)
      @log += "#{@current_player.name} se han producido #{rounds} de #{max} rondas de combate\n"
    end



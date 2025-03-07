# encoding : UTF−8

module Irrgarten

  class GameState

    def initialize(labyrinth, players, monsters, current_player, winner, log)
      #Atributos de instancia:
      @labyrinth = labyrinth;
      @players = players;
      @monsters = monsters;
      @currentPlayer = currentPlayer;
      @winner = winner;
      @log = log;
    end

  #Consultores:
  public
    def labyrinth
      @labyrinth
    end

    def players
      @players
    end

    def monsters
      @monsters
    end
    
    def currentPlayer
      @currentPlayer
    end

    def winner
      @winner
    end

    def log
      @log
    end



  end

end
# encoding: UTF-8

require_relative 'Player' 
require_relative 'Dice'

module Irrgarten
  
  class FuzzyPlayer < Player

    def initialize(other)
      copy(other)
    end

    def move(direction, valid_moves)
      Dice.next_setp(super, valid_moves, intelligence)
    end

    def attack
      self.sum_weapons + Dice.intensity(@strength)
    end

    def to_s
      string = "Fuzzy"
      string += super
      return string
    end

    protected
    def defensive_energy
      return self.sum_shields + Dice.intensity(@intelligence)
    end

  end # class

end # module
#encoding: utf-8

require_relative 'Dice'

module Irrgarten

  class Weapon

    public
  
    def initialize(power, uses)
      @power = power.to_f
      @uses = uses.to_i
    end
  
    def attack
      if @uses > 0
        @uses -= 1
        return @power
      else return 0
      end
    end
  
    def to_s
      "W[#{@power}, #{@uses}]"
    end
  
    def discard
      Dice.discard_element(@uses)
    end
  
  end
    

end 

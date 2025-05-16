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
      produce_effect
    end
  
    def to_s
      string = ("W" + super)
      return string
    end
  
  end
    

end 

#encoding: utf-8

require_relative 'Dice'
require_relative 'CombatElement'

module Irrgarten

  class Weapon < CombatElement

    
    def attack
      self.produce_effect
    end
  
    def to_s
      string = ("W" + super)
      return string
    end
  
  end
    

end 

#encoding: utf-8

require_relative 'Dice'
require_relative 'CombatElement'

module Irrgarten

  class Shield < CombatElement

    public

    def protect
     self.produce_effect
    end
  
    def to_s
      string = ("S" + super)
      return string
    end
    
  
  end # class
  

end # module


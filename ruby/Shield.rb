#encoding: utf-8

require_relative 'Dice'

module Irrgarten

  class Shield < CombatElement

    public
    
    def initialize(protection, uses)
     super(protection, uses)
    end
  
    def protect
     produce_effect
    end
  
    def to_s
      string = ("S" + super)
      return string
    end
    
  
  end # class
  

end # module


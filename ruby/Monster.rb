#encoding: utf-8

require_relative 'Dice'


module Irrgarten


  class Monster < LabyrinthCharacter

    #public_class_method :new  # Clase instanciable pero su superclase es abstracta

    @@INITIAL_HEALTH=5
    
    def initialize(name, intelligence, strength)
      super(name, intelligence, strength, @@INITIAL_HEALTH)
    end
    
    def attack #Devuelve la intensidad del ataque
      Dice.intensity(@strength)
    end
  
    def defend(received_attack) #True si el monstruo muere
      is_dead = dead
      if !is_dead
        defensive_energy = Dice.intensity(@inteligence)
        if defensive_energy < received_attack
          got_wounded
          is_dead = dead
        end
      end
      is_dead
    end
  
    
    
  
  end #class



end #module
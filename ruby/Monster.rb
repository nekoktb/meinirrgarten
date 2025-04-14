#encoding: utf-8

require_relative 'Dice'


module Irrgarten


  class Monster

    @@INITIAL_HEALTH=5
    @@NULL_POS=-1
  
    def initialize (name, inteligence, strength)
      @name = name
      @inteligence = inteligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @row = @@NULL_POS
      @col = @@NULL_POS
    end
  
    
    def dead #True si el monstruo esta muerto
      @health<=0
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
  
    def setPos(row, col) #Establece la posición del monstruo
      @row=row
      @col=col
    end 
  
    def to_s #Devuelve una cadena con la información del monstruo
      "M[#{@name} (HP: #{@health}; SP: #{@strength}; IP: #{@inteligence}); POS:{#{@row},#{@col}}]"
    end  
    
    def got_wounded 
      @health -= 1 
    end
  
    private :got_wounded # metodo de INSTANCIA privado
  
  end #class



end #module
#encoding: utf-8

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
  
    def defend (received_attack)
       
    end #se hace en la P3
  
    def setPos(row, col) #Establece la posición del monstruo
      @row=row
      @col=col
    end 
  
    def to_s #Devuelve una cadena con la información del monstruo
      "M[#{@name} (#{@inteligence}, #{@strength}, #{@health}), {#{@row}, #{@col}}]"
    end  
    
    private :got_wounded # metodo de INSTANCIA privado
    def got_wounded 
      @health--
    end
  
  
  end #class



end #module
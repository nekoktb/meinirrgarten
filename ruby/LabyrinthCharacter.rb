# encoding: UTF-8


module Irrgarten

  # Clase abstracta (NO INSTANCIABLE en ruby, que no tiene abstractas) que representa un personaje del laberinto
  class LabyrinthCharacter

    private_class_method :new  # Clase no instanciable
    
    @@NULL_POS = -1     #Posición inválida

    def initialize(name, intelligence, strength, health)
        @name = name
        @intelligence = intelligence
        @strength = strength
        @health = health

        @row = @@NULL_POS
        @col = @@NULL_POS
    end



  end # class
end # module
# encoding: UTF-8


module Irrgarten

  # Clase abstracta (NO INSTANCIABLE en ruby, que no tiene abstractas) que representa un personaje del laberinto
  class LabyrinthCharacter

    private_class_method :new  # Clase no instanciable
    
    @@NULL_POS = -1     #Posición inválida

    public

    def initialize(name, intelligence, strength, health)
        @name = name
        @intelligence = intelligence
        @strength = strength
        @health = health
        pos(@@NULL_POS, @@NULL_POS) 
    end

    def self.copy(character)
      new(character.name, character.intelligence, character.strength, character.health)
    end
    def copy(other)
      @name = other.name
      @intelligence = other.intelligence
      @strength = other.strength
      @health = other.health
      pos(other.row, other.col)
  end

    def dead
      @health <= 0
    end

    def row 
      @row
    end

    def col
      @col
    end

    def pos(row, col)
      @row = row
      @col = col
    end

    def to_s
      "[#{@name} (HP: #{@health}; SP: #{@strength}; IP: #{@intelligence}); POS:{#{@row},#{@col}}"
    end


    protected

    def inteligence
      @intelligence
    end
    
    def strength
      @strength
    end
    
    def health
      @health
    end

    def health(health)
      @health = health
    end

    def got_wounded
      @health -= 1
    end

    public # METODOS ABSTRACTOS
    def attack
      raise NotImplementedError, "Este es un método abstracto, debe ser implementado y usado por las subclases."
    end
    def defend(received_attack)
      raise NotImplementedError, "Este es un método abstracto, debe ser implementado y usado por las subclases."
    end

  end # class
end # module
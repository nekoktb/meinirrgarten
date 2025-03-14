#encoding : UTF−8
module Irrgarten  

  class PLayer

    @@MAX_WEAPON = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@NULL_POS = -1
    @@HTIS2LOSE = 3
    @@NAME_DEFAULT = "Player#"

    def initialize(number, intelligence, strength)
      @name = @@NAME_DEFAULT + number.to_s
      @intelligence = intelligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @row = @@NULL_POS
      @col = @@NULL_POS
      @consucutive_hits = 0
    end

    def resurrect
      #Listas de armas y escudos vacías
      @health = @@INITIAL_HEALTH
    end

    def row
      @row
    end

    def col
      @col
    end

    def number
      @number
    end

    def pos=row,col
      @row=row
      @col=col
    end

    def dead
      @health<=0
    end
  

end #module Irrgarten
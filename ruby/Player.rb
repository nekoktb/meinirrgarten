#encoding : UTF−8
module Irrgarten  

  class Player

    @@MAX_WEAPON = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@NULL_POS = -1
    @@HTIS2LOSE = 3
    @@NAME_DEFAULT = "Player#"

    def initialize(number, intelligence, strength)
      @number = number
      @name = @@NAME_DEFAULT + number.to_s
      @intelligence = intelligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @row = @@NULL_POS
      @col = @@NULL_POS
      @consucutive_hits = 0
      @weapons = Array.new
      @shields = Array.new
    end

    def resurrect
      @weapons.clear
      @shields.clear
      @health = @@INITIAL_HEALTH
      reset_hits
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

    def move(direction,valid_moves)
      # P3
    end
  
    def attack
      return @strength + sumWeapons
    end

    def defend(received_attack)
      # P3, depende de la implementación de manage_hit
    end

    def receive_reward
      # P3
    end

    def to_string
      "P[#{@name} (HP: #{@health}; SP: #{@strength}; IP: #{@intelligence}); POS:{#{@row},#{@col}}]"
    end


    private  # Métodos privados

    def receive_weapon(w)
      # P3
    end
    
    def receive_shield(s)
      # P3
    end

    def new_weapon
        return Weapon.new(Dice.weapon_power, Dice.uses_left)
    end

    def new_shield
        return Shield.new(Dice.shield_power, Dice.uses_left)
    end

    def sum_weapons
      sum = 0
      @weapons.each do |weapon|
        sum += weapon.attack
      end

      sum
    end

    def sum_shields
      sum = 0
      @shields.each do |shield|
        sum += shield.protect
      end
      sum
    end

    def defensive_energy
      return @intelligence + sum_shields
    end

    def manage_hit(received_attack)
      # P3
    end

    def reset_hits
      @consucutive_hits = 0
    end

    def got_wounded
      @health -= 1
    end

    def inc_consecutive_hits
      @consucutive_hits += 1
    end

end #module Irrgarten
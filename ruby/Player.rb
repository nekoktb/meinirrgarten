#encoding: UTF-8

require_relative 'Dice'
require_relative 'Directions'
require_relative 'Weapon'
require_relative 'Shield'

module Irrgarten  

  class Player

    @@MAX_WEAPON = 2
    @@MAX_SHIELDS = 3
    @@INITIAL_HEALTH = 10
    @@NULL_POS = -1
    @@HTIS2LOSE = 3
    @@NAME_DEFAULT = "Player"

    def initialize(number, intelligence, strength)
      @number = number
      @name = @@NAME_DEFAULT + number.to_s
      @intelligence = intelligence
      @strength = strength
      @health = @@INITIAL_HEALTH
      @row = @@NULL_POS
      @col = @@NULL_POS
      @consecutive_hits = 0
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

    def pos(row, col)
      @row = row
      @col = col
    end

    def dead
      @health<=0
    end

    def move(direction, valid_moves)
      size = valid_moves.size
      contained = valid_moves.include?(direction)
      
      if size > 0 && !contained
        first_element = valid_moves.first
        return first_element
      else
        return direction
      end
    end
  
    def attack
      return @strength + sum_weapons
    end

    def defend(received_attack)
      manage_hit(received_attack)
    end

    def receive_reward
      w_reward = Dice.weapons_reward
      s_reward = Dice.shields_reward

      w_reward.times do
        wnew = new_weapon
        receive_weapon(wnew)
      end

      s_reward.times do
        snew = new_shield
        receive_shield(snew)
      end

      extra_health = Dice.health_reward
      @health += extra_health
    end

    def to_s
      "[" + @name + ": (HP: " + @health.to_s + 
      "; Base_SP: " + @strength.to_s + " + Weapons_SP: " + sum_weapons.to_s +
      "; IP: " + @intelligence.to_s + " + Shield:" + sum_shields.to_s +
      "); POS:{" + @row.to_s + "," + @col.to_s + "}]"
    end


    private  # Métodos privados

    def receive_weapon(w)
      @weapons.delete_if { |wi| wi.discard }
      
      if @weapons.size < @@MAX_WEAPON
        @weapons.push(w)
      end
    end
    
    def receive_shield(s)
      @shields.delete_if { |si| si.discard }

      if @shields.size < @@MAX_SHIELDS
        @shields.push(s)
      end
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
      defense = defensive_energy
      if defense < received_attack
        got_wounded
        inc_consecutive_hits
      else
        reset_hits
      end

      if @consecutive_hits == @@HTIS2LOSE || dead
        reset_hits
        lose = true
      else
        lose = false
      end

      lose
    end

    def reset_hits
      @consecutive_hits = 0
    end

    def got_wounded
      @health -= 1
    end

    def inc_consecutive_hits
      @consecutive_hits += 1
    end

  end #class Player

end #module Irrgarten
# encoding : UTF−8

class Shield

  def initialize(protection, uses)
    @protection = protection.tp_f
    @uses = uses.to_i
  end

  def attack
    if @uses > 0
      @uses -= 1
      return @@protection
    else return 0
    end
  end

  def to_s
    "W[#{@protection}, #{@uses}]"
  end
  
  def discard
    Dice.discard_element(@uses)
  end
  
end


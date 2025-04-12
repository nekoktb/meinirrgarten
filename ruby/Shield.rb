#encoding: utf-8

require_relative 'Dice'

module Irrgarten

  class Shield

    public
    
    def initialize(protection, uses)
      @protection = protection.tp_f
      @uses = uses.to_i
    end
  
    def protect
      if @uses > 0
        @uses -= 1
        return @@protection
      else return 0
      end
    end
  
    def to_s
      "S[#{@protection}, #{@uses}]"
    end
    
    def discard
      Dice.discard_element(@uses)
    end
  
  end
  

end


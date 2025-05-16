# encoding: UTF-8

require_relative 'Dice'

module Irrgarten

  class CombatElement

    public

    def initialize (effect, uses) 
      @effect = effect
      @uses = uses
    end

    def discard
      Dice.discard_element(@uses)
    end

    def to_s
      "[#{@effect}, #{@uses}]"
    end

    protected
    def produce_effect
      if @uses > 0
        @uses -= 1
        return @effect
      else return 0
      end
    end

  end #class

end #module
 

#encoding: utf-8

require_relative 'Game'
require_relative 'textUI'
require_relative 'controller'



game = Irrgarten::Game.new(1)


ui = UI::TextUI.new
controller = Control::Controller.new(game, ui)


controller.play

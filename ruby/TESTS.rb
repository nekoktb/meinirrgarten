#encoding: utf-8

require_relative 'Game'
require_relative 'UI/textUI'
require_relative 'Controller/controller'



game = Irrgarten::Game.new(1)


ui = UI::TextUI.new
controller = Control::Controller.new(game, ui)


controller.play

#encoding : UTF−8
module Irrgarten

  class Labirinth
    
    #Atributos de clase
    @@BLOCK_CHAR = '#'
    @@EMPTY_CHAR = '-'
    @@MONSTER_CHAR = 'M'
    @@COMBAT_CHAR = 'C'
    @@EXIT_CHAR = 'E'
    @@ROW = 0
    @@COL = 1


    def initialize (rows, cols, exit_row, exit_col)
      #Atributos de instancia
      @n_rows = rows
      @n_cols = cols
      @exit_row = exit_row
      @exit_col = exit_col

      @monsters = Array.new(rows) { Array.new(cols) { Monster.new } }
      @players = Array.new(rows) { Array.new(cols) { Player.new } }
      @labyrinth = Array.new(rows) { Array.new(cols) { @@EMPTY_CHAR } }
    
    end


    #Métodos de instancia públicos
    public
    def spread_players (players)
      # P3
    end

    def have_a_winner
      (@players[@exit_row][@exit_col] != nil)

    end

    def to_s
      
    end

    def add_moster (row, col, monster)
      
    end 

    def put_player (direction, player)
      # P3
    end

    def add_block (orientation, start_row, start_col, length)
      # P3
    end

    def valid_moves (row, col)
      # P3
    end


   #Métodos de instancia privados
    private 

    def pos_ok (row, col)
      row >= 0 && row < @n_rows && col >= 0 && col < @n_cols
    end

    def empty_pos (row, col)
      @labyrinth[row][col] == @@EMPTY_CHAR
    end

    def mosnter_pos (row, col)
      @labyrinth[row][col] == @@MONSTER_CHAR
    end

    def exit_pos (row, col)
      row == @exit_row && col == @exit_col
    end

    def combat_pos (row, col)
      @labyrinth[row][col] == @@COMBAT_CHAR
    end

    def can_step_on (row, col)
      pos_ok(row, col) && (empty_pos(row, col) || exit_pos(row, col) || combat_pos(row, col))
    end

    def update_old_pos (row, col)

    end

    def dir_2_pos (row, col, direction)
      
    end

    def random_empty_pos 

    end 

    def put_player_2D (old_row, old_col, row, col, player)

    end
    


  end # class Labirinth

end # module Irrgarten
#encoding: UTF-8

require_relative 'Orientation'
require_relative 'Directions'
require_relative 'Dice'
require_relative 'Player'
require_relative 'Monster'


module Irrgarten

  class Labyrinth
    
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

      @monsters = Array.new(rows) { Array.new(cols) { nil } }
      @players = Array.new(rows) { Array.new(cols) { nil } }
      @labyrinth = Array.new(rows) { Array.new(cols) { @@EMPTY_CHAR } }
    
    end


    #Métodos de instancia públicos
    public
    def spread_players (players)
      players.each do |p|
        pos = random_empty_pos
        put_player_2D(-1, -1, pos[@@ROW], pos[@@COL], p)
      end
    end

    # Devuelve true si hay un jugador en la casilla de salida, false en caso contrario.
    def have_a_winner
      (@players[@exit_row][@exit_col] != nil)

    end

    # Devuelve una representación del laberinto como una cadena de texto.
    def to_s
      str = ""
      @labyrinth.each do |row|
        row.each do |col|
          str += col.to_s
        end
        str += "\n"
      end
      str += "Exit: (#{@exit_row}, #{@exit_col})\n"
      return str
    end

    # Si la posición suministrada está dentro del tablero y está vacía, 
    # anota en el laberinto la presencia de un monstruo, guarda la referencia del monstruo en el
    # atributo contenedor adecuado e indica al monstruo cual es su posición actual (setPos).
    def add_monster (row, col, monster)
      if pos_ok(row, col) && empty_pos(row, col)
        @labyrinth[row][col] = @@MONSTER_CHAR
        @monsters[row][col] = monster
        monster.setPos(row, col)
      end
    end 


    # Este método recibe un jugador y una dirección y coloca al jugador en la posición siguiente
    # al jugador en la dirección indicada.
    # Si la posición siguiente está vacía, se devuelve nil.
    # Si la posición siguiente está ocupada por un monstruo, se devuelve el monstruo.
    def put_player (direction, player)
      # P3
      old_row = player.row
      old_col = player.col
      new_pos = dir_2_pos(old_row, old_col, direction)
      monster = put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
      return monster
    end


    # Este método añade un bloque de longitud length en la dirección indicada por el parámetro orientation.
    # La posición inicial del bloque es (start_row, start_col) y el bloque se añade en la dirección
    # indicada por el parámetro orientation.
    def add_block (orientation, start_row, start_col, length)
      # P3

      if orientation == Orientation::VERTICAL
        inc_row = 1
        inc_col = 0
      else
        inc_row = 0
        inc_col = 1
      end
      row = start_row
      col = start_col

      while ( (pos_ok(row, col) && empty_pos(row, col)) && (length > 0) ) do
        @labyrinth[row][col]  #en el diagrama se usa el metodo set, pero no existe
        length -= 1
        row += inc_row
        col += inc_col
      end


    end

    def valid_moves(row, col)
      output = []
      
      if can_step_on(row - 1, col)
        output.push(:up)
      end
      
      if can_step_on(row + 1, col)
        output.push(:down)
      end
      
      if can_step_on(row, col - 1)
        output.push(:left)
      end
      
      if can_step_on(row, col + 1)
        output.push(:right)
      end
      
      output
    end


   #Métodos de instancia privados
    private 

    def pos_ok (row, col)
      row >= 0 && row < @n_rows && col >= 0 && col < @n_cols
    end

    def empty_pos (row, col)
      @labyrinth[row][col] == @@EMPTY_CHAR
    end

    def monster_pos (row, col)
      @labyrinth[row][col] == @@MONSTER_CHAR
    end

    def exit_pos (row, col)
      row == @exit_row && col == @exit_col
    end

    def combat_pos (row, col)
      @labyrinth[row][col] == @@COMBAT_CHAR
    end

    # Indica si la posición suministrada está dentro del laberinto y se
    # corresponde con una de estas tres opciones: casilla vacía, casilla donde habita un monstruo o salida
    # (posOK, emptyPos, monsterPos, exitPos)
    def can_step_on (row, col)
      pos_ok(row, col) && (empty_pos(row, col) || monsterPos(row, col) || exitPos(row, col))
    end

    # Este método solo realiza su función si la posición suministrada está dentro del laberinto. 
    # Si es el caso, si en esa posición el laberinto estaba indicando el estado de combate, 
    # el estado de esa casilla del laberinto pasa a indicar que simplemente hay un monstruo. 
    # En otro caso, el estado de esa casilla del laberinto pasa a indicar que está vacía. 
    # Este método es llamado cuando un jugador abandona una casilla y se encarga de dejar la casilla que se abandona en el estado correcto.
    def update_old_pos (row, col)
      if pos_ok(row, col)
        if combat_pos(row, col)
          @labyrinth[row][col] = @@MONSTER_CHAR
        else
          @labyrinth[row][col] = @@EMPTY_CHAR
        end
      end
    end

    # Este método calcula la posición del laberinto a la que se llegaría si desde la posición 
    # suministrada se avanza en la dirección pasada como parámetro. 
    # No es necesario realizar comprobaciones relativas a no generar posiciones fuera del laberinto.
    def dir_2_pos (row, col, direction)
      case direction
      when :up
        row -= 1
      when :down
        row += 1
      when :left
        col -= 1
      when :right
        col += 1
      end
      return [row, col]
    end

    # Utilizando el dado, genera una posición aleatoria en el laberinto (fila y columna) 
    # asegurando que esta esté vacía. Genera internamente posiciones hasta que se cumple esta restricción 
    # y una vez generada se devuelve. Si no hay posiciones vacías se producirá un bucle infinito.
    def random_empty_pos
      row = Dice.random_pos(@n_rows)
      col = Dice.random_pos(@n_cols)
      while (!empty_pos(row, col))
        row = Dice.random_pos(@n_rows)
        col = Dice.random_pos(@n_cols)
      end
      return [row, col]
    end


    # Este método recibe la posicion actual (old_row, old_col) de un jugador (player) y la nueva posición (row, col)
    # a la que se quiere mover. 
    # Si la nueva posición puede ser ocupada por el jugador
    #     y la posición anterior es válida
    #         se actualiza la antigua posicion del jugador (si es el que estaba en la posición).
    #     y en la nueva posición hay un monstruo
    #         se entra en combate y se devuelve el monstruo
    #     y en la nueva posición NO hay monstruo
    #         se actualiza el laberinto con el número del jugador
    #     Finalmente se actualiza la posición del jugador
    #     y se devuelve el monstruo (si lo había) (o nil si no lo había)
    def put_player_2D (old_row, old_col, row, col, player)
      # P3

      output = nil

      if can_step_on(row, col)
        if pos_ok(old_row, old_col)
          p = @players[old_row][old_col] # NO SIRVE @players.get(old_row, old_col) no existe el get de los arrays en ruby
          if p == player
            update_old_pos(old_row, old_col)
            @players[row][col] = nil  # NO SIRVE @players.set(row, col, nil) no existe el set de los arrays en ruby
          end
        end
      
        if monster_pos(row, col)
          @labyrinth[row][col] = @@COMBAT_CHAR
          output = @monsters[row][col]
        else
          number = player.number
          @labyrinth[row][col] = number.to_s
        end

        @players[row][col] = player
        player.pos(row, col)

      end

      return output

    end # put_player_2D
    


  end # class Labirinth

end # module Irrgarten
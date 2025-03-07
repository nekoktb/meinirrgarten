# encoding : UTF−8
class Dice 
  
# Atributos de clase:

  @@MAX_USES          = 5     # número máximo de usos de armas y escudos
  @@MAX_INTELLIGENCE  = 10.0  # valor máximo para la inteligencia de jugadores y monstruos
  @@MAX_STRENGTH      = 10.0  # valor máximo para la fuerza de jugadores y monstruos
  @@RESURRECT_PROB    = 0.3   # probabilidad de que un jugador sea resucitado en cada turno
  @@WEAPONS_REWARD    = 2     # numero máximo de armas recibidas al ganar un combate
  @@SHIELDS_REWARD    = 3     # numero máximo de escudos recibidos al ganar un combate
  @@HEALTH_REWARD     = 5     # numero máximo de unidades de salud recibidas al ganar un combate
  @@MAX_ATTACK        = 3     # máxima potencia de las armas
  @@MAX_SHIELD        = 2     # máxima potencia de los escudos
  
  @@generator = Random.new

# Métodos de clase públicos:
  public

  # Devuelve un número aleatorio de fila o columna en el tablero [0, max[
  def self.random_pos(max)
    @@generator.rand(max)
  end

  # Devuelve el índice del jugador que comenzará la partida [0, nplayers[
  def self.who_starts(nplayers)
    @@generator.rand(nplayers)
  end

  # Devuelve un valor aleatorio de inteligencia en el intervalo [0, MAX_INTELLIGENCE[
  def self.random_intelligence
    @@generator.rand(@@MAX_INTELLIGENCE.to_f)
  end

  # Devuelve un valor aleatorio de fuerza en el intervalo [0, MAX_STRENGTH[
  def self.random_strength
    @@generator.rand(@@MAX_STRENGTH.to_f)
  end

  # Indica si un jugador muerto debe ser resucitado
  def self.resurrect_player
    @@generator.rand < @@RESURRECT_PROB
  end

  # Devuelve la cantidad de armas que recibirá el jugador por ganar el combate
  # Valor entero en el intervalo cerrado [0, WEAPONS_REWARD]
  def self.weapons_reward
    @@generator.rand(@@WEAPONS_REWARD + 1)
  end

  # Devuelve la cantidad de escudos que recibirá el jugador por ganar el combate
  # Valor entero en el intervalo cerrado [0, SHIELDS_REWARD]
  def self.shields_reward
    @@generator.rand(@@SHIELDS_REWARD + 1)
  end

  # Devuelve la cantidad de unidades de salud que recibirá el jugador por ganar el combate
  # Valor entero en el intervalo cerrado [0, HEALTH_REWARD]
  def self.health_reward
    @@generator.rand(@@HEALTH_REWARD + 1)
  end

  # Devuelve un valor aleatorio de potencia para un arma en el intervalo [0, MAX_ATTACK[
  def self.weapon_power
    @@generator.rand(@@MAX_ATTACK.to_f)
  end

  # Devuelve un valor aleatorio de potencia para un escudo en el intervalo [0, MAX_SHIELD[
  def self.shield_power
    @@generator.rand(@@MAX_SHIELD.to_f)
  end

  # Devuelve el número de usos que se asignará a un arma o escudo
  # Valor entero en el intervalo cerrado [0, MAX_USES]
  def self.uses_left
    @@generator.rand(@@MAX_USES + 1)
  end

  # Devuelve una cantidad de competencia aplicada en el intervalo [0, competence[
  def self.intensity(competence)
    @@generator.rand(competence.to_f)
  end

  # Devuelve true o false en función de la probabilidad inversamente proporcional a los usos restantes.
  # Casos extremos: si uses_left es 0 devuelve true, si es igual al máximo devuelve false.
  def self.discard_element(uses_left)
    @@generator.rand(1.0) >= (uses_left.to_f/@@MAX_USES);
  end


end


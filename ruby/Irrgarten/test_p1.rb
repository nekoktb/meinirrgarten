# encoding : UTF−8

class TestP1

  def main

    # Crear instancias de las clases y utilizar sus métodos
    dice = Dice.new
    100.times do
      puts dice.roll
    end

    # Suponiendo que hay otras clases como Class1, Class2, etc.
    class1_instance = Class1.new
    class1_instance.method1
    class1_instance.method2

    class2_instance = Class2.new
    class2_instance.method1
    class2_instance.method2

    # Utilizar valores de tipos enumerados
    enum_value = EnumType::VALUE1
    puts enum_value

    # Probar la clase Dice
    dice = Dice.new
    results = Hash.new(0)
    100.times do
      result = dice.roll
      results[result] += 1
    end

    results.each do |key, value|
      puts "Result #{key} occurred #{value} times"
    end
    

  end

end

#probar main:
TestP1.main
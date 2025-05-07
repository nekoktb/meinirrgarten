package irrgarten;

abstract class CombatElement {
    
    // Efecto del elemento de combate
    private float effect;
    
    //Número de usos disponibles del elemento de combate.
    private int uses;
    
    /**
     * Constructor de la clase abstracta CombatElement
     * @param effect Valor numérico del efecto del elemento de combate
     * @param uses El número de usos disponibles del elemento de combate
     */
    public CombatElement (float effect, int uses){
        this.effect=effect;
        this.uses=uses;
    }
    
    /**
     * Método para realizar el efecto correspondiente al elemento de combate,
     * se reduce en uno su uso.
     * @return La intensidad del efecto.
     */
    protected float produceEffect(){
        if(uses>0){
            uses--;
            return effect;
        }
        else
            return 0;
    }
    
    
    //Método que indica si se descartará el elemento segun los usos restantes.
    public boolean discard(){
        return Dice.discardElement(this.uses);
    }
    
    @Override
    public String toString(){
        return "[" + effect + ", " + uses + "]";
    }
}
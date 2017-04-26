package uqac.gestionvieetu.Budget;

import java.io.Serializable;


public class Recette implements Serializable {
    private double recette;
    private String type;

    Recette(double d,String type){
        recette=d;
        this.type=type;
    }

    @Override
    public String toString() {
        return "\t\t - Montant de " + recette + " \t\t\ttype : " + type;
    }

    public double getRecette(){
        return recette;
    }
}

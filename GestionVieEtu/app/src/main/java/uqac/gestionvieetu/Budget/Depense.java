package uqac.gestionvieetu.Budget;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Depense implements Serializable {
    private double depense;
    private String type;

    Depense(double d,String type){
        depense = d;
        this.type = type;
    }

    @Override
    public String toString() {
        return "\t\t - Montant de " + depense + "\t\t\ttype : " + type;
    }

    public double getDepense(){
        return depense;
    }

}

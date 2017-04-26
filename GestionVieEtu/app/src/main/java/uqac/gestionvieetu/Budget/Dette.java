package uqac.gestionvieetu.Budget;

import java.io.Serializable;


public class Dette implements Serializable {
    private double dette;
    private String type,nom,prenom;

    Dette(double d,String type,String nom,String prenom){
        dette=d;
        this.type=type;
        this.nom=nom;
        this.prenom=prenom;
    }

    @Override
    public String toString() {
        return "\t\t - Montant de " + dette + " \t\t\ttype : " + type + " \t\t\tdu par "+nom +" "+prenom;
    }
}

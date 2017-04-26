package uqac.gestionvieetu.Budget;

import java.io.Serializable;


public class Emprunt implements Serializable {
    private double emprunt;
    private String type,nom,prenom;

    Emprunt(double d,String type,String nom,String prenom){
        emprunt=d;
        this.type=type;
        this.nom=nom;
        this.prenom=prenom;
    }

    @Override
    public String toString() {
        return "\t\t - Montant de " + emprunt + " \t\t\ttype : " + type + "\t\t\temprunté à "+nom +" "+prenom;
    }
}

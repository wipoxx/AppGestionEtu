package uqac.gestionvieetu.Budget;

/**
 * Created by Fiolyne on 25/04/2017.
 */

public class Budget {
    private double montant;
    private double solde;

    public Budget(){
        this.montant = 0;
        this.solde = 0;
    }

    public Budget(double montant){
        super();
        this.montant = montant;
        this.solde = montant;
    }

    public Budget(double montant, double solde){
        super();
        this.montant = montant;
        this.solde = solde;
    }

    public void setMontant(double montant){this.montant = montant;}
    public void setSolde(double solde){this.solde = solde;}

    public double getMontant(){return montant;}
    public double getSolde(){return solde;}

    public String toString(){
        return "Buget montant = " + montant + ", solde = " + solde;
    }
}

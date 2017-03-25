package uqac.gestionvieetu;

import java.util.Date;

public class TableBudget {
    private int id;
    private float montant;
    private float solde;
    private Date mois;
    private Date annee;

    public TableBudget(){}

    public TableBudget(float montant, float solde, Date mois, Date annee){
        super();
        this.montant = montant;
        this.solde = solde;
        this.mois = mois;
        this.annee = annee;
    }

    public void setMontant(float montant){this.montant = montant;}
    public void setSolde(float solde){this.solde = solde;}
    public void setMois(Date mois){this.mois = mois;}
    public void setAnnee(Date annee){this.annee = annee;}

    public float getMontant(){return montant;}
    public float getSolde(){return solde;}
    public Date getMois(){return mois;}
    public Date getAnnee(){return annee;}

    @Override
    public String toString(){
        return "Buget id = " + id + ", montant = " + montant + ", solde = " + solde +
                ", mois = " + mois + ", annee = " + annee;
    }

}

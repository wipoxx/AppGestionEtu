package uqac.gestionvieetu.Budget;

import java.util.Date;

//**************** Classe non utilisée **************************//

public class TableBudget {
    private int id;
    private float montant;
    private float solde;
    private String mois;
    private String annee;

    public TableBudget(){}

    public TableBudget(float montant, float solde, String mois, String annee){
        super();
        this.montant = montant;
        this.solde = solde;
        this.mois = mois;
        this.annee = annee;
    }

    public void setId(int id){this.id = id;}
    public void setMontant(float montant){this.montant = montant;}
    public void setSolde(float solde){this.solde = solde;}
    public void setMois(String mois){this.mois = mois;}
    public void setAnnee(String annee){this.annee = annee;}

    public int getId(){return id;}
    public float getMontant(){return montant;}
    public float getSolde(){return solde;}
    public String getMois(){return mois;}
    public String getAnnee(){return annee;}

    @Override
    public String toString(){
        return "Buget id = " + id + ", montant = " + montant + ", solde = " + solde +
                ", mois = " + mois + ", annee = " + annee;
    }

}

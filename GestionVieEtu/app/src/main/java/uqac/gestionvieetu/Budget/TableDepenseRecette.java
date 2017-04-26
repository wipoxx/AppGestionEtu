package uqac.gestionvieetu.Budget;

import java.util.Date;

public class TableDepenseRecette {
    private int id;
    private float montant;
    private String type;
    private String date;
    private int depense;

    public TableDepenseRecette(){}

    public TableDepenseRecette(float montant, String type, String date, int depense){
        super();
        this.montant = montant;
        this.type = type;
        this.date = date;
        this.depense = depense;
    }

    public void setMontant(float montant){this.montant = montant;}
    public void setType(String type){this.type = type;}
    public void setDate(String date){this.date = date;}
    public void setId(int id){this.id = id;}
    public void setDepense(int depense){this.depense = depense;}

    public float getMontant(){return montant;}
    public String getType(){return type;}
    public String getDate(){return date;}
    public int getId(){return id;}
    public int getDepense(){return depense;}

    @Override
    public String toString(){
        if(depense == 0){
            return "Depense id = " + id + ", montant = " + montant + ", type = " + type +
                    ", date = " + date;
        }
        else return "Recette id = " + id + ", montant = " + montant + ", type = " + type +
                ", date = " + date;
    }
}

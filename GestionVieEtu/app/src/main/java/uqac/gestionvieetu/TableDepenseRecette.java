package uqac.gestionvieetu;

import java.util.Date;

public class TableDepenseRecette {
    private int id;
    private float montant;
    private String type;
    private Date date;
    private boolean depense;

    public TableDepenseRecette(){}

    public TableDepenseRecette(float montant, String type, Date date, boolean depense){
        super();
        this.montant = montant;
        this.type = type;
        this.date = date;
        this.depense = depense;
    }

    public void setMontant(float montant){this.montant = montant;}
    public void setType(String type){this.type = type;}
    public void setDate(Date date){this.date = date;}
    public void setId(int id){this.id = id;}
    public void setDepense(boolean depense){this.depense = depense;}

    public float getMontant(){return montant;}
    public String getType(){return type;}
    public Date getDate(){return date;}
    public int getId(){return id;}
    public boolean getDepense(){return depense;}

    @Override
    public String toString(){
        if(depense){
            return "Depense id = " + id + ", montant = " + montant + ", type = " + type +
                    ", date = " + date;
        }
        else return "Recette id = " + id + ", montant = " + montant + ", type = " + type +
                ", date = " + date;
    }
}

package uqac.gestionvieetu.Budget;

import java.util.Date;

//**************** Classe non utilisée **************************//

public class TableDetteEmprunt {
    private int id;
    private float montant;
    private String type;
    private Date date;
    private boolean dette;
    private String prenom;
    private String nom;

    public TableDetteEmprunt(){}

    public TableDetteEmprunt(float montant, String type, Date date, String nom, String prenom, boolean dette){
        super();
        this.montant = montant;
        this.type = type;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.dette = dette;
    }

    public void setMontant(float montant){this.montant = montant;}
    public void setType(String type){this.type = type;}
    public void setDate(Date date){this.date = date;}
    public void setId(int id){this.id = id;}
    public void setNom(String nom){this.nom = nom;}
    public void setPrenom(String prenom){this.prenom = prenom;}
    public void setDette(boolean dette){this.dette = dette;}

    public float getMontant(){return montant;}
    public String getType(){return type;}
    public Date getDate(){return date;}
    public int getId(){return id;}
    public String getNom(){return nom;}
    public String getPrenom(){return prenom;}
    public boolean getDette(){return dette;}

    @Override
    public String toString(){
        if(dette){
            return "Dette de : nom = " + nom + ", prenom = " + prenom + ", id = " + id +
                    ", montant = " + montant + ", type = " + type + ", date = " + date;
        }
        else return "Emprunt a : nom = " + nom + ", prenom = " + prenom + ", id = " + id +
                ", montant = " + montant + ", type = " + type + ", date = " + date;
    }
}

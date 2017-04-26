package uqac.gestionvieetu.Budget;

import java.io.Serializable;
import java.util.ArrayList;

public class Budget implements Serializable{
    private double montant;
    private double solde;
    private ArrayList<Depense> depense;
    private ArrayList<Recette> recette;
    private ArrayList<Emprunt> emp;
    private ArrayList<Dette> dette;

    public Budget(){
        this.montant = 0;
        this.solde = 0;

        depense = new ArrayList<Depense>();
        recette = new ArrayList<Recette>();

        emp = new ArrayList<Emprunt>();
        dette = new ArrayList<Dette>();

    }

    public void setMontant(double montant){this.montant = montant;}
    public void setSolde(double solde){this.solde = solde - totalDepense() + totalRecette();}

    public double getMontant(){return montant;}
    public double getSolde(){return solde;}

    public void addDep(Double dep,String type){
        solde-=dep;
        depense.add(new Depense(dep,type));
    }

    public void addrec(Double rec,String type){
        solde+=rec;
        recette.add(new Recette(rec,type));
    }

    public void addEmp(Double empr,String type,String nom,String prenom){
        emp.add(new Emprunt(empr,type,nom,prenom));
    }

    public void addDette(Double dette,String type,String nom,String prenom){
        this.dette.add(new Dette(dette,type,nom,prenom));
    }

    double totalDepense(){
        double somme=0;
        for(Depense d:depense){
            somme+=d.getDepense();
        }

        return somme;
    }

    double totalRecette(){
        double somme=0;
        for(Recette r:recette){
            somme+=r.getRecette();
        }

        return somme;
    }



    public String toString(){
        String s="";
        s+="Dépense :\n";
        for (Depense d:depense) {
            s+=d.toString()+"\n";
        }
        s+="\n\nRecette :\n";
        for (Recette r:recette) {
            s+=r.toString()+"\n";
        }
        s+="\n\nEmprunt :\n";
        for (Emprunt e:emp) {
            s+=e.toString()+"\n";
        }
        s+="\n\nDette :\n";
        for (Dette d:dette) {
            s+=d.toString()+"\n";
        }
        return s;
    }
}

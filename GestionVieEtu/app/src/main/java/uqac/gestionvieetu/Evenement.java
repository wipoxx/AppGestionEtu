package uqac.gestionvieetu;

public class Evenement {
    private String titre;
    private String description;
    private String debut;
    private String fin;

    public void setTitre(String title) {
        this.titre = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDebut(String begin) {
        this.debut = begin;
    }

    public void setFin(String end) {
        this.fin = end;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "Titre : " +titre+ " ; Descritpion : " +description+ " ; " +debut+ " -> " +fin;
    }
}

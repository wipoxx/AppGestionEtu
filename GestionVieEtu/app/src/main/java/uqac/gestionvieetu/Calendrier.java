package uqac.gestionvieetu;

public class Calendrier {
    private String id;
    private String nom;
    private boolean visible;

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "Id : " +id+ " ; Nom : " +nom+ " ; visible ? " + (visible ? "oui" : "non");
    }
}

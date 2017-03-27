package uqac.gestionvieetu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import uqac.gestionvieetu.Etudes.AgendaFragment;
import uqac.gestionvieetu.Etudes.AjoutHoraireFragment;
import uqac.gestionvieetu.Etudes.EtudesFragment;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendrier; //Le calendrier commun de l'appli

    //Variables pour afficher l'heure choisie par l'utilisateur dans un bouton
    private String moment;
    private View bHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public CalendarView getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(CalendarView calendrier) {
        this.calendrier = calendrier;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Lors d'un clic sur le bouton Etudes dans la première fenêtre ; Affiche le layout études
    public void afficherLayoutEtudes(View view) {
        this.changerLayout(new EtudesFragment());
    }

    //Lors d'un clic sur le bouton Gérer EDT dans les Etudes ; Affiche le layout agenda
    public void afficherBoutonsAgenda(View view) {
        this.changerLayout(new AgendaFragment());

    }

    //Lors d'un clic sur le bouton Budget dans la première fenêtre
    public void afficherLayoutBudget(View view) {
        this.changerLayout(new BudgetFragment());
    }

    //Lors d'un clic sur le bouton Ajouter horaire ; affiche layout Ajout horaire
    public void ajoutHoraire(View view) {
        this.changerLayout(new AjoutHoraireFragment());
        //getMenuInflater().inflate(R.menu.menu_main, getMen);

    }

    //Permet de mettre le layout contenu dans le fragment en entrée à la place de celui du fragment présent dans activity_main.xml
    private void changerLayout(Fragment fragment) {
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();

        fragTrans.replace(R.id.fragment_main, fragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }

    //Affiche le dialogue pour choisir heure+minute
    public void afficherTimePicker(View view) {
        TimePickerFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "timePicker");
        bHeure = view;
    }

    //Met l'heure choisie par l'utilisateur dans le texte du bouton
    public void setMoment(String moment) {
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.fragment_main);

        if (currentFragment instanceof AjoutHoraireFragment) {
            ((AjoutHoraireFragment) currentFragment).setMoment(moment, bHeure);
            this.findViewById(R.id.fragment_main).invalidate();
        }
        this.moment = moment;
    }
}

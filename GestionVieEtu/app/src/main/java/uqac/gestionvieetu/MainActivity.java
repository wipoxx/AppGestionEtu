package uqac.gestionvieetu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;

import uqac.gestionvieetu.Etudes.EdtFragment;
import uqac.gestionvieetu.Etudes.AjoutHoraireFragment;
import uqac.gestionvieetu.Etudes.EtudesFragment;
import uqac.gestionvieetu.Etudes.RootEtudesFragment;
import uqac.gestionvieetu.Sorties.AjoutSortieFragment;
import uqac.gestionvieetu.Sorties.SortiesFragment;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendrier; //Le calendrier commun de l'appli

    //Variables pour afficher l'heure choisie par l'utilisateur dans un bouton
    private String moment;
    private String dateSelectionnee;
    private View bHeure;
    private View bDate;

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
    public void afficherLayoutEdt(View view) {
        this.changerLayout(new EdtFragment(), R.id.sous_etudes_fragment);

    }

    public void afficherLayoutSorties(View view) {
        this.changerLayout(new SortiesFragment());
    }

    //Lors d'un clic sur le bouton Budget dans la première fenêtre
    public void afficherLayoutBudget(View view) {
        this.changerLayout(new BudgetFragment());
    }

    //Lors d'un clic sur le bouton Ajouter horaire ; affiche layout Ajout horaire
    public void ajoutHoraire(View view) {
        Fragment fragment = new AjoutHoraireFragment();
        this.changerLayout(fragment);
    }

    public void ajoutSortie(View view) {
        this.changerLayout(new AjoutSortieFragment());
    }

    //Place le fragment en entrée dans le main_fragment (et donc change la vue à afficher)
    public void changerLayout(Fragment fragment) {
        changerLayout(fragment, R.id.main_fragment);
    }

    //Place le fragment en entrée dans le fragment en entrée (utile pour remplacer le fragment dans le layout_etudes
    public void changerLayout(Fragment fragment, int idFragment) {
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();

        fragTrans.replace(idFragment, fragment);
        fragTrans.addToBackStack(fragment.getClass().getName());
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
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.main_fragment);

        if (currentFragment instanceof AjoutHoraireFragment) {
            ((AjoutHoraireFragment) currentFragment).setMoment(moment, bHeure);
            this.findViewById(R.id.main_fragment).invalidate();
        } else if (currentFragment instanceof AjoutSortieFragment) {
            ((AjoutSortieFragment) currentFragment).setMoment(moment, bHeure);
            this.findViewById(R.id.main_fragment).invalidate();
        }
        this.moment = moment;
    }

    public void afficherDatePicker(View view) {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "timePicker");
        bDate = view;
    }

    //Pour permettre de retourner à l'affichage précédent dans la partie Etudes
    //Sinon bug à cause des différents fragments
    @Override
    public void onBackPressed() {
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.main_fragment);

        if (currentFragment instanceof AjoutHoraireFragment) {
            this.changerLayout(new EtudesFragment());
            this.changerLayout(new EdtFragment(), R.id.sous_etudes_fragment);
        }
        else if (currentFragment instanceof EtudesFragment) {
            Fragment currentFragment2 = this.getSupportFragmentManager().findFragmentById(R.id.sous_etudes_fragment);
            if(currentFragment2 instanceof EdtFragment) {
                this.changerLayout(new RootEtudesFragment(), R.id.sous_etudes_fragment);
            } else {
                this.changerLayout(new MainFragment());
            }
        }
        else {
            super.onBackPressed();
        }
    }
}

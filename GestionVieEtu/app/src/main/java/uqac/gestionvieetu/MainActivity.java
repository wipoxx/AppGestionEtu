package uqac.gestionvieetu;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import uqac.gestionvieetu.Budget.AjoutBudgetFragment;
import uqac.gestionvieetu.Budget.BudgetFragment;
import uqac.gestionvieetu.Budget.DepenseFragment;
import uqac.gestionvieetu.Budget.DetteFragment;
import uqac.gestionvieetu.Budget.EmpruntFragment;
import uqac.gestionvieetu.Budget.RecetteFragment;
import uqac.gestionvieetu.Etudes.AjoutHoraireFragment;
import uqac.gestionvieetu.Etudes.AjoutTacheFragment;
import uqac.gestionvieetu.Etudes.EdtFragment;
import uqac.gestionvieetu.Etudes.EtudesFragment;
import uqac.gestionvieetu.Etudes.RootEtudesFragment;
import uqac.gestionvieetu.Sorties.AjoutSortieFragment;
import uqac.gestionvieetu.Sorties.FragmentMapActivity;
import uqac.gestionvieetu.Sorties.SortiesFragment;
import uqac.gestionvieetu.Budget.BudgetActivty;


public class MainActivity extends AppCompatActivity{
    private static HashMap<String, Boolean> lJoursSelectionnes;

    //Nécessaire pour AjouterHoraire > Récurrence : toutes les semaines > sélection des jours à répéter
    static {
        lJoursSelectionnes = new HashMap<>();
        lJoursSelectionnes.put("MO", false);
        lJoursSelectionnes.put("TU", false);
        lJoursSelectionnes.put("WE", false);
        lJoursSelectionnes.put("TH", false);
        lJoursSelectionnes.put("FR", false);
        lJoursSelectionnes.put("SA", false);
        lJoursSelectionnes.put("SU", false);
    }

    //Variables pour afficher l'heure et la date choisie par l'utilisateur dans un bouton
    private String dateSelectionnee;
    private View bHeure;
    private View bDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //lance l'activité budget
        Button btnBudget = (Button)findViewById(R.id.tvBudget);
        btnBudget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MainActivity.this,BudgetActivty.class);
                startActivity(intent);
            }
        });
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Lors d'un clic sur le bouton Etudes dans la première fenêtre ; Affiche le layout études
    @RequiresApi(api = Build.VERSION_CODES.N)
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

    public void afficherLayoutAjoutBudget(View view) {
        this.changerLayout(new AjoutBudgetFragment());
    }

    public void afficherLayoutDepense(View view) {
        this.changerLayout(new DepenseFragment());
    }

    public void afficherLayoutRecette(View view) {
        this.changerLayout(new RecetteFragment());
    }

    public void afficherLayoutEmprunt(View view) {
        this.changerLayout(new EmpruntFragment());
    }

    public void afficherLayoutDette(View view) {
        this.changerLayout(new DetteFragment());
    }
    //Lors d'un clic sur le bouton Ajouter horaire ; affiche layout Ajout horaire
    public void afficherAjoutHoraire(View view) {
        this.changerLayout(new AjoutHoraireFragment());
    }

    public void afficherAjoutTache(View view) {
        this.changerLayout(new AjoutTacheFragment());
    }

    public void ajoutSortie(View view) {
        this.changerLayout(new AjoutSortieFragment());
    }

    public void affichageMap(View view) {
        this.changerLayout(new FragmentMapActivity());
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
        } else if (currentFragment instanceof AjoutSortieFragment) {
            ((AjoutSortieFragment) currentFragment).setMoment(moment, bHeure);
        }
        this.findViewById(R.id.main_fragment).invalidate();
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
        if (currentFragment instanceof AjoutHoraireFragment || currentFragment instanceof AjoutTacheFragment) {
            this.changerLayout(new EtudesFragment());
            this.changerLayout(new EdtFragment(), R.id.sous_etudes_fragment);
        } else if (currentFragment instanceof AjoutSortieFragment || currentFragment instanceof FragmentMapActivity) {
            this.changerLayout(new SortiesFragment());
        } else if (currentFragment instanceof EtudesFragment) {
            Fragment currentFragment2 = this.getSupportFragmentManager().findFragmentById(R.id.sous_etudes_fragment);
            if (currentFragment2 instanceof EdtFragment) {
                this.changerLayout(new RootEtudesFragment(), R.id.sous_etudes_fragment);
            } else {
                this.changerLayout(new MainFragment());
            }
        } else if (currentFragment instanceof SortiesFragment) {
            this.changerLayout(new MainFragment());
        } else {
            super.onBackPressed();
        }
    }

    public String getDateSelectionnee() {
        return dateSelectionnee;
    }

    public void setDateSelectionnee(String dateSelectionnee) {
        this.dateSelectionnee = dateSelectionnee;
        ((Button) bDate).setText(dateSelectionnee);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ajoutHoraire(View view) throws ParseException {
        //Récupération views du layout pour avoir accès aux choix de l'utilisateur
        Spinner sMatiere = (Spinner) findViewById(R.id.sMatiere);
        EditText etTitre = (EditText) findViewById(R.id.etTitreHoraire);
        Button bDate = (Button) findViewById(R.id.bDateEdt);
        Button bDebut = (Button) findViewById(R.id.bHeureDebutEdt);
        Button bFin = (Button) findViewById(R.id.bHeureFinEdt);
        Spinner sRecur = (Spinner) findViewById(R.id.sRecurrence);
        EditText etLieu = (EditText) findViewById(R.id.etLieuEdt);
        EditText etDetails = (EditText) findViewById(R.id.etNoteEdt);

        //Création de l'intent
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);

        intent.setType("vnd.android.cursor.item/event");

        if (!etTitre.getText().toString().isEmpty()) {
            intent.putExtra(CalendarContract.Events.TITLE, sMatiere.getSelectedItem() + " - " + etTitre.getText());
        } else {
            intent.putExtra(CalendarContract.Events.TITLE, sMatiere.getSelectedItem().toString());
        }

        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, etLieu.getText().toString());
        intent.putExtra(CalendarContract.Events.DESCRIPTION, etDetails.getText().toString());

        // Setting dates
        String date = bDate.getText().toString();

        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = curFormater.parse(date);

        SimpleDateFormat anneeFormater = new SimpleDateFormat("yyyy");
        SimpleDateFormat moisFormater = new SimpleDateFormat("MM");
        SimpleDateFormat jourFormater = new SimpleDateFormat("dd");

        int annee = Integer.parseInt(anneeFormater.format(dateObj));
        int mois = Integer.parseInt(moisFormater.format(dateObj)) - 1; //-1 à cause du Calendrier qui est indexé différement
        int jour = Integer.parseInt(jourFormater.format(dateObj));
        GregorianCalendar calDate = new GregorianCalendar(annee, mois, jour);


        // Conversion des heures en millisecondes
        String debut = bDebut.getText().toString();
        String fin = bFin.getText().toString();

        long msDebut = calDate.getTimeInMillis() + TimeUnit.HOURS.toMillis(Integer.parseInt(debut.substring(0, 2)))
                + TimeUnit.MINUTES.toMillis(Integer.parseInt(debut.substring(3, 5)));

        long msFin = calDate.getTimeInMillis() + TimeUnit.HOURS.toMillis(Integer.parseInt(fin.substring(0, 2)))
                + TimeUnit.MINUTES.toMillis(Integer.parseInt(fin.substring(3, 5)));


        //Ajout des heures à l'événement
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, msDebut);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, msFin);

        // make it a full day event
        //intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // make it a recurring Event
        String recurrence = "";
        switch (sRecur.getSelectedItem().toString()) {
            case "Tous les jours":
                recurrence = "FREQ=DAILY";
                break;

            case "Toutes les semaines":
                recurrence = "FREQ=WEEKLY";
                boolean sortie = false; //var de sortie de boucle
                boolean joursSelect = false;    //si des jours ont été selectionnés
                int i = 0;
                Object[] valeurs = lJoursSelectionnes.values().toArray();

                //On cherche à savoir si des jours ont été selectionnés pour ajouter "BYDAY=.." dans la récurrence
                while (!sortie || i < valeurs.length) {
                    if (valeurs[i].equals(true)) {
                        joursSelect = true;
                        sortie = true;
                    }
                    i++;
                }

                //Si des jours ont été selectionnés on les ajoute à la récurrence grâce au Hashmap
                if (joursSelect) {
                    recurrence += ";BYDAY=";
                    for (String cle : lJoursSelectionnes.keySet()) {
                        if (lJoursSelectionnes.get(cle)) {
                            recurrence += cle + ",";
                        }
                    }
                    //recurrence.substring(0, recurrence.length() - 2); //Enlève la dernière virgule
                }

                break;
            case "Tous les mois":
                recurrence = "FREQ=MONTHLY";
        }
        //intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");
        if (!recurrence.isEmpty()) {
            intent.putExtra(CalendarContract.Events.RRULE, recurrence);
        }

        intent.putExtra(CalendarContract.Events.EVENT_COLOR, 2);


        // Making it private and shown as busy
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    //Dans l'ajout d'horaire, s'occupe de la sélection de jours pour la récurrence
    public void selectionnerJour(View view) {
        Button b = (Button) view;
        //Le hashmap sait quels jours sont seletionnés ou non ; les garde dans l'ordre de la semaine
        switch (b.getText().toString()) {
            case "Lun.":
                lJoursSelectionnes.put("MO", !lJoursSelectionnes.get("MO"));
                break;
            case "Mar.":
                lJoursSelectionnes.put("TU", !lJoursSelectionnes.get("TU"));
                break;
            case "Mer.":
                lJoursSelectionnes.put("WE", !lJoursSelectionnes.get("WE"));
                break;
            case "Jeu.":
                lJoursSelectionnes.put("TH", !lJoursSelectionnes.get("TH"));
                break;
            case "Ven.":
                lJoursSelectionnes.put("FR", !lJoursSelectionnes.get("FR"));
                break;
            case "Sam.":
                lJoursSelectionnes.put("SA", !lJoursSelectionnes.get("SA"));
                break;
            case "Dim.":
                lJoursSelectionnes.put("SU", !lJoursSelectionnes.get("SU"));
                break;

        }
    }
    
    //Récupérer les matières sauvegardées dans un fichier depuis les Settings
    public ArrayList<String> getMatieres() {
        ArrayList<String> lMatieres = new ArrayList<>();

        try {
            FileInputStream fis = openFileInput(getResources().getString(R.string.nomFichierMatieres));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("\n")) {
                    lMatieres.add(line);
                }
            }
            br.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lMatieres;
    }

    /*public void afficherHistoque (View view) {
            @SuppressWarnings("unchecked")
            ArrayAdapter<TableDepenseRecette> adapter = (ArrayAdapter<TableDepenseRecette>) getListAdapter();
            TableDepenseRecette dr = null;
            switch (view.getId()) {
                case R.id.btnDepense:
                    String[] dep = new String[] { "Cool", "Very nice", "Hate it" };
                    int nextInt = new Random().nextInt(3);
                    // enregistrer le nouveau commentaire dans la base de données
                    comment = datasource.createComment(comments[nextInt]);
                    adapter.add(comment);
                    break;
                case R.id.delete:
                    if (getListAdapter().getCount() > 0) {
                        comment = (Comment) getListAdapter().getItem(0);
                        datasource.deleteComment(comment);
                        adapter.remove(comment);
                    }
                    break;
            }
            adapter.notifyDataSetChanged();
        } */


    /*public void modifierBudget(View view){
        EditText et = (EditText)findViewById(R.id.lblAjoutBudget);
        String b = et.getText().toString();
        float a = Float.parseFloat(b);
        DAOBase db = new DAOBase(this);
        db.open();

        /*TableBudget tb = new TableBudget(a, a, "decembre", "2010");
        db.ajouterBudget(tb);

        db.close();

        afficherLayoutBudget(view);
    }*/
}



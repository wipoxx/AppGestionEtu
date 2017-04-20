package uqac.gestionvieetu.Etudes;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uqac.gestionvieetu.CalendarContentResolver;
import uqac.gestionvieetu.Calendrier;
import uqac.gestionvieetu.Evenement;
import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

//Le fragment qui contient le layout de la partie Etudes
public class EtudesFragment extends Fragment {

    private View layout_etudes;
    private CaldroidFragment calendrierFragment;
    private View viewSelectionnee;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout_etudes = inflater.inflate(R.layout.layout_etudes, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        FragmentManager fragmentManager = getChildFragmentManager();
        calendrierFragment = (CaldroidFragment) fragmentManager.findFragmentById(R.id.calendrier_fragment);

        final CaldroidListener caldroidListener = new CaldroidListener() {
            //Lorsque l'utilisateur sélectionne une date
            @Override
            public void onSelectDate(Date date, View view) {

                //déselectionne la vue précedemment sélectionnée
                if(viewSelectionnee != null) {
                    viewSelectionnee.setBackgroundColor(getResources().getColor(R.color.caldroid_white, activity.getTheme()));
                }
                //Met son background en orange
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent, activity.getTheme()));

                //Ajoute la date à l'activité pour la passer aux fragment d'ajout d'événement
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
                activity.setDateSelectionnee(simpleDateFormat.format(date));

                viewSelectionnee = view; //La nouvelle view remplace l'ancienne
            }
        };

        calendrierFragment.setCaldroidListener(caldroidListener);
        return layout_etudes;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        Set<Set<Evenement>> evenements = this.getEvenements();
    }

    //Une collection qui contient une collection d'événements, séparées par calendriers
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Set<Set<Evenement>> getEvenements() {
        CalendarContentResolver contentResolver = new CalendarContentResolver(getContext());
        Set<Calendrier> calendriers = contentResolver.getCalendars();
        Set<Set<Evenement>> evenementsParCal = new HashSet<>();

        //On récupère tous les calendriers du téléphone
        for (Calendrier cal : calendriers) {
            Log.d("Calendrier_perso : ", cal.toString());
            evenementsParCal.add(contentResolver.getEvents(cal.getId()));
        }

        //On récupère les événements par calendriers
        for (Set<Evenement> evenementSet : evenementsParCal) {
            for (Evenement e : evenementSet) {
                Log.d("Evenements_perso", e.toString());
                //Log.d("Date_evenement", date.toString());
                Date date = new Date(Long.parseLong(e.getDebut()));
                calendrierFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.date_rouge, getActivity().getTheme()), date);
            }
        }
        return evenementsParCal;
    }
}

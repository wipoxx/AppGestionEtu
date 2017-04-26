package uqac.gestionvieetu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CalendrierFragment extends CaldroidFragment {

    private View viewSelectionnee;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final MainActivity activity = (MainActivity) getActivity();

        final CaldroidListener caldroidListener = new CaldroidListener() {
            //Lorsque l'utilisateur sélectionne une date
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSelectDate(Date date, View view) {

                //déselectionne la vue précedemment sélectionnée
                if (viewSelectionnee != null) {
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

        this.setCaldroidListener(caldroidListener);
        this.getEvenements();
        return super.onCreateView(inflater, container, savedInstanceState);
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
            try {
                evenementsParCal.add(contentResolver.getEvents(cal.getId()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //On récupère les événements par calendriers
        for (Set<Evenement> evenementSet : evenementsParCal) {
            for (Evenement e : evenementSet) {
                Log.d("Evenements_perso", e.toString());
                //Log.d("Date_evenement", date.toString());
                Date date = new Date(Long.parseLong(e.getDebut()));

                this.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.template_evenement, getActivity().getTheme()), date);
            }
        }
        return evenementsParCal;
    }
}

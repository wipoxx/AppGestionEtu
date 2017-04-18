package uqac.gestionvieetu.Etudes;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import com.roomorama.caldroid.CaldroidFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout_etudes = inflater.inflate(R.layout.layout_etudes, container, false);
        final MainActivity activity = (MainActivity) getActivity();
        Set<Set<Evenement>> evenements = this.getEvenements();
        /*//Date par défaut = date courante
        CalendarView calendarView = (CalendarView) layout_etudes.findViewById(R.id.calendrier);
        Long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String dateCourante = simpleDateFormat.format(date);

        activity.setDateSelectionnee(dateCourante);

        //Quand l'utilisateur sélectionne une date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Envoie la date choisie à l'activité
                String date = "";

                //Si le jour est < 10
                if (dayOfMonth < 10) {
                    date += "0";
                }

                date += dayOfMonth + "/";
                //Affiche le 0 avant le mois si entre 0 et 9
                if (month < 10) {
                    date += "0";
                }
                date += (++month) + "/" + year;
                activity.setDateSelectionnee(date);
            }
        });*/

/*        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        android.support.v4.app.FragmentTransaction t = activity.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendrier_fragment, caldroidFragment);
        t.commit();

*/
        return layout_etudes;
    }

    //Doit servir à afficher la date choisie dans le bouton d'ajout horraire/tâche dans le calendrier
    /*public void setDate(String date) throws ParseException {
        final MainActivity activity = (MainActivity) getActivity();

        //Date par défaut = date courante
        CalendarView calendarView = (CalendarView) layout_etudes.findViewById(R.id.calendrier);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        Date mDate = simpleDateFormat.parse(date);
        long timeInMilliseconds = mDate.getTime();
        calendarView.setDate(timeInMilliseconds);
    }*/

    //Une collection qui contient une collection d'événements, séparées par calendriers
    public Set<Set<Evenement>> getEvenements() {
        CalendarContentResolver contentResolver = new CalendarContentResolver(getContext());
        Set<Calendrier> calendriers = contentResolver.getCalendars();
        Set<Set<Evenement>> evenementsParCal = new HashSet<>();
        for (Calendrier cal : calendriers) {
            evenementsParCal.add(contentResolver.getEvents(cal.getId()));
        }
        return evenementsParCal;
    }


}

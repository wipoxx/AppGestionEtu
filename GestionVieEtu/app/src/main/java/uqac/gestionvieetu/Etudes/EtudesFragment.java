package uqac.gestionvieetu.Etudes;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

//Le fragment qui contient le layout de la partie Etudes
public class EtudesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_etude = inflater.inflate(R.layout.layout_etudes, container, false);
        final MainActivity activity = (MainActivity) getActivity();

        //Date par défaut = date courante
        CalendarView calendarView = (CalendarView) layout_etude.findViewById(R.id.calendrier);
        Long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        });

        return layout_etude;
    }
}

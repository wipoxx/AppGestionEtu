package uqac.gestionvieetu.Etudes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

//Le fragment qui contient le layout de la partie Etudes
public class EtudesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_etude = inflater.inflate(R.layout.layout_etudes, container, false);

        //Récupère le calendrier et le passe à l'activité
        CalendarView calendarView = (CalendarView) layout_etude.findViewById(R.id.calendrier);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setCalendrier(calendarView);

        return layout_etude;
    }
}

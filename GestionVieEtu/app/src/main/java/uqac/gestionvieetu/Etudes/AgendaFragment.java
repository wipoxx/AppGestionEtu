package uqac.gestionvieetu.Etudes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

public class AgendaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout_agenda = (LinearLayout) inflater.inflate(R.layout.layout_agenda, container, false);

        //Récupère le calendrier commun et l'ajoute au layout
        MainActivity mainActivity = (MainActivity) getActivity();
        CalendarView calendarView = mainActivity.getCalendrier();
        ViewGroup viewParent = (ViewGroup) calendarView.getParent();
        viewParent.removeView(calendarView);
        layout_agenda.addView(calendarView, 0);

        return layout_agenda;
    }
}
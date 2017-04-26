package uqac.gestionvieetu.Etudes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

public class AjoutHoraireFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final LinearLayout layout_horaire = (LinearLayout) inflater.inflate(R.layout.layout_ajout_horaire, container, false);
        MainActivity activity = (MainActivity) getActivity();

        //----------------SPINNER MATIERES
        //----Pour le texte du spinner
        ArrayList<String> lMatieres = activity.getMatieres();
        Spinner sMatiere = (Spinner) layout_horaire.findViewById(R.id.sMatiere);
        if (lMatieres.isEmpty()) {
            lMatieres.add("");
            lMatieres.add("Vous pouvez ajouter une matière dans les settings");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lMatieres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMatiere.setAdapter(adapter);

        //-------------SPINNER RECURRENCE
        //----Pour le texte du spinner
        final Spinner sRecur = (Spinner) layout_horaire.findViewById(R.id.sRecurrence);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.recurrence_evenement, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sRecur.setAdapter(adapter2);

        //Ajout d'un listener sur le spinner de la récurrence
        //pour afficher les jours à choisir si l'utilisateur veut répéter l'event toutes les semaines
        sRecur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] recur = getResources().getStringArray(R.array.recurrence_evenement);
                String[] jours = getResources().getStringArray(R.array.jours_array);
                String s = sRecur.getSelectedItem().toString();

                LinearLayout layout_jours1 = (LinearLayout) layout_horaire.findViewById(R.id.llJours1);
                LinearLayout layout_jours2 = (LinearLayout) layout_horaire.findViewById(R.id.llJours2);

                if (sRecur.getSelectedItem().toString().equals(recur[2])) {
                    for (int i = 0; i < 4; i++) {
                        //Button b = new Button(getContext());
                        Button b = (Button) inflater.inflate(R.layout.template_button_jours, null);
                        b.setText(jours[i]);
                        layout_jours1.addView(b, i);
                    }

                    for (int i = 4; i < 7; i++) {
                        //Button b = new Button(getContext());
                        Button b = (Button) inflater.inflate(R.layout.template_button_jours, null);
                        b.setText(jours[i]);
                        layout_jours2.addView(b, i - 4);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-------------BOUTONS DATE - HEURE
        //Ajout de la date choisie sur le calendrier dans le texte du bouton
        Button bDate = (Button) layout_horaire.findViewById(R.id.bDateEdt);
        bDate.setText(activity.getDateSelectionnee());

        //Ajout de l'heure courante aux boutons de début et fin d'horaire
        Date d = new Date();
        CharSequence s = DateFormat.format("HH:mm", d.getTime());
        this.setMoment(s.toString(), layout_horaire.findViewById(R.id.bHeureDebutEdt));
        this.setMoment(s.toString(), layout_horaire.findViewById(R.id.bHeureFinEdt));


        return layout_horaire;
    }

    //Met l'heure et les minutes dans le texte du bouton
    public void setMoment(String moment, View view) {
        Button b = (Button) view;
        b.setText(moment);
    }

    public void setDate(String date, View view) {
        Button b = (Button) view;
        b.setText(date);
    }
}
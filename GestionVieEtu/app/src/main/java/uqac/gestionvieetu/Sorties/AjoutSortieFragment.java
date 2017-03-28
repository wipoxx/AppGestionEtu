package uqac.gestionvieetu.Sorties;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import uqac.gestionvieetu.R;

/**
 * Created by guill on 28/03/2017.
 */

public class AjoutSortieFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout layout_horaire = (LinearLayout) inflater.inflate(R.layout.layout_ajout_sortie, container, false);
            return layout_horaire;
        }

        //Met l'heure et les minutes dans le texte du bouton
        public void setMoment(String moment, View view) {
            Button b = (Button) view;
            b.setText(moment);
        }
}

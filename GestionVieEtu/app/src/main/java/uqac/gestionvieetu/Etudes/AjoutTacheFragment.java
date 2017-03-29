package uqac.gestionvieetu.Etudes;

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

import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

public class AjoutTacheFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout_ajout_tache = (LinearLayout) inflater.inflate(R.layout.layout_ajout_tache, container, false);

        MainActivity activity = (MainActivity) getActivity();
        //----Pour le texte du spinner
        Spinner sMatiere = (Spinner) layout_ajout_tache.findViewById(R.id.sMatiere);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.matieres_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sMatiere.setAdapter(adapter);


        Button bDate = (Button) layout_ajout_tache.findViewById(R.id.bDateTache);
        bDate.setText(activity.getDateSelectionnee());

        return layout_ajout_tache;
    }

    public void setDate(String date, View view) {
        Button b = (Button) view;
        b.setText(date);
    }
}
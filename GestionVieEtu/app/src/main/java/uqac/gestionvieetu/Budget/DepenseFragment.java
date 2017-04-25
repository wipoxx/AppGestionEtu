package uqac.gestionvieetu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Fiolyne on 24/04/2017.
 */

public class DepenseFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_depense, container, false);

        String [] values =
                {"Alimentaire","Vestimentaire", "Utilitaire", "Soins", "Sortie", "Loisir", "Sport", "Autre", "Loyer", "Transport",};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerTypeDep);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }
}

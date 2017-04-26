package uqac.gestionvieetu.Etudes;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import uqac.gestionvieetu.CalendrierFragment;
import uqac.gestionvieetu.R;

//Le fragment qui contient le layout de la partie Etudes
public class EtudesFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< Updated upstream
        return inflater.inflate(R.layout.layout_etudes, container, false);
=======
        View layout_etudes = inflater.inflate(R.layout.layout_etudes, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();
        CalendrierFragment calendrierFragment = (CalendrierFragment) fragmentManager.findFragmentById(R.id.calendrier_fragment);
        return layout_etudes;
>>>>>>> Stashed changes
    }

}

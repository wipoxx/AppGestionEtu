package uqac.gestionvieetu.Sorties;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import uqac.gestionvieetu.CalendrierFragment;
import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

/**
 * Created by guill on 28/03/2017.
 */

public class SortiesFragment extends android.support.v4.app.Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_sorties, container, false);
    }
}

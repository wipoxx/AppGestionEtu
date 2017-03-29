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

public class RootEtudesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout_root_etudes = (LinearLayout) inflater.inflate(R.layout.layout_etudes_root, container, false);
        return layout_root_etudes;
    }
}
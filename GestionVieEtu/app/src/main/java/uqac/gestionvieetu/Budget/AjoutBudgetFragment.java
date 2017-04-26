package uqac.gestionvieetu.Budget;

/**
 * Created by Fiolyne on 23/04/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import uqac.gestionvieetu.R;

public class AjoutBudgetFragment extends Fragment {
    //Budget bdg;
    //EditText montant;
    //Button valider;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_ajout_budget, container, false);

        /*montant = (EditText)view.findViewById(R.id.lblAjoutBudget);
        valider = setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Budget bg = new Budget(Double.parseDouble(montant.getText().toString()));
                Intent intent = new Intent(AjoutBudgetFragment.this, BudgetFragment.class);
                intent.putExtra("budget", bg);
                startActivity(intent);
            }


        });*/
        return view;
    }
}

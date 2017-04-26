package uqac.gestionvieetu.Budget;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import uqac.gestionvieetu.R;

//Le fragment qui contient le layout de la partie Budget
public class BudgetFragment extends Fragment {
    //Budget bg;
    //TextView montant, solde;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_budget, container, false);

        /*bg = (Budget) getIntent().getExtras().getSerializable("budget");

        montant =(TextView)v.findViewById(R.id.lblBudget);
        montant.setText(Double.toString(bg.getMontant()));
        solde = (TextView)v.findViewById(R.id.lblSolde);
        solde.setText(Double.toString(bg.getSolde()));*/

        return v;
    }
}

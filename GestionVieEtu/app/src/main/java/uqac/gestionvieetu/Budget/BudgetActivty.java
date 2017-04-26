package uqac.gestionvieetu.Budget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import uqac.gestionvieetu.MainActivity;
import uqac.gestionvieetu.R;

import static android.content.ContentValues.TAG;

public class BudgetActivty extends Activity {
    Budget budget;
    TextView tvmontant,tvsolde,tvaffiche;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_budget);

        if(getIntent().getExtras()!=null){
            budget = (Budget)getIntent().getExtras().getSerializable("budg");
        }
        else budget=new Budget();

        tvmontant=(TextView) findViewById(R.id.lblBudget);
        tvsolde=(TextView) findViewById(R.id.lblSolde);
        tvaffiche=(TextView) findViewById(R.id.listDep);
        Button btnAddBudget = (Button)findViewById(R.id.btnModif);
        btnAddBudget.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(BudgetActivty.this,AjoutBudgetActivity.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });

        Button btnDep = (Button)findViewById(R.id.btnDepense);
        btnDep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(BudgetActivty.this,DepenseActivity.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });

        Button btnRec = (Button)findViewById(R.id.btnRecette);
        btnRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(BudgetActivty.this,RecetteActivity.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });

        Button btnEmp = (Button)findViewById(R.id.btnEmp);
        btnEmp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(BudgetActivty.this,EmpruntActivity.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });

        Button btnDette = (Button)findViewById(R.id.btnDette);
        btnDette.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(BudgetActivty.this,DetteActivity.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });

        tvmontant.setText(Double.toString(budget.getMontant()));
        tvsolde.setText(Double.toString(budget.getSolde()));
        tvaffiche.setText(budget.toString());
    }

}

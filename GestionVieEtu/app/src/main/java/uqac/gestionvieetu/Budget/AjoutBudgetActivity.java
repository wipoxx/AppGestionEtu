package uqac.gestionvieetu.Budget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uqac.gestionvieetu.R;

public class AjoutBudgetActivity extends Activity {
    Budget budget;
    EditText montant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ajout_budget);

        budget=(Budget) getIntent().getExtras().getSerializable("budg");
        montant=(EditText) findViewById(R.id.lblAjoutBudget);
        montant.setText("0");
        Button btnValider = (Button)findViewById(R.id.btnValiderBudget);
        btnValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                budget.setMontant(Double.parseDouble(montant.getText().toString()));
                budget.setSolde(Double.parseDouble(montant.getText().toString()));
                Intent intent=new Intent(AjoutBudgetActivity.this,BudgetActivty.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });
    }


}

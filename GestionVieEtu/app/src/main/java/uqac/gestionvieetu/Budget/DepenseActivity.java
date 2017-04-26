package uqac.gestionvieetu.Budget;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import uqac.gestionvieetu.R;

public class DepenseActivity extends Activity{
    Budget budget;
    EditText montant;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_depense);


        String [] values =
                {"Alimentaire","Vestimentaire", "Utilitaire", "Soins", "Sortie", "Loisir", "Sport", "Autre", "Loyer", "Transport",};
        spinner = (Spinner)findViewById(R.id.spinnerTypeDep);
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_dropdown_item, values);
        spinner.setAdapter(adapter);

        budget=(Budget) getIntent().getExtras().getSerializable("budg");
        montant=(EditText) findViewById(R.id.etDepense);
        montant.setText("0");
        Button btnValider = (Button)findViewById(R.id.btnValiderDepense);
        btnValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                budget.addDep(Double.parseDouble(montant.getText().toString()),spinner.getSelectedItem().toString());
                Intent intent=new Intent(DepenseActivity.this,BudgetActivty.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });
    }
}

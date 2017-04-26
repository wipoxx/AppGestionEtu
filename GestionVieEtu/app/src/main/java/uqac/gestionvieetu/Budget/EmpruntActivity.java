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

public class EmpruntActivity extends Activity{
    Budget budget;
    EditText montant, nom, prenom;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_emprunt);

        String [] values =
                {"Alimentaire","Vestimentaire", "Utilitaire", "Soins", "Sortie", "Loisir", "Sport", "Autre", "Loyer", "Transport",};
        spinner = (Spinner)findViewById(R.id.spinnerTypeEmp);
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_dropdown_item, values);
        spinner.setAdapter(adapter);

        budget=(Budget) getIntent().getExtras().getSerializable("budg");
        montant=(EditText) findViewById(R.id.etEmprunt);
        montant.setText("0");
        nom=(EditText) findViewById(R.id.etEmpruntNom);
        nom.setText("");
        prenom=(EditText) findViewById(R.id.etEmpruntPrenom);
        prenom.setText("");
        Button btnValider = (Button)findViewById(R.id.btnValiderEmprunt);
        btnValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                budget.addEmp(Double.parseDouble(montant.getText().toString()),spinner.getSelectedItem().toString(),nom.getText().toString(), prenom.getText().toString());
                Intent intent=new Intent(EmpruntActivity.this,BudgetActivty.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });
    }
}

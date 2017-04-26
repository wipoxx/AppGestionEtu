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

public class DetteActivity extends Activity{
    Budget budget;
    EditText montant, nom, prenom;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dette);

        String [] values =
                {"Alimentaire","Vestimentaire", "Utilitaire", "Soins", "Sortie", "Loisir", "Sport", "Autre", "Loyer", "Transport",};
        spinner = (Spinner)findViewById(R.id.spinnerTypeDette);
        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_dropdown_item, values);
        spinner.setAdapter(adapter);

        budget=(Budget) getIntent().getExtras().getSerializable("budg");
        montant=(EditText) findViewById(R.id.etDette);
        montant.setText("0");
        nom=(EditText) findViewById(R.id.etDetteNom);
        nom.setText("");
        prenom=(EditText) findViewById(R.id.etDettePrenom);
        prenom.setText("");
        Button btnValider = (Button)findViewById(R.id.btnValiderDette);
        btnValider.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                budget.addDette(Double.parseDouble(montant.getText().toString()),spinner.getSelectedItem().toString(),nom.getText().toString(), prenom.getText().toString());
                Intent intent=new Intent(DetteActivity.this,BudgetActivty.class);
                intent.putExtra("budg",budget);
                startActivity(intent);
                finish();
            }
        });
    }

}

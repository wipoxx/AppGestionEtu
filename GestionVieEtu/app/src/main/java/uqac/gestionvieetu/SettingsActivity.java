package uqac.gestionvieetu;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private final String NOM_FICHIER = "matieres_settings";
    private ArrayList<String> lMatieres = new ArrayList<>();
    private String dateSelectionnee;
    private View bDate;
    //private String[] lMatieres = new String[]{"Tech bbbbbbb bbbbbbb bbbbbbb bbbbbbb bbbbbbb bbbbbbb bbbbbbb ", "Prog mobile", "Marketing", "Sa race", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette", "Chaussette"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);

        //Récupérer les matières déjà sauvegardées
        try {
            FileInputStream fis = openFileInput(NOM_FICHIER);
            int carInt = fis.read();
            while (carInt != -1) {
                String car = Integer.toString(carInt);
                String matiere = "";
                if (car.equals(";")) {
                    matiere += car;
                } else {
                    lMatieres.add(matiere);
                    matiere = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*String FILENAME = "hello_file";
        String string = "hello world!";

        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(string.getBytes());
        fos.close();*/

        afficherListeMatieres();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_enregistrerSettings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Ajoute une matière dans lMatieres, l'affiche et sauvegarde la nouvelle liste
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void ajouterMatiere(View view) {

        final LinearLayout llPopup = (LinearLayout) View.inflate(this, R.layout.layout_popup_matiere, null);

        final PopupWindow popup = new PopupWindow(llPopup, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        popup.setContentView(llPopup);
        popup.setFocusable(true);
        popup.update();
        popup.showAtLocation(findViewById(R.id.layout_settings), Gravity.TOP, 0, 0);


        //Bouton ok
        Button bAjouter = (Button) llPopup.findViewById(R.id.bAjouterPopupMatiere);
        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etMatiere = (EditText) llPopup.findViewById(R.id.etNomMatiere);
                lMatieres.add(etMatiere.getText().toString());
                popup.dismiss();
                afficherListeMatieres();
                //SAUVEGARDE DS FICHIER
            }
        });

        //Bouton annuler
        Button bAnnuler = (Button) llPopup.findViewById(R.id.bAnnulerPopupMatiere);
        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


    }

    //Permet d'afficher le contenu de lMatieres dans la ListView
    public void afficherListeMatieres() {
        ListView lvMatieres = (ListView) findViewById(R.id.lvMatieresSettings);
        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.template_matiere_settings, lMatieres);
        lvMatieres.setAdapter(adapter);
        lvMatieres.setTextFilterEnabled(true);

        lvMatieres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                registerForContextMenu(view);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        TextView tvMatiere = (TextView) v;
        menu.setHeaderTitle(((TextView) v).getText());
        menu.add(0, v.getId(), 0, "Modifier");
        menu.add(0, v.getId(), 0, "Supprimer");
    }

    public void afficherDatePicker(View view) {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "timePicker");
        bDate = view;
    }

    public void setDateSelectionnee(String dateSelectionnee) {
        this.dateSelectionnee = dateSelectionnee;
        ((Button) bDate).setText(dateSelectionnee);
    }
}

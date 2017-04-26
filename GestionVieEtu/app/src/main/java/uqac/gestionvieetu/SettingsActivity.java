package uqac.gestionvieetu;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    private ArrayList<String> lMatieres = new ArrayList<>();
    private View bDate;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);

        //Récupérer les matières déjà sauvegardées
        try {
            FileInputStream fis = openFileInput(getResources().getString(R.string.nomFichierMatieres));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("\n")) {
                    lMatieres.add(line);
                }
            }
            br.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

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

        //Context menu avec un clic long sur une matière
        //registerForContextMenu(lvMatieres);
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
        menu.setHeaderTitle(((TextView) v).getText());
        Toast.makeText(this, v.getId() + "", Toast.LENGTH_SHORT).show();
        menu.add(0, v.getId(), 0, "Modifier");
        menu.add(0, v.getId(), 0, "Supprimer");


        /*if (v.getId() == R.id.lvMatieresSettings) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            //TextView obj = (TextView) lv.getItemAtPosition(acmi.position);
            String obj = (String) lv.getItemAtPosition(acmi.position);
            menu.add("One");
            menu.add("Two");
            menu.add("Three");
            menu.add(obj);
        }*/

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getItemId() + "", Toast.LENGTH_SHORT).show();
        if (item.getTitle() == "Modifier") {
            Toast.makeText(this, item.getItemId() + "", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Supprimer") {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }

    public void afficherDatePicker(View view) {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "timePicker");
        bDate = view;
    }

    public void setDateSelectionnee(String dateSelectionnee) {
        ((Button) bDate).setText(dateSelectionnee);
    }

    public void enregistrerMatieres(View view) {
        //SAUVEGARDE DS FICHIER
        FileOutputStream fos;
        try {
            fos = openFileOutput(getResources().getString(R.string.nomFichierMatieres), Context.MODE_PRIVATE);

            //Ajouter chaque matière dans le fichier
            for (String nomMatiere : lMatieres) {
                fos.write(nomMatiere.getBytes());
                fos.write("\n".getBytes());
            }


            fos.close();
            Toast.makeText(this, "Matières enregistrées", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

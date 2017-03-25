package uqac.gestionvieetu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Lors d'un clic sur le bouton Etudes dans la première fenêtre
    public void afficherLayoutEtudes(View view) {
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();

        Fragment etudesFragment = new EtudesFragment();

        fragTrans.replace(R.id.fragment_main, etudesFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }

    //Lors d'un clic sur le bouton Gérer EDT dans les Etudes
    /*public void afficherBoutonsAgenda(View view) {

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout =  layoutInflater.inflate(R.layout.layout_etudes, null);
        LinearLayout linearLayout =  (LinearLayout) viewLayout.findViewById(R.id.layout_etudes);

        //LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.layout_etudes, null);

        linearLayout.removeAllViewsInLayout();
        linearLayout.removeView(findViewById(R.id.gererEDT));
        linearLayout.removeView(findViewById(R.id.gererNotes));
        linearLayout.removeView(findViewById(R.id.planifierRevisions));

    }*/

    //Lors d'un clic sur le bouton Budget dans la première fenêtre
    public void afficherLayoutBudget(View view) {
        FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();

        Fragment budgetFragment = new BudgetFragment();


        fragTrans.replace(R.id.fragment_main, budgetFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }
}

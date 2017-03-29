package uqac.gestionvieetu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Récupérer la date choisie dans le calendrier comme valeur par défaut
        String dateSelectionnee = ((MainActivity) getActivity()).getDateSelectionnee();
        int jour = Integer.parseInt(dateSelectionnee.substring(0, 2));
        int mois = Integer.parseInt(dateSelectionnee.substring(3, 5));
        int annee = Integer.parseInt(dateSelectionnee.substring(6, 10));


        //Créer une nouvelle instance du DatePicker et la retourner
        return new DatePickerDialog(getActivity(), this, annee, --mois, jour);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        MainActivity mainActivity = (MainActivity) getActivity();
        String date = "";

        //Si le jour est < 10
        if (dayOfMonth < 10) {
            date += "0";
        }

        date += dayOfMonth + "/";

        //Affiche le 0 avant le mois si entre 0 et 9
        if (month < 10) {
            date += "0";
        }
        date += (++month) + "/" + year;
        mainActivity.setDateSelectionnee(date);
    }
}

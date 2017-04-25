package uqac.gestionvieetu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerDialog datePickerDialog;
        int jour, mois, annee;
        //Récupérer la date choisie dans le calendrier comme valeur par défaut
        /*String dateSelectionnee = ((MainActivity) getActivity()).getDateSelectionnee();
        if (dateSelectionnee != null) {
             jour = Integer.parseInt(dateSelectionnee.substring(0, 2));
             mois = Integer.parseInt(dateSelectionnee.substring(3, 5));
             annee = Integer.parseInt(dateSelectionnee.substring(6, 10));

        } else {*/
            Calendar c = new GregorianCalendar();
            Date date = c.getTime();
            DateFormat jourFormat = new SimpleDateFormat("dd");
            DateFormat moisFormat = new SimpleDateFormat("MM");
            DateFormat anneeFormat = new SimpleDateFormat("yyyy");

            jour = Integer.parseInt(jourFormat.format(date));
            mois = Integer.parseInt(moisFormat.format(date));
            annee = Integer.parseInt(anneeFormat.format(date));

       // }


        //Créer une nouvelle instance du DatePicker et la retourner
        return new DatePickerDialog(getActivity(), this, annee, --mois, jour);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Activity activity = getActivity();
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
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).setDateSelectionnee(date);
        } else if (activity instanceof SettingsActivity) {
            ((SettingsActivity) activity).setDateSelectionnee(date);
        }

    }
}

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
        final CalendarView c = ((MainActivity) getActivity()).getCalendrier();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        String dateFormatee = format.format(c.getDate());

        Log.d("Date:Date", dateFormatee);
        Log.d("Date:Jour", dateFormatee.substring(0, 2));
        Log.d("Date:Mois", dateFormatee.substring(3, 5));
        Log.d("Date:Annee", dateFormatee.substring(6, 10));

      //  int jour = Integer.getInteger(dateFormatee.substring(0, 2));
        //int mois = Integer.getInteger(dateFormatee.substring(3, 5));
        //int annee = Integer.getInteger(dateFormatee.substring(6, 10));

        //Créer une nouvelle instance du DatePicker et la retourner
        return new DatePickerDialog(getActivity());
        //return new DatePickerDialog(getActivity(), this, jour, mois, annee);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}

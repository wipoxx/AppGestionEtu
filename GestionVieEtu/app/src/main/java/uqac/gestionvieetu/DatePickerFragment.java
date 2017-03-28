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

import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePicker.OnDateChangedListener{

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Récupérer la date choisie dans le calendrier comme valeur par défaut
        final CalendarView c = ((MainActivity) getActivity()).getCalendrier();
        Log.d("Date_long",  String.valueOf(c.getDate()));
        //Créer une nouvelle instance du DatePicker et la retournée
        //return new DatePickerDialog(getActivity(), this, );
        return new DatePickerDialog(getActivity());
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}

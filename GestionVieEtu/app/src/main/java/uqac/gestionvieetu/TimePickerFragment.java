package uqac.gestionvieetu;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Utiliser le temps courant comme valeur par défaut dans le TimePicker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Créer une nouvelle instance du TimePicker et la retournée
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        MainActivity mainActivity = (MainActivity) getActivity();
        String moment = "";
        //Affiche le 0 avant les heures si entre 0 et 9
        if (hourOfDay < 10) {
            moment += "0";
        }
        moment += hourOfDay + ":";

        //Affiche le 0 avant les minutes si entre 0 et 9
        if (minute < 10) {
            moment += "0";
        }

        moment += minute;
        mainActivity.setMoment(moment);
    }

}

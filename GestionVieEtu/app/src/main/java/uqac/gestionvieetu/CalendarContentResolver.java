package uqac.gestionvieetu;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class CalendarContentResolver {
    public static final String[] FIELDS_CALENDAR = {
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            CalendarContract.Calendars.VISIBLE
    };

    public static final String[] FIELDS_EVENT = {
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DESCRIPTION,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND
    };

    public static final Uri CALENDAR_URI = Uri.parse("content://com.android.calendar/calendars");
    public static final Uri EVENT_URI = Uri.parse("content://com.android.calendar/events");

    ContentResolver contentResolver;
    Set<Calendrier> calendars = new HashSet<>();
    Set<Evenement> events = new HashSet<>();

    public  CalendarContentResolver(Context ctx) {
        contentResolver = ctx.getContentResolver();
    }

    public Set<Calendrier> getCalendars() {
        // Fetch a list of all calendars sync'd with the device and their display names
        Cursor cursor = contentResolver.query(CALENDAR_URI, FIELDS_CALENDAR, null, null, null);

        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Calendrier cal = new Calendrier();

                    cal.setId(cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars._ID)));
                    cal.setNom(cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME)));
                    cal.setVisible(!cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.VISIBLE)).equals("0"));

                    calendars.add(cal);
                }
            }
        } catch (AssertionError ex) { Log.e("CalendarContentResolver", ex.toString()); }

        return calendars;
    }

    public Set<Evenement> getEvents(String calendarId) {
        String selection = CalendarContract.Events.CALENDAR_ID +"="+ calendarId;
        Cursor cursor = contentResolver.query(EVENT_URI, FIELDS_EVENT, selection, null, null);
        Evenement event;
        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    event = new Evenement();

                    event.setTitre(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE)));
                    event.setDescription(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION)));
                    event.setDebut(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTSTART)));
                    event.setFin(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTEND)));

                    events.add(event);
                }
            }
        } catch (AssertionError ex) {
            Log.e("CalendarContentResolver", ex.toString());
        }

        return events;
    }

}
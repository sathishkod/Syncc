package gtsarandum.syncc;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.vdesmet.lib.calendar.DayAdapter;

import java.util.Calendar;

public class CustomDayAdapter implements DayAdapter {

    private Context context;

    private static final int[] CATEGORY_COLORS = {
            0,
            Color.BLUE,
            Color.RED,
            Color.GREEN, Color.RED,
            Color.CYAN, Color.GREEN, Color.RED, Color.BLUE, Color.BLACK
    };

    private final long mToday;

    public CustomDayAdapter(Context context) {
        this.context=context;

        // Get the time in millis of today
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        mToday = calendar.getTimeInMillis();
    }

    @Override
    public int getCategoryColors(final long dayInMillis) {
/*
        //check if day has events
        Calendar allDay=Calendar.getInstance();
        allDay.setTimeInMillis(dayInMillis);
        allDay.set(Calendar.HOUR_OF_DAY,0);
        allDay.set(Calendar.MINUTE,0);
        allDay.set(Calendar.SECOND,0);

        Calendar endOfDay=Calendar.getInstance();
        endOfDay.setTimeInMillis(dayInMillis);
        endOfDay.set(Calendar.HOUR_OF_DAY,23);
        endOfDay.set(Calendar.MINUTE,59);
        endOfDay.set(Calendar.SECOND,59);

        Cursor cursor=context.getContentResolver().query(CalendarContract.Events.CONTENT_URI,
                new String[]{//which columns to return?
                        CalendarContract.Events.DTSTART,
                        CalendarContract.Events.DTEND
                },
                "("+CalendarContract.Events.DTSTART+">"+dayInMillis+" and "

                        +CalendarContract.Events.DTEND+"<"+endOfDay.getTimeInMillis()+
                        ") or ("

                        +CalendarContract.Events.ALL_DAY+"=1) and "+

                        "("+CalendarContract.Events.DTSTART+">="+allDay.getTimeInMillis()
                        +")"/* and "+"("+CalendarContract.Events.DTEND+"<="+
                        endTimeAllDay.getTimeInMillis()+"))"//selection
                null,
                null
        ;

        int start;
        int end;
        boolean hasEvent=false;

        if (/*cursor.moveToFirst()){
            do {
                start=cursor.getInt(0);
                end=cursor.getInt(1);

                if (start>allDay.getTimeInMillis() && end<endOfDay.getTimeInMillis()){
                    hasEvent=true;
                }

            } while (cursor.moveToNext());
        }

        if (hasEvent){
            return CATEGORY_COLORS[4];
        }*/

        return CATEGORY_COLORS[0];
    }

    @Override
    public boolean isDayEnabled(final long dayInMillis) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dayInMillis);

        // Disable all saturdays
        return true; //calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY;
    }

    @Override
    public void updateTextView(final TextView dateTextView, final long dayInMillis) {
        if(mToday == dayInMillis) {
            // Do something with the selected date
            dateTextView.setTextColor(context.getResources().getColor(R.color.deep_orange_a700));
        }
        // else, we don't need to update the TextView
    }

    @Override
    public void updateHeaderTextView(final TextView header, final int dayOfWeek) {
        switch(dayOfWeek) {
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:
                header.setTextColor(context.getResources().getColor(R.color.deep_orange_a200));
                break;

            default:
                header.setTextColor(context.getResources().getColor(R.color.indigo_500));
                break;
        }
    }
}

package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.zaim.decoratecalendarview.DecorateCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.cyclingsir.helper.calendar.DateEvent;
import de.cyclingsir.helper.calendar.HighlightCalendarView;


public class CalendarFragment extends Fragment
{
    //attr
    private FrameLayout frameLayout;

    //Highlight Calendar

    ArrayList<SynccEvent> synccEvents;
    private HighlightCalendarView highlightCalendarView;
    private customOnCalendarInteractionListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout=(FrameLayout) inflater.inflate(R.layout.fragment_calendar, container, false);
        return frameLayout;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        synccEvents=new ArrayList<SynccEvent>();
        //fill array with events
        fillSynccEventsList();

        setHasOptionsMenu(true);

        if (frameLayout!=null) {
            highlightCalendarView=(HighlightCalendarView) frameLayout.findViewById(R.id.calendar);
            highlightCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
            highlightCalendarView.setShowWeekNumber(false);

            //decorative calender
            /*
            decorateCalendarView=(DecorateCalendarView) frameLayout.findViewById(R.id.calendar);
            Bundle calenderArgs =new Bundle();
            calenderArgs.putString(DecorateCalendarView.BUNDLE_KEY_BEGINNING_DAY_OF_WEEK, String.valueOf(Calendar.MONDAY));
            decorateCalendarView.initCalendar(this.fragmentManager,calenderArgs);*/
        }else {
            test("frameLayout=null");
        }

        if(highlightCalendarView!=null){
            //listener and events
            highlightCalendarView.addEvents(synccEvents);
            //sets listener that calls internal listener set from mainactivity
            highlightCalendarView.setOnDateSelectedListener(new HighlightCalendarView.OnDateSelectedListener() {
                @Override
                public void onDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth) {
                    if (listener!=null) {
                        listener.onDaySelected(view, year, month, dayOfMonth);
                    }
                }

                @Override
                public void onEventDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth, List<DateEvent> list) {
                    if (listener!=null){
                        listener.onEventDaySelected(view,year,month,dayOfMonth, list);
                    }
                }

                @Override
                public void onViewChanged(long startDate, long endDate) {
                    //don't do anything i guess
                    if (listener!=null){
                        listener.onViewChanged(startDate,endDate);
                    }
                }

                @Override
                public void onEventSelected(DateEvent event) {
                    if (listener!=null){
                        listener.onEventSelected(event);
                    }
                }

                @Override
                public void onAddEvent(long date) {
                    //again nothing much to do here
                    if (listener!=null){
                        listener.onAddEvent(date);
                    }
                }
            });
        }

    }

    private void fillSynccEventsList(){
        int id=getCalendarID();
        //get all events and put into list to give the highlightcalendarview

        //TODO gets phantom events? fix it so it only has events that actually take place
        Cursor cursor=getActivity().getContentResolver().query(
                CalendarContract.Events.CONTENT_URI,
                new String[]{
                        CalendarContract.Events._ID,
                        CalendarContract.Events.TITLE,
                        CalendarContract.Events.DTSTART,
                        CalendarContract.Events.DTEND,
                        CalendarContract.Events.DESCRIPTION,
                        CalendarContract.Events.EVENT_LOCATION
                },
                CalendarContract.Events.CALENDAR_ID+"=1",
                null,
                null
        );

        if (cursor.moveToFirst()){
            do {
                synccEvents.add(new SynccEvent(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getLong(2),
                        cursor.getLong(3)
                ));

                synccEvents.get(synccEvents.size()-1).setDescription(cursor.getString(4));
                synccEvents.get(synccEvents.size()-1).setLocation(cursor.getString(5));
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    private int getCalendarID(){

        Cursor cursor=getActivity().getContentResolver().query(
                CalendarContract.Calendars.CONTENT_URI,
                new String[]{
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                        CalendarContract.Calendars.OWNER_ACCOUNT
                },
                null,
                null,
                null
        );

        //TODO shows events from the past - why? fix!

        if (cursor.moveToFirst()){
            cursor.moveToNext();
            cursor.moveToNext();
            test(cursor.getString(1));
        }

        return 0;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.calendar_menu,menu);
        showGlobalContextActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_go_to_today://go to today

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showGlobalContextActionBar(){
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    public CalendarFragment() {
    }

    public void setListener(customOnCalendarInteractionListener listener){
        this.listener=listener;
    }

    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }


    public interface customOnCalendarInteractionListener {
        void onDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth);
        void onEventDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth, List<DateEvent> list);
        void onViewChanged(long startDate, long endDate);
        void onEventSelected(DateEvent event);
        void onAddEvent(long date);
    }

}

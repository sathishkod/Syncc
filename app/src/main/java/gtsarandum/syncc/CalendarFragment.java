package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.cyclingsir.helper.calendar.DateEvent;
import de.cyclingsir.helper.calendar.HighlightCalendarView;


public class CalendarFragment extends Fragment
{
    //attr
    ArrayList<SynccEvent> synccEvents;
    private HighlightCalendarView highlightCalendarView;
    private FrameLayout frameLayout;
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
        }else {
            test("frameLayout=null");
        }

        if(highlightCalendarView!=null){
            //listener and events
            //TODO get events and display them in calender with .addEvents(List<? extends DateEvent>)
            //sets listener that calls internal listener set from mainactivity
            highlightCalendarView.setOnDateSelectedListener(new HighlightCalendarView.OnDateSelectedListener() {
                @Override
                public void onDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth) {
                    if (listener!=null) {
                        listener.onDaySelected(view, year, month, dayOfMonth);
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
                    //TODO look into this method and figure out what it does
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
        //get all events and put into list to give the highlightcalendarview
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
                highlightCalendarView.setDate(Calendar.getInstance().getTimeInMillis(),true,true);
                //TODO find working way to go to today when actionbar icon is clicked
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
        void onViewChanged(long startDate, long endDate);
        void onEventSelected(DateEvent event);
        void onAddEvent(long date);
    }

}

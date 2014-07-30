package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import de.cyclingsir.helper.calendar.DateEvent;
import de.cyclingsir.helper.calendar.HighlightCalendarView;


public class CalendarFragment extends Fragment
{
    //attr
    private HighlightCalendarView highlightCalendarView;
    private FrameLayout frameLayout;


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

        setHasOptionsMenu(true);

        if (frameLayout!=null) {
            highlightCalendarView=(HighlightCalendarView) frameLayout.findViewById(R.id.calendar);
        }else {
            test("frameLayout=null");
        }

        if(highlightCalendarView!=null){
            //listener and events

            highlightCalendarView.setOnDateSelectedListener(new HighlightCalendarView.OnDateSelectedListener() {
                @Override
                public void onDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth) {

                }

                @Override
                public void onViewChanged(long startDate, long endDate) {
                    //don't do anything i guess
                }

                @Override
                public void onEventSelected(DateEvent event) {
                    //TODO look into this method and figure out what it does
                }

                @Override
                public void onAddEvent(long date) {
                    //again nothing much to do here
                }
            });
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.calendar_menu,menu);
        showGlobalContextActionBar();
    }

    private void showGlobalContextActionBar(){
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    public CalendarFragment() {}
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

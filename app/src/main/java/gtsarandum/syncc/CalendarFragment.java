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

import com.vdesmet.lib.calendar.MultiCalendarView;
import com.vdesmet.lib.calendar.OnDayClickListener;

import java.util.Calendar;


public class CalendarFragment extends Fragment
    implements
        OnDayClickListener

{
    private MultiCalendarView multiCalendarView;
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
            multiCalendarView=(MultiCalendarView) frameLayout.findViewById(R.id.calendar);
        }else {
            test("frameLayout=null");

        }

        if(multiCalendarView!=null) {

            /*extendedCalendarView.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
                @Override
                public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {
                    Intent intent=new Intent(getActivity().getApplicationContext(),DayEventsDisplayActivity.class);
                    Calendar calendar= Calendar.getInstance();
                    calendar.set(day.getYear(),day.getMonth(),day.getDay());
                    intent.putExtra(DayEventsDisplayActivity.DAY,calendar.getTimeInMillis());
                    getActivity().startActivity(intent);
                }

                @Override
                public void onDayLongClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {

                }
            });*/

            multiCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
            multiCalendarView.setIndicatorVisible(true);
            Calendar fday=Calendar.getInstance();
            Calendar lday=Calendar.getInstance();
            fday.set(Calendar.DAY_OF_MONTH,1);
            lday.set(Calendar.MONTH,12*3);
            multiCalendarView.setFirstValidDay(fday);
            multiCalendarView.setLastValidDay(lday);
            multiCalendarView.setOnDayClickListener(this);
        } else {
            test("multiCalendarView=null");
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


    @Override
    public void onDayClick(long dayInMillis) {
        Toast.makeText(getActivity().getApplicationContext(), String.valueOf(dayInMillis), Toast.LENGTH_SHORT).show();
    }
}

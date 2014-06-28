package gtsarandum.syncc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;


public class CalendarFragment extends Fragment
{

    private ExtendedCalendarView extendedCalendarView;
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

        if (frameLayout!=null) {
            extendedCalendarView = (ExtendedCalendarView)frameLayout.findViewById(R.id.calendar);
        }else {
            test("frameLayout=null");

        }

        if(extendedCalendarView!=null) {
            extendedCalendarView.setGesture(1);
            extendedCalendarView.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
                @Override
                public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {
                    test("onClick...");
                }

                @Override
                public void onDayLongClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {

                }
            });
        } else {
            test("extendedCalendarView=null");
        }
    }

    public CalendarFragment() {}
    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }



}

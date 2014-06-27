package gtsarandum.syncc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tyczj.extendedcalendarview.ExtendedCalendarView;


public class CalendarFragment extends Fragment {

    private OnCalendarFragmentInteractionListener mListener;

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
            extendedCalendarView = (ExtendedCalendarView) frameLayout.findViewById(R.id.calendar);
        }else {
            test("frameLayout=null");

        }

        if(extendedCalendarView!=null) {
            extendedCalendarView.setGesture(1);
        } else {
            test("extendedCalendarView=null");
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCalendarFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCalendarFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCalendarFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onCalendarFragmentInteraction(Uri uri);
    }

    public CalendarFragment() {}
    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }

}

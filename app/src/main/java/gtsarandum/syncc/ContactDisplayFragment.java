package gtsarandum.syncc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ContactDisplayFragment extends Fragment {

    //keys
    public static final String CONTACT_ID="id";
    public static final String CONTACT_NAME="name";
    public static final String CONTACT_NUMBER="number";

    //attr
    private SynccContact synccContact;

    public ContactDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeSynccContact();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_display, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void makeSynccContact(){
        synccContact=new SynccContact(
                getArguments().getString(CONTACT_ID),
                getArguments().getString(CONTACT_NAME),
                getArguments().getString(CONTACT_NUMBER)
        );

        test(synccContact.getName());
    }

    private void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

}

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


public class ContactDisplayFragment extends Fragment {

    //keys
    public static final String CONTACT_ID="id";
    public static final String CONTACT_NAME="name";
    public static final String CONTACT_NUMBER="number";

    //attr
    private SynccContact synccContact;
    private FrameLayout frameLayout;

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
        frameLayout=(FrameLayout)inflater.inflate(R.layout.fragment_contact_display,container,false);
        return frameLayout;
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
        Bundle contact=getArguments();
        if (contact!=null) {
            synccContact = new SynccContact(
                    contact.getString(CONTACT_ID),
                    contact.getString(CONTACT_NAME),
                    contact.getString(CONTACT_NUMBER)
            );

            test(synccContact.getName());
        } else {
            test("no arguments set...");
        }
    }

    private void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

}

package gtsarandum.syncc;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactDisplayFragment extends Fragment {

    //keys
    public static final String CONTACT_ID="id";
    public static final String CONTACT_NAME="name";
    public static final String CONTACT_HAS_PHONE_NUMBER="boolean";
    public static final String CONTACT_NUMBER="number";
    public static final String CONTACT_PHOTO_ID="photoId";
    public static final String CONTACT_PHOTO_THUMBNAIL_URI="thumbnail";
    public static final String CONTACT_PHOTO_URI="photoUri";


    //attr
    private SynccContact synccContact;
    private FrameLayout frameLayout;

    //layout-attr
    TextView nameTextView;
    TextView numberTextView;
    ListView emailsList;

    public ContactDisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout=(FrameLayout)inflater.inflate(R.layout.fragment_contact_display,container,false);
        return frameLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        synccContact=bundleToContact();
        nameTextView=(TextView) frameLayout.findViewById(R.id.contactdetails_name);
        numberTextView=(TextView) frameLayout.findViewById(R.id.contactdetails_phone_number);
        emailsList=(ListView) frameLayout.findViewById(R.id.contactdetails_emails);

        nameTextView.setText(synccContact.getName());

        if (synccContact.isHasPhoneNumber()) {
            numberTextView.setText(synccContact.getNumber());
        } else {
            test("no numbers...");
        }

        /*
        if (synccContact.getEmails().size()>0) {
            ArrayAdapter<String> emailAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    synccContact.getEmails()
            );
            emailsList.setAdapter(emailAdapter);
            emailsList.setVisibility(View.GONE);
        } else {
            test("no emails...");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private SynccContact bundleToContact(){
        return new SynccContact(
                getActivity(),
                getArguments().getLong(CONTACT_ID),
                getArguments().getString(CONTACT_NAME),
                getArguments().getInt(CONTACT_HAS_PHONE_NUMBER)
        );
    }

    private void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

}

package gtsarandum.syncc;



import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactDisplayFragment extends Fragment {

    //attr
    private RelativeLayout relativeLayout;
    private FrameLayout phoneContainer;
    private FrameLayout emailContainer;
    private SynccContact synccContact;

    public static ContactDisplayFragment newInstance(){
        ContactDisplayFragment contactDisplayFragment=new ContactDisplayFragment();
        return contactDisplayFragment;
    }

    public ContactDisplayFragment() {
        // Required empty public constructor
    }

    public void setUp(SynccContact synccContact) {
        phoneContainer = (FrameLayout) relativeLayout.findViewById(R.id.phone_number_container);
        emailContainer = (FrameLayout) relativeLayout.findViewById(R.id.email_container);

        this.synccContact = synccContact;

        if (synccContact != null) {

        ArrayList<String> numbers = new ArrayList<String>();
        ArrayList<String> emails = new ArrayList<String>();

        for (int i = 0; i < synccContact.getNumberCount(); i++) {
            numbers.add(synccContact.getNumberAtPosition(i));
        }
        for (int j = 0; j < synccContact.getEmailCount(); j++) {
            emails.add(synccContact.getEmailAtPosition(j));
        }

        ListFragment numberList = new ListFragment();
        ListFragment emailList = new ListFragment();

        numberList.setListAdapter(new ArrayAdapter<String>(getActivity(), synccContact.getNumberCount(), numbers));
        emailList.setListAdapter(new ArrayAdapter<String>(getActivity(), synccContact.getEmailCount(), emails));

        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
        FragmentTransaction transaction2 = getFragmentManager().beginTransaction();

        transaction1.replace(R.id.phone_number_container, numberList);
        transaction2.replace(R.id.email_container, emailList);

        transaction1.commit();
        transaction2.commit();
        } else {
            test("synccContact is null");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        relativeLayout=(RelativeLayout) inflater.inflate(R.layout.fragment_contact_display, container, false);
        return relativeLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    private void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }
}

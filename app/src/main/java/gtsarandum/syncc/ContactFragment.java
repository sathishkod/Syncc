package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactFragment extends ListFragment {

    //attr
    private ContactAdapter contactAdapter;
    private customContactClickListener listener;
    private ArrayList<SynccContact> contacts;
    private Cursor contactCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactAdapter=new ContactAdapter(getActivity().getApplicationContext());
        contacts=new ArrayList<SynccContact>();
        contactCursor=getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.Phone._ID,//0
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,//1
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,//2
                        ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER,//3
                        ContactsContract.CommonDataKinds.Phone.NUMBER,//4
                        ContactsContract.Contacts.PHOTO_ID,//5
                        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,//6
                        ContactsContract.Contacts.PHOTO_URI
                },
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        //setListAdapter
        setListAdapter(contactAdapter);

        fillViewAndList();
    }


    private void fillViewAndList(){
        char cmp=' ';
        String name;
        String id;

        if (contactCursor!=null && contactCursor.moveToFirst()){
            for (int i=0;i<contactCursor.getCount();i++){
                name=contactCursor.getString(1);
                id=contactCursor.getString(0);
                if (cmp<name.charAt(0)){
                    cmp=name.charAt(0);
                    contactAdapter.addSectionHeaderItem(String.valueOf(cmp), null);
                }

                contactAdapter.addItem(name,id);

                //add contact to list


                contactCursor.moveToNext();
            }
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //enables custom actionbar menu
        setHasOptionsMenu(true);

        //LongClickListener
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener!=null){
                    listener.onContactLongClick(adapterView,view,i,l);
                }
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.contact_menu,menu);
        showGlobalContextActionBar();
    }
    private void showGlobalContextActionBar(){
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener!=null){
            //find out which contact was selected and obtain information on it to make SynccContact

            //create testcontact

            SynccContact synccContact=new SynccContact("testid", "testname",true, "test@email.test");

            listener.onContactClick(l,v,position,id,synccContact);
        }
    }

    public ContactFragment() {}
    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }

    public void setListener(customContactClickListener listener){
        this.listener=listener;
    }

    public interface customContactClickListener {
        void onContactClick(ListView l,View v,int position, long id, SynccContact synccContact);
        void onContactLongClick(AdapterView<?> adapterView, View view, int i, long l);
    }

}

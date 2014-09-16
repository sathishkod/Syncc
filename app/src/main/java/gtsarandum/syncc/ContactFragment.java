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
import android.widget.TextView;
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
                        ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER,//2
                        ContactsContract.CommonDataKinds.Phone.NUMBER,//3
                        ContactsContract.Contacts.PHOTO_ID,//4
                        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,//5
                        ContactsContract.Contacts.PHOTO_URI//6
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

                //add contact to contact list
                contacts.add(new SynccContact(
                        contactCursor.getString(0),
                        contactCursor.getString(1),
                        contactCursor.getInt(2),
                        contactCursor.getString(3),
                        contactCursor.getString(4),
                        contactCursor.getString(5),
                        contactCursor.getString(6)
                ));

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
            //TODO : filter out dividers... -.-

            //remove number of dividers from position
            TextView textView=(TextView) v.findViewById(R.id.text);
            position=position-getNumbersToRemove(textView.getText().charAt(0))-1;

            listener.onContactClick(l,v,position,id,contacts.get(getContactPositionByName(textView.getText().toString())));
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

    private int getContactPositionByName(String name){
        int i=0;

        while (i<contacts.size()){
            if (contacts.get(i).getName().equals(name)){
                break;
            } else {
                i++;
            }
        }

        return i;
    }

    private int getNumbersToRemove(char c){
        switch (c){
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            case 'i':
                return 9;
            case 'j':
                return 10;
            case 'k':
                return 11;
            case 'l':
                return 12;
            case 'm':
                return 13;
            case 'n':
                return 14;
            case 'o':
                return 15;
            case 'p':
                return 16;
            case 'q':
                return 17;
            case 'r':
                return 18;
            case 's':
                return 19;
            case 't':
                return 20;
            case 'u':
                return 21;
            case 'v':
                return 22;
            case 'w':
                return 23;
            case 'x':
                return 24;
            case 'y':
                return 25;
            case 'z':
                return 26;
            default:return 0;
        }
    }
}

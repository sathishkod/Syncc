package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactFragment extends ListFragment {

    //attr
    private ContactAdapter contactAdapter;
    private customContactClickListener listener;
    private ArrayList<SynccContact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactAdapter=new ContactAdapter(getActivity().getApplicationContext());
        contacts=new ArrayList<SynccContact>();

        fillContactList();

        //setListAdapter
        setListAdapter(contactAdapter);

        fillView();
    }

    private void fillContactList(){

    }

    private void fillView(){
        Cursor cursor=getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{
                    ContactsContract.CommonDataKinds.Phone._ID,//0
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,//1
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,//2
                    ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER,//3
                    ContactsContract.CommonDataKinds.Phone.NUMBER,//4
                    ContactsContract.Contacts.PHOTO_ID,//5
                    ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,//6
                    ContactsContract.Contacts.PHOTO_URI},//7
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        char cmp=' ';
        String name;
        String id;

        if (cursor!=null && cursor.moveToFirst()){
            for (int i=0;i<cursor.getCount();i++){
                name=cursor.getString(1);
                id=cursor.getString(0);
                if (cmp<name.charAt(0)){
                    cmp=name.charAt(0);
                    contactAdapter.addSectionHeaderItem(String.valueOf(cmp), null);
                }

                contactAdapter.addItem(name,id);

                cursor.moveToNext();
            }
        }

    }
/*
    private Bitmap loadContactPhoto(ContentResolver contentResolver,long id){
        Uri uri= ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream inputStream= ContactsContract.Contacts.openContactPhotoInputStream(contentResolver,uri);
        if(inputStream==null){
            return null;
        }

        return BitmapFactory.decodeStream(inputStream);
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
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
            listener.onContactClick(l,v,position,id);
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
        void onContactClick(ListView l,View v,int position, long id);
    }

}

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


public class ContactFragment extends ListFragment {

    ContactAdapter contactAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactAdapter=new ContactAdapter(getActivity().getApplicationContext());

        //setListAdapter
        setListAdapter(contactAdapter);

        fillView();
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
        String photo;

        if (cursor!=null && cursor.moveToFirst()){
            for (int i=0;i<cursor.getCount();i++){
                name=cursor.getString(1);
                photo=cursor.getString(6);
                if (cmp<name.charAt(0)){
                    cmp=name.charAt(0);
                    contactAdapter.addSectionHeaderItem(String.valueOf(cmp));
                }

                contactAdapter.addItem(name/*,loadContactPhoto(getActivity().getApplicationContext().getContentResolver(),cursor.getInt(0))*/);

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
        super.onListItemClick(l, v, position, id);

    }

    public ContactFragment() {}
    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }

}

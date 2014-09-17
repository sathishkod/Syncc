package gtsarandum.syncc;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Nicolas on 8/8/2014.
 */
public class SynccContact {

    //attr
    private long id;
    private String name;
    private boolean hasPhoneNumber;
    private String number;
    private boolean hasPhotoInfo;
    private String photoId;
    private String photoThumbUri;
    private String photoUri;

    private ArrayList<String> emails;

    //extra
    private Context context;

    //constructor
    public SynccContact(Context context, long id, String name, int hasPhoneNumber){
        this.context=context;
        this.id=id;
        this.name=name;
        switch (hasPhoneNumber){
            case 1:
                this.hasPhoneNumber=true;
                break;
            case 2:
                this.hasPhoneNumber=false;
                break;
            default:break;
        }
        this.hasPhotoInfo=true;

        this.emails=new ArrayList<String>();
    }

    //getter
    public long getId(){return id;}

    public String getName(){return name;}

    public boolean isHasPhoneNumber(){return hasPhoneNumber;}

    public int getHasPhoneNumberValue(){
        if (hasPhoneNumber){
            return 1;
        } else {
            return 0;
        }
    }

    public String getNumber(){
        //gets numbers by id
        if (hasPhoneNumber){

            ContentResolver contentResolver=context.getContentResolver();
            Cursor cursor=contentResolver.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    null,
                    ContactsContract.Contacts._ID+" = "+id,
                    null,
                    null
            ); //TODO : resolve empty cursor error
            //contact seems to have no data available?

            if (cursor.moveToFirst()){

                cursor.moveToNext();

                String contactId=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor phones=contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId,
                        null,
                        null);

                if (phones.moveToFirst()){
                    while (phones.moveToNext()) {
                        this.number=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }

                phones.close();
            } else {
                test("cursor error...");
            }

            cursor.close();

            return number;
        } else {
            return null;
        }
    }

    public ArrayList<String> getEmails(){
        //gets emails by id
        ContentResolver contentResolver=context.getContentResolver();
        Cursor cursor=contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                ContactsContract.Contacts._ID+" = "+id,
                null,
                null
        );

        if (cursor.moveToFirst()) {

            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            Cursor emailCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null,
                    null
            );

            if (emailCursor.moveToFirst()){
                do {
                    this.emails.add(emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)));
                } while (emailCursor.moveToNext());
            }
        }

        return this.emails;
    }

    public String getPhotoId(){
        return photoId;
    }

    public String getPhotoThumbUri(){
        return photoThumbUri;
    }

    public String getPhotoUri(){
        return photoUri;
    }

    public boolean isHasPhotoInfo(){
        return hasPhotoInfo;
    }

    //setter
    public void setName(String name){this.name=name;}

    public void addEmail(String email){
        this.emails.add(email);
    }

    public void addPhotoInfo(String photoId, String photoThumbUri, String photoUri) {
        //replaces old data
        this.photoThumbUri=photoThumbUri;
        this.photoUri=photoUri;
        this.photoId=photoId;
        this.hasPhotoInfo=true;
    }

    //other methods
    public void deleteNumbers(){
        this.number="";
        this.hasPhoneNumber=false;
    }

    public void deleteEmails(){
        this.emails.clear();
    }

    private void test(CharSequence charSequence){
        Toast.makeText(context,charSequence,Toast.LENGTH_SHORT).show();
    }

}

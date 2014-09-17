package gtsarandum.syncc;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

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
    private ArrayList<String> numbers;
    private boolean hasPhotoInfo;
    private String photoId;
    private String photoThumbUri;
    private String photoUri;

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

    //TODO : update methods to get data by contact id

    public ArrayList<String> getNumbers(){
        if (hasPhoneNumber){

            ContentResolver contentResolver=context.getContentResolver();
            Cursor cursor=contentResolver.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    null,
                    ContactsContract.Contacts._ID+" = "+id,
                    null,
                    null
            );

            if (cursor.moveToFirst()){

                String contactId=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor phones=contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = "+contactId,
                        null,
                        null);

                if (phones.moveToFirst()){
                    do {
                        this.numbers.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    } while (phones.moveToNext());
                }
            }

            return numbers;
        } else {
            return null;
        }
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

    public void addNumber(String number){
        //replaces old number
        this.numbers.add(number);
        this.hasPhoneNumber=true;
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
        this.numbers.clear();
        this.hasPhoneNumber=false;
    }

}

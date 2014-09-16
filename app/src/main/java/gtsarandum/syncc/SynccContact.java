package gtsarandum.syncc;

import android.graphics.Bitmap;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Nicolas on 8/8/2014.
 */
public class SynccContact {

    //attr
    private String id;
    private String name;
    private boolean hasPhoneNumber;
    private String number;
    private String photoId;
    private String photoThumbUri;
    private String photoUri;

    //constructors

    public SynccContact(String id, String name, int hasPhoneNumber, String number){
        this.id=id;
        this.name=name;
        if(hasPhoneNumber==1){
            this.hasPhoneNumber=true;
            this.number=number;
        } else {
            this.hasPhoneNumber=false;
            this.number="";
        }
    }

    //constructor w/ everything
    public SynccContact(String id, String name, int hasPhoneNumber, String number,
                        String photoId, String photoThumbUri, String photoUri){
        this.id=id;
        this.name=name;
        if(hasPhoneNumber==1){
            this.hasPhoneNumber=true;
            this.number=number;
        } else {
            this.hasPhoneNumber=false;
            this.number="";
        }
        this.photoId=photoId;
        this.photoThumbUri=photoThumbUri;
        this.photoUri=photoUri;
    }

    //getter
    public String getId(){return id;}

    public String getName(){return name;}

    public boolean isHasPhoneNumber(){return hasPhoneNumber;}

    public String getNumber(){
        if (hasPhoneNumber){
            return number;
        } else {
            return null;
        }
    }

    public String getPhotoId(){return photoId;}

    public String getPhotoThumbUri(){return photoThumbUri;}

    public String getPhotoUri(){return photoUri;}

    //setter
    public void setName(String name){this.name=name;}

    public void setNumber(String number){
        //replaces old number
        this.number=number;
        this.hasPhoneNumber=true;
    }

    //other methods
    public void deleteNumber(){
        this.number="";
        this.hasPhoneNumber=false;
    }

}

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
    private ArrayList<String> numbers;
    private String photoId;
    private String photoUri;
    private ArrayList<String> emails;

    //constructors
    public SynccContact(String id, String name){
        this.id=id;
        this.name=name;
    }

    public SynccContact(String id, String name, String number){
        this.id=id;
        this.name=name;
        numbers=new ArrayList<String>();
        numbers.add(number);
    }

    public SynccContact(String id, String name, String number, String email){
        this.id=id;
        this.name=name;
        numbers=new ArrayList<String>();
        emails=new ArrayList<String>();
        numbers.add(number);
        emails.add(email);
    }


    //getter
    public String getId(){return id;}

    public String getName(){return name;}

    public String getNumberAtPosition(int i){
        if (i>numbers.size()){
            return null;
        } else {
            return numbers.get(i);
        }
    }

    public ArrayList<String> getAllNumbers(){return numbers;}

    public String getEmailAtPosition(int i){
        if (i>emails.size()){
            return null;
        } else {
            return emails.get(i);
        }
    }

    public ArrayList<String> getAllEmails(){return emails;}

    public int getNumberCount(){return numbers.size();}

    public int getEmailCount(){return emails.size();}

    //setter
    public void setName(String name){this.name=name;}

    public void setNumbers(ArrayList<String> numbers){this.numbers=numbers;}

    public void setEmails(ArrayList<String> emails){this.emails=emails;}

    //adder
    public void addNumber(String number){numbers.add(number);}

    public void addEmail(String email){emails.add(email);}

}

package gtsarandum.syncc;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Nicolas on 8/8/2014.
 */
public class SynccContact {

    //attr
    private String name;
    private ArrayList<String> numbers;
    private ArrayList<String> emails;
    private Bitmap photo;

    //constructors
    public SynccContact(String name){
        this.name=name;
    }

    public SynccContact(String name, String number){
        this.name=name;
        numbers=new ArrayList<String>();
        numbers.add(number);
    }

    public SynccContact(String name, String number, String email){
        this.name=name;
        numbers=new ArrayList<String>();
        emails=new ArrayList<String>();
        numbers.add(number);
        emails.add(email);
    }

    public SynccContact(String name, String number, Bitmap photo){
        this.name=name;
        this.numbers=new ArrayList<String>();
        numbers.add(number);
        this.photo=photo;
    }

    //getter
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

    public void setPhoto(Bitmap photo){this.photo=photo;}

    //adder
    public void addNumber(String number){numbers.add(number);}

    public void addEmail(String email){emails.add(email);}

}

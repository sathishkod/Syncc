package gtsarandum.syncc;

/**
 * Created by root on 30.08.14.
 */
public class SynccNote {

    //attr
    private String id;
    private String text;
    private String title;

    public SynccNote(String id, String text, String title){
        this.id=id;
        this.text=text;
        this.title=title;
    }

    //getter
    public String getID(){return id;}

    public String getText(){return text;}

    public String getTitle(){return title;}

    //setter
    public void setTitle(String title){this.title=title;}

    public void setText(String text){this.text=text;}
}

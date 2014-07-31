package gtsarandum.syncc;

import de.cyclingsir.helper.calendar.DateEvent;

/**
 * Created by Nicolas on 7/31/2014.
 */
public class SynccEvent implements DateEvent {

    //attr
    private String id;
    private String title;
    private long begin;
    private long end;
    private String description;
    private String location;

    public SynccEvent(String id, String title, long begin, long end){
        this.id=id;
        this.title=title;
        this.begin=begin;
        this.end=end;
    }

    public SynccEvent(String id, long begin, long end){
        this.id=id;
        this.begin=begin;
        this.end=end;
    }

    //getter
    public String getId(){return id;}
    public String getTitle(){return title;}
    public long getEndDate(){return end;}
    public String getDescription(){return description;}
    public String getLocation(){return location;}

    //setter
    public void setDescription(String description){
        this.description=description;
    }

    public void setLocation(String location){
        this.location=location;
    }


    @Override
    public long getDate() {
        return this.begin;
    }
}

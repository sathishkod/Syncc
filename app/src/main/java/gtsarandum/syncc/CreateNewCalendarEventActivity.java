package gtsarandum.syncc;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;
import com.doomonafireball.betterpickers.recurrencepicker.EventRecurrence;
import com.doomonafireball.betterpickers.recurrencepicker.EventRecurrenceFormatter;
import com.doomonafireball.betterpickers.recurrencepicker.RecurrencePickerDialog;

import java.util.Calendar;
import java.util.TimeZone;

public class CreateNewCalendarEventActivity extends FragmentActivity {

    //elements inside the view
    private ScrollView scrollView;
    private EditText eventTitleEditText;                //event_title_edit_text
    private EditText eventLocationEditText;             //event_location_edit_text
    private TextView fromDatePicker;                    //from_date_picker - onclicklistener and text
    private TextView fromTimePicker;                    //from_time_picker - onclicklistener and text
    private TextView toDatePicker;                      //to_date_picker - onclicklistener and text
    private TextView toTimePicker;                      //to_time_picker - onclicklistener and text
    private CheckBox allDayCheck;                       //all_day_check - onstatechangedlistener
    private Spinner reminderSpinner;                    //reminder_spinner - fill with items through adapter
    private EditText descriptionEditText;               //description_edit_text
    private TextView recurrence;

    //necessary variables for view elements
    private Calendar fromDateTimeValue;
    private Calendar toDateTimeValue;
    private boolean allDayCheckValue;
    private int reminderPosition;
    private EventRecurrence eventRecurrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scrollView=(ScrollView)getLayoutInflater().inflate(R.layout.activity_create_new_calendar_event,null,false);
        setContentView(scrollView);
        init();
    }
    private void init(){
        //get all the views!
        eventTitleEditText=(EditText) scrollView.findViewById(R.id.event_title_edit_text);
        eventLocationEditText=(EditText) scrollView.findViewById(R.id.event_location_edit_text);
        fromDatePicker=(TextView) scrollView.findViewById(R.id.from_date_picker);
        fromTimePicker=(TextView) scrollView.findViewById(R.id.from_time_picker);
        toDatePicker=(TextView) scrollView.findViewById(R.id.to_date_picker);
        toTimePicker=(TextView) scrollView.findViewById(R.id.to_time_picker);
        allDayCheck=(CheckBox) scrollView.findViewById(R.id.all_day_check);
        reminderSpinner=(Spinner) scrollView.findViewById(R.id.reminder_spinner);
        descriptionEditText=(EditText) scrollView.findViewById(R.id.description_edit_text);
        recurrence=(TextView) scrollView.findViewById(R.id.recurrence);

        //init values
        fromDateTimeValue=Calendar.getInstance();
        toDateTimeValue=Calendar.getInstance();
        toDateTimeValue.set(Calendar.HOUR, fromDateTimeValue.get(Calendar.HOUR)+1); //Termin geht default eine stunde lang
        allDayCheckValue=false;
        reminderPosition=0;
        eventRecurrence=new EventRecurrence();

        //fill date and time pickers and recurrende with text
        updatePickers();
        recurrence.setText(getResources().getString(R.string.set_recurrence));

        //adapter for spinner
        /*reminderSpinner.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.reminder_options, R.layout.custom_spinner_item));*/

        //onItemSelected for spinner
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reminderPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //onClickListener for TextView
        fromDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open datepicker and change text in TextView
                CalendarDatePickerDialog calendarDatePickerDialog=new CalendarDatePickerDialog();
                calendarDatePickerDialog.setOnDateSetListener(new CalendarDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {
                        fromDateTimeValue.set(i,i2,i3);
                        updatePickers();
                    }
                });
                calendarDatePickerDialog.show(getSupportFragmentManager(),"tag");
            }
        });

        fromTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open timepicker and change text in TextView
                RadialTimePickerDialog radialTimePickerDialog=new RadialTimePickerDialog();
                radialTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
                        fromDateTimeValue.set(Calendar.HOUR_OF_DAY,i);
                        fromDateTimeValue.set(Calendar.MINUTE,i2);
                        updatePickers();
                    }
                });
                radialTimePickerDialog.show(getSupportFragmentManager(),"tag");
            }
        });

        toDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open datepicker and chane text in TextView
                CalendarDatePickerDialog calendarDatePickerDialog=new CalendarDatePickerDialog();
                calendarDatePickerDialog.setOnDateSetListener(new CalendarDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(CalendarDatePickerDialog calendarDatePickerDialog, int i, int i2, int i3) {
                        toDateTimeValue.set(i,i2,i3);
                        updatePickers();
                    }
                });
                calendarDatePickerDialog.show(getSupportFragmentManager(),"tag");
            }
        });

        toTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open timepicker and change text in TextView
                RadialTimePickerDialog radialTimePickerDialog=new RadialTimePickerDialog();
                radialTimePickerDialog.setOnTimeSetListener(new RadialTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i2) {
                        toDateTimeValue.set(Calendar.HOUR_OF_DAY,i);
                        toDateTimeValue.set(Calendar.MINUTE,i2);
                        updatePickers();
                    }
                });
                radialTimePickerDialog.show(getSupportFragmentManager(),"tag");
            }
        });

        recurrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecurrencePickerDialog recurrencePickerDialog=new RecurrencePickerDialog();
                recurrencePickerDialog.setOnRecurrenceSetListener(new RecurrencePickerDialog.OnRecurrenceSetListener() {
                    @Override
                    public void onRecurrenceSet(String s) {
                        if (s!=null) {
                            eventRecurrence.parse(s);
                            updateRecurrence(s);
                        } else {
                            Toast.makeText(getApplicationContext(),getString(R.string.no_recurrence),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                recurrencePickerDialog.show(getSupportFragmentManager(),"tag");
            }
        });

        //set listener for checkbox
        allDayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set value for dedicated boolean
                if(isChecked){
                    allDayCheckValue=true;
                    fromTimePicker.setVisibility(View.GONE);
                    toTimePicker.setVisibility(View.GONE);
                } else {
                    allDayCheckValue=false;
                    fromTimePicker.setVisibility(View.VISIBLE);
                    toTimePicker.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void updatePickers(){
        if(fromDateTimeValue.getTimeInMillis()>toDateTimeValue.getTimeInMillis()){
            toDateTimeValue.setTimeInMillis(fromDateTimeValue.getTimeInMillis());
        }

        String date;
        String time;

        String [] months=getResources().getStringArray(R.array.month_short);

        //fromDateTime
        date=fromDateTimeValue.get(Calendar.DAY_OF_MONTH)+" "
                +months[fromDateTimeValue.get(Calendar.MONTH)]+" "
                +fromDateTimeValue.get(Calendar.YEAR);

        fromDatePicker.setText(date);

        if (fromDateTimeValue.get(Calendar.HOUR_OF_DAY)>10 && fromDateTimeValue.get(Calendar.MINUTE)>10){
            time=fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +fromDateTimeValue.get(Calendar.MINUTE);
        } else if (fromDateTimeValue.get(Calendar.HOUR_OF_DAY)<10 && fromDateTimeValue.get(Calendar.MINUTE)>10){
            time="0"+fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +fromDateTimeValue.get(Calendar.MINUTE);
        } else if (fromDateTimeValue.get(Calendar.HOUR_OF_DAY)>10 && fromDateTimeValue.get(Calendar.MINUTE)<10){
            time=fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +"0"+fromDateTimeValue.get(Calendar.MINUTE);
        } else if (fromDateTimeValue.get(Calendar.HOUR_OF_DAY)<10 && fromDateTimeValue.get(Calendar.MINUTE)<10){
            time="0"+fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +"0"+fromDateTimeValue.get(Calendar.MINUTE);
        } else {
            time=fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +fromDateTimeValue.get(Calendar.MINUTE);
        }

        fromTimePicker.setText(time);

        //toDateTime
        date=toDateTimeValue.get(Calendar.DAY_OF_MONTH)+" "
                +months[toDateTimeValue.get(Calendar.MONTH)]+" "
                +toDateTimeValue.get(Calendar.YEAR);

        toDatePicker.setText(date);

        if (toDateTimeValue.get(Calendar.HOUR_OF_DAY)>10 && toDateTimeValue.get(Calendar.MINUTE)>10){
            time=toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +toDateTimeValue.get(Calendar.MINUTE);
        } else if (toDateTimeValue.get(Calendar.HOUR_OF_DAY)<10 && toDateTimeValue.get(Calendar.MINUTE)>10){
            time="0"+toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +toDateTimeValue.get(Calendar.MINUTE);
        } else if (toDateTimeValue.get(Calendar.HOUR_OF_DAY)>10 && toDateTimeValue.get(Calendar.MINUTE)<10){
            time=toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +"0"+toDateTimeValue.get(Calendar.MINUTE);
        } else if (toDateTimeValue.get(Calendar.HOUR_OF_DAY)<10 && toDateTimeValue.get(Calendar.MINUTE)<10){
            time="0"+toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +"0"+toDateTimeValue.get(Calendar.MINUTE);
        } else {
            time=toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                    +toDateTimeValue.get(Calendar.MINUTE);
        }

        toTimePicker.setText(time);
    }

    private void updateRecurrence(String s){
        String repeatString = "";
        if (!TextUtils.isEmpty(s)) {
            repeatString = EventRecurrenceFormatter.getRepeatString(this, getResources(), eventRecurrence, true);
        }

        recurrence.setText(repeatString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_new_calendar_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_accept:
                saveCalendarEvent();
                break;
            case R.id.action_cancel:
                finish();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveCalendarEvent(){

        ContentValues contentValues=new ContentValues();
        //information for the event
        contentValues.put(CalendarContract.Events.TITLE,eventTitleEditText.getText().toString());
        contentValues.put(CalendarContract.Events.EVENT_LOCATION,eventLocationEditText.getText().toString());
        contentValues.put(CalendarContract.Events.DTSTART,fromDateTimeValue.getTimeInMillis());
        contentValues.put(CalendarContract.Events.DTEND,toDateTimeValue.getTimeInMillis());
        contentValues.put(CalendarContract.Events.ALL_DAY,allDayCheckValue);
        contentValues.put(CalendarContract.Events.DESCRIPTION,descriptionEditText.getText().toString());
        contentValues.put(CalendarContract.Events.CALENDAR_ID,1);
        contentValues.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        //insert event into calendar
        Uri uri=getContentResolver().insert(CalendarContract.Events.CONTENT_URI,contentValues);

        //set recurrence
        //set reminder




        finish();
    }
}

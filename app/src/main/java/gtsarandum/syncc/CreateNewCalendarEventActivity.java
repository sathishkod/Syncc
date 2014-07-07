package gtsarandum.syncc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.doomonafireball.betterpickers.radialtimepicker.RadialPickerLayout;
import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

import java.util.Calendar;

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
    private Spinner repetitionSpinner;                  //repetition_spinner - fill with items through adapter
    private Spinner reminderSpinner;                    //reminder_spinner - fill with items through adapter
    private EditText descriptionEditText;               //description_edit_text

    //necessary variables for view elements
    private Calendar fromDateTimeValue;
    private Calendar toDateTimeValue;
    private boolean allDayCheckValue;
    private int reminderPosition, repetitionPosition;


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
        repetitionSpinner=(Spinner) scrollView.findViewById(R.id.repetition_spinner);
        reminderSpinner=(Spinner) scrollView.findViewById(R.id.reminder_spinner);
        descriptionEditText=(EditText) scrollView.findViewById(R.id.description_edit_text);

        //init values
        fromDateTimeValue=Calendar.getInstance();
        toDateTimeValue=Calendar.getInstance();
        toDateTimeValue.set(Calendar.HOUR, fromDateTimeValue.get(Calendar.HOUR)+1); //Termin geht default eine stunde lang
        allDayCheckValue=false;
        reminderPosition=0;
        repetitionPosition=0;

        //adapter for spinners
        reminderSpinner.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.reminder_options, R.layout.custom_spinner_item));

        repetitionSpinner.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.repetition_options, R.layout.custom_spinner_item));

        //fill date and time pickers with text
        updatePickers();

        //onItemSelected for spinners
        reminderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reminderPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        repetitionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repetitionPosition=position;
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
        updateFromPickers();
        updateToPickers();
    }

    private void updateFromPickers(){
        String date;
        String time;

        String [] months=getResources().getStringArray(R.array.month_short);

        date=fromDateTimeValue.get(Calendar.DAY_OF_MONTH)+" "
                +months[fromDateTimeValue.get(Calendar.MONTH)]+" "
                +fromDateTimeValue.get(Calendar.YEAR);

        fromDatePicker.setText(date);

        time=fromDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                +fromDateTimeValue.get(Calendar.MINUTE);

        fromTimePicker.setText(time);
    }

    private void updateToPickers(){
        String date;
        String time;

        String[] months=getResources().getStringArray(R.array.month_short);

        date=toDateTimeValue.get(Calendar.DAY_OF_MONTH)+" "
                +months[toDateTimeValue.get(Calendar.MONTH)]+" "
                +toDateTimeValue.get(Calendar.YEAR);

        toDatePicker.setText(date);

        time=toDateTimeValue.get(Calendar.HOUR_OF_DAY)+":"
                +toDateTimeValue.get(Calendar.MINUTE);

        toTimePicker.setText(time);

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
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveCalendarEvent(){
        finish();
    }
}

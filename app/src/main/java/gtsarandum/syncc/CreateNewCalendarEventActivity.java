package gtsarandum.syncc;

import android.app.Activity;
import android.os.Bundle;
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

public class CreateNewCalendarEventActivity extends Activity {

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

        //adapter for spinners
        reminderSpinner.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.reminder_options, R.layout.custom_spinner_item));

        repetitionSpinner.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.repetition_options, R.layout.custom_spinner_item));

        //onItemSelected for spinners
        reminderSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //change value
            }
        });

        repetitionSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //chane value
            }
        });

        //onClickListener for TextViews
        fromDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open datepicker and change text in TextView
            }
        });

        fromTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open timepicker and change text in TextView
            }
        });

        toDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open datepicker and chane text in TextView
            }
        });

        toTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open timepicker and change text in TextView
            }
        });

        //set listener for checkbox
        allDayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set value for dedicated boolean
            }
        });
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

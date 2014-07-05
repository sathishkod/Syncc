package gtsarandum.syncc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class CreateNewCalendarEventActivity extends Activity {

    ScrollView scrollView;
    EditText title;//event_title_edit_text
    EditText location;//event_location_edit_text
    TextView datePicker;//from_date_picker
    //from_time_picker
    //to_date_picker
    //to_time_picker
    //all_day_check
    //repetition_spinner
    //reminder_spinner
    //description_edit_text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_calendar_event);
    }

    @Override
    public void onStart(){
        super.onStart();

        scrollView=(ScrollView)findViewById(R.id.scrollview);
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

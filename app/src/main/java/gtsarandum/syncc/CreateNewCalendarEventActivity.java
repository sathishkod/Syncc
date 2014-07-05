package gtsarandum.syncc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

public class CreateNewCalendarEventActivity extends Activity {

    ScrollView scrollView;

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

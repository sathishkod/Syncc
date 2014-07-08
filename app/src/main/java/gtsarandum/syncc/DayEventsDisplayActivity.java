package gtsarandum.syncc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class DayEventsDisplayActivity extends Activity {

    public static final String DAY="DAY";
    private long dayInMillis;
    private Calendar calendar=Calendar.getInstance();
    private CardListView cardListView;
    private CardArrayAdapter cardArrayAdapter;
    private ArrayList<Card> cards;
    private ArrayList<CalendarContract.Events> events;
    private int eventCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_events_display);

        View view=findViewById(R.id.relativeLayout);
        View root=view.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));

        Intent intent=getIntent();
        dayInMillis=intent.getLongExtra(DAY,0);
        calendar.setTimeInMillis(dayInMillis);

        /*Day day=new Day(getApplicationContext(),calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));

        eventCount=day.getNumOfEvenets();
        test(eventCount+"");
        events=day.getEvents();*/

        cardListView=(CardListView)findViewById(R.id.event_list);

        cards=new ArrayList<Card>();
        cardArrayAdapter=new CardArrayAdapter(getApplicationContext(),cards);

        cardListView.setAdapter(cardArrayAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.day_events_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getApplicationContext(), charSequence, Toast.LENGTH_SHORT).show();
    }
}

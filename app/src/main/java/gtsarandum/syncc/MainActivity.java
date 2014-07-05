package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;


public class MainActivity extends Activity
        implements
        NavigationDrawerFragment.NavigationDrawerCallbacks

{


    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view=findViewById(R.id.container);
        View root=view.getRootView();
        root.setBackgroundColor(getResources().getColor(android.R.color.white));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment=null;

        switch (position){
            case 0://open calendar
                fragment=new CalendarFragment();
                break;
            case 1://open contacts
                fragment=new ContactFragment();
                break;
            case 2://open notes
                fragment=new NoteFragment();
                break;
            case 3://login
                openLogin();
                break;
            case 4://Settings
                openSettings();
                break;
            default:break;
        }

        if(fragment!=null){
            replaceContainer(fragment);
        } else {
            test("fragment=null");
        }

        onSectionAttached(position);
    }

    private void replaceContainer(Fragment fragment){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
            default:break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings://settings
                openSettings();
                break;
            case R.id.action_new_event://new event
                newCalendarEvent();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newCalendarEvent(){//update to create activity and give all options to create event
        /*Calendar begin =Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin.getTimeInMillis());

        startActivity(intent);*/

        Intent intent=new Intent(getApplicationContext(),CreateNewCalendarEventActivity.class);
        startActivity(intent);
    }

    private void openSettings(){

    }

    private void openLogin(){
        Intent intent=new Intent(getApplicationContext(),LiveLoginActivity.class);
        startActivity(intent);
    }

    private void logout(){

    }

    //makes a toast that says the given charSequence
    public void test(CharSequence charSequence){
        Toast.makeText(getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

    //calender callbacks

    public class OnCalendarDayClickListener implements ExtendedCalendarView.OnDayClickListener

    {
        @Override
        public void onDayClicked (AdapterView < ? > adapter, View view,int position, long id, Day day){
        test("adfadsfadf");
        //calenderClickedToMainListener.onDayClicked(adapter,view,position,id,day);
    }

        @Override
        public void onDayLongClicked (AdapterView < ? > adapter, View view,int position,long id, Day day){
        //calenderClickedToMainListener.onDayLongClicked(adapter,view,position,id,day);
    }

    }

}

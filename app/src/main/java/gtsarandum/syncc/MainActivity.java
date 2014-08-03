package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import de.cyclingsir.helper.calendar.DateEvent;
import de.cyclingsir.helper.calendar.HighlightCalendarView;


public class MainActivity extends Activity
        implements
        NavigationDrawerFragment.NavigationDrawerCallbacks
{


    private NavigationDrawerFragment mNavigationDrawerFragment;
    private InformationDrawer informationDrawer;
    private DrawerLayout drawerLayout;

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
        informationDrawer=(InformationDrawer)
                getFragmentManager().findFragmentById(R.id.information_drawer);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //set up info drawer
        informationDrawer.setUp((DrawerLayout)findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position){
            case 0://open calendar

                CalendarFragment calendarFragment=new CalendarFragment();
                //setup
                calendarFragment.setListener(new CalendarFragment.customOnCalendarInteractionListener() {
                    @Override
                    public void onDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth) {
                        //create AlertDialog - create new event? yes|no
                    }

                    @Override
                    public void onEventDaySelected(HighlightCalendarView view, int year, int month, int dayOfMonth, List<DateEvent> list){
                        test(String.valueOf(list.size()));
                        drawerLayout.openDrawer(GravityCompat.END);
                    }

                    @Override
                    public void onViewChanged(long startDate, long endDate) {

                    }

                    @Override
                    public void onEventSelected(DateEvent event) {

                    }

                    @Override
                    public void onAddEvent(long date) {

                    }
                });
                replaceContainer(calendarFragment);
                break;
            case 1://open contacts
                ContactFragment contactFragment=new ContactFragment();
                //setup
                contactFragment.setListener(new ContactFragment.customContactClickListener() {
                    @Override
                    public void onContactClick(ListView l, View v, int position, long id) {
                        //create fragment and update content in information drawer
                        ContactDisplayFragment contactDisplayFragment=new ContactDisplayFragment();
                        //setup
                        contactDisplayFragment.setUp();
                        //update content
                        informationDrawer.updateContent(contactDisplayFragment);
                        //open informationdrawer
                        drawerLayout.openDrawer(GravityCompat.END);
                    }
                });
                replaceContainer(contactFragment);
                break;
            case 2://open notes
                NoteFragment noteFragment=new NoteFragment();

                replaceContainer(noteFragment);
                break;
            case 3://login
                openLogin();
                break;
            case 4://Settings
                openSettings();
                break;
            default:break;
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
            case R.id.action_go_to_today://go to today

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void newCalendarEvent(){

        Intent intent=new Intent(getApplicationContext(),CreateNewCalendarEventActivity.class);
        intent.putExtra(CreateNewCalendarEventActivity.HAS_DATE,false);
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

}

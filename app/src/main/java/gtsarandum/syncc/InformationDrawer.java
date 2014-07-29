package gtsarandum.syncc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class InformationDrawer extends Fragment {

    //attr
    private DrawerLayout drawerLayout;
    private Fragment fragment;
    private RelativeLayout relativeLayout;
    private int width;


    public void setUp(DrawerLayout drawerLayout){
        this.drawerLayout=drawerLayout;

        //set shadow - reverse shadow
        this.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow_reverse, GravityCompat.END);
        //update content with placeholder
        PlaceholderFragment placeholderFragment=new PlaceholderFragment();

        updateContent(placeholderFragment);

        //set width to be screen width-actionbar width
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.width=displayMetrics.widthPixels-getActivity().getActionBar().getHeight();
    }

    public void updateContent(Fragment fragment){
        this.fragment=fragment;
        replaceInfoContainer(this.fragment);
    }

    public InformationDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        relativeLayout=(RelativeLayout)inflater.inflate(R.layout.fragment_information_drawer,container,false);
        return relativeLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        relativeLayout.setMinimumWidth(this.width);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void replaceInfoContainer(Fragment fragment){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.info_container,fragment);
        transaction.commit();
    }

}

package gtsarandum.syncc;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class InformationDrawer extends Fragment {

    //attr


    public void setUp(DrawerLayout drawerLayout){

        //set shadow - reverse shadow
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow_reverse, GravityCompat.END);
        //update content with placeholder
        PlaceholderFragment placeholderFragment=new PlaceholderFragment();
        Bundle bundle=new Bundle();
        bundle.putString(PlaceholderFragment.TEXT_KEY,getResources().getString(R.string.info_placeholder_text));
        placeholderFragment.setArguments(bundle);

        updateContent(placeholderFragment);
    }

    public void updateContent(Fragment fragment){
        replaceInfoContainer(fragment);
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
        return inflater.inflate(R.layout.fragment_information_drawer,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
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

    private void test(CharSequence charSequence){
        Toast.makeText(getActivity().getApplicationContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

}

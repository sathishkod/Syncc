package gtsarandum.syncc;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

//displays a textview with

public class PlaceholderFragment extends Fragment {

    //keys
    public static final String TEXT_KEY="text";
    //attr
    private FrameLayout frameLayout;
    private String text;

    public PlaceholderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frameLayout=(FrameLayout) inflater.inflate(R.layout.fragment_placeholder,container,false);
        return frameLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        TextView textView=(TextView)frameLayout.findViewById(R.id.placeholder_textview);
        textView.setText(text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        text=this.getArguments().getString(TEXT_KEY);
    }

}

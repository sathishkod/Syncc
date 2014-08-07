package gtsarandum.syncc;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import de.cyclingsir.helper.calendar.DateEvent;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

public class EventDisplayFragment extends Fragment {

    //attr
    private FrameLayout frameLayout;
    private CardListView cardListView;
    private ArrayList<SynccEvent> dataList;
    private ArrayList<Card> cardList;

    public static EventDisplayFragment getInstance(ArrayList<SynccEvent> list){
        EventDisplayFragment eventDisplayFragment=new EventDisplayFragment();
        eventDisplayFragment.setListData(list);
        return eventDisplayFragment;
    }

    public void setListData(ArrayList<SynccEvent> list){
        this.dataList=list;
    }

    public EventDisplayFragment() {
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
        frameLayout=(FrameLayout) inflater.inflate(R.layout.fragment_event_display,container,false);
        return frameLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        cardListView=(CardListView)frameLayout.findViewById(R.id.event_list);
    }

    private CardArrayAdapter buildAdapterFromList(){

         return new CardArrayAdapter(getActivity(),cardList);
    }


}

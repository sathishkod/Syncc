package gtsarandum.syncc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.microsoft.live.LiveAuthClient;
import com.microsoft.live.LiveAuthException;
import com.microsoft.live.LiveAuthListener;
import com.microsoft.live.LiveConnectClient;
import com.microsoft.live.LiveConnectSession;
import com.microsoft.live.LiveStatus;

import java.util.Arrays;

public class LiveLoginActivity extends Activity {

    private LiveAuthClient auth;
    private LiveConnectClient client;
    private TextView resultTextView;

    public final static String clientName="name";
    public final static String clientID="id";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_login);
        this.resultTextView = (TextView)findViewById(R.id.resultTextView);
        this.auth = new LiveAuthClient(this, MyConstants.APP_CLIENT_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Iterable<String> scopes = Arrays.asList( //emails?
                "wl.signin",
                "wl.basic"/*,
                "wl.calendars",
                "wl.contact"*/);
        //this.auth.login(scopes, this);
        this.auth.login(this,scopes,new CustomLiveAuthListener(resultTextView));
    }



    public class CustomLiveAuthListener implements LiveAuthListener{

        private TextView resultTextView;

        public CustomLiveAuthListener(TextView resultTextView){
            this.resultTextView=resultTextView;
        }


        public void onAuthComplete(LiveStatus status, LiveConnectSession session, Object userState) {
            if(status == LiveStatus.CONNECTED) {
                this.resultTextView.setText("Signed in.");
                this.resultTextView.setTextSize(25);
                client = new LiveConnectClient(session);



                finish();
            }
            else {
                this.resultTextView.setText("Not signed in.");
                this.resultTextView.setTextSize(25);
                client = null;
            }
        }

        public void onAuthError(LiveAuthException exception, Object userState) {
            this.resultTextView.setText("Error signing in: " + exception.getMessage());
            client = null;
        }
    }
}

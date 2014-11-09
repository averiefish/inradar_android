package inradar.linkedin.com.inradar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by bili on 11/8/14.
 */
public class LinkedInLoginFragment extends Fragment
        implements BaseResultReceiver.ReceiverCallBack{
    private static final String TAG = "LinkedInLoginFragment";
    private static final String LinkedInMyProfileEndpoint = "http://10.0.3.2:8000/linkedin_login/test";

    public static final String EXTRA_PROFILE = "com.linkedin.inradar.profile";

    private BaseResultReceiver mResultReceiver;

    private EditText mAccessTokenEditText;
    private Button mLoginWithLinkedInButton;

    public LinkedInLoginFragment() {
        super();
        mResultReceiver = new BaseResultReceiver(new Handler(Looper.getMainLooper()), 0, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_linkedin_login, container, false);

        mAccessTokenEditText = (EditText)v.findViewById(R.id.access_token_edit_text);

        mLoginWithLinkedInButton = (Button)v.findViewById(R.id.login_with_linkedin_button);
        mLoginWithLinkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String access_token = mAccessTokenEditText.getText().toString();
                Toast.makeText(getActivity(), access_token, Toast.LENGTH_LONG).show();
                Log.d(TAG, "starting service intent");
                Intent i = new Intent("com.linkedin.inradar.HttpClientService");
                i.putExtra(HttpClientService.EXTRA_REST_ENDPOINT, LinkedInMyProfileEndpoint);
                i.putExtra(HttpClientService.EXTRA_REST_RESULT_RECEIVER, mResultReceiver);
                v.getContext().startService(i);
            }
        });

        return v;
    }

    @Override
    public void onReceiveResult(int receiverId, int resultCode, Bundle resultData) {
        Log.d(TAG, ": " + receiverId + ": " + resultCode + ": " + resultData.toString());
        if (resultData != null) {
            String content = resultData.getString(HttpClientService.EXTRA_REST_RESULT);
            Log.d(TAG, content);
            Intent i = new Intent(getActivity(), RadarActivity.class);
            i.putExtra(EXTRA_PROFILE, content);
            startActivity(i);
        }
    }
}

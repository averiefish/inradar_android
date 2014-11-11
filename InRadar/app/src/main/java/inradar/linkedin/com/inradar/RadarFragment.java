package inradar.linkedin.com.inradar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bili on 11/8/14.
 */
public class RadarFragment extends Fragment implements BaseResultReceiver.ReceiverCallBack {
    private static final String TAG = "RadarFragment";
    private static final String LocationUpdatesEndpoint = "http://10.0.3.2:8000/radar/location";
    private static final String EXTRA_PARAM_LONGITUDE = "longitude";
    private static final String EXTRA_PARAM_LATITUDE = "latitude";
    private static final String EXTRA_PARAM_ACCESS_TOKEN = "access_token";
    private String mAccessToken;
    private BaseResultReceiver mResultReceiver;
    private Button mSpotButton;
    private String mTestContent;

    private RadarLocationManager mRadarLocationManager;
    private Location mLastLocation;

    private BroadcastReceiver mLocationReceiver = new LocationReceiver() {
        @Override
        protected void onLocationReceived(Context context, Location loc) {
            super.onLocationReceived(context, loc);
            mLastLocation = loc;
            updateLocation(loc);
        }

        @Override
        protected void onProviderEnabledChanged(boolean enabled) {
            int toastText = enabled ? R.string.gps_enabled : R.string.gps_disabled;
            Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
        }
    };

    private Boolean mSpotStarted = false;

    public RadarFragment() {
        super();
        mResultReceiver = new BaseResultReceiver(new Handler(Looper.getMainLooper()), 0, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(mLocationReceiver, new IntentFilter(RadarLocationManager.ACTION_LOCATION));
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(mLocationReceiver);
        super.onStop();
    }

    private void updateLocation(Location loc) {
        Intent i = new Intent("com.linkedin.inradar.HttpClientService");
        i.putExtra(HttpClientService.EXTRA_REST_ENDPOINT, LocationUpdatesEndpoint);
        i.putExtra(HttpClientService.EXTRA_REST_RESULT_RECEIVER, mResultReceiver);
        Bundle params = new Bundle();
        params.putString(EXTRA_PARAM_LONGITUDE, String.valueOf(loc.getLongitude()));
        params.putString(EXTRA_PARAM_LATITUDE, String.valueOf(loc.getLatitude()));
        params.putString(EXTRA_PARAM_ACCESS_TOKEN, mAccessToken);
        i.putExtra(HttpClientService.EXTRA_REST_PARAMS, params);
        getActivity().startService(i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRadarLocationManager = RadarLocationManager.get(getActivity());
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mTestContent = args.getString(LinkedInLoginFragment.EXTRA_PROFILE);
        mAccessToken = args.getString(LinkedInLoginFragment.EXTRA_ACCESS_TOKEN);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radar, container, false);

        mSpotButton = (Button)v.findViewById(R.id.spot_button);
        mSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSpotStarted) {
                    mSpotStarted = false;
                    mRadarLocationManager.stopLocationUpdates();
                    mSpotButton.setText(R.string.start_spot_btn);
                    // tell back end spot stopped
                } else {
                    mSpotStarted = true;
                    mRadarLocationManager.startLocationUpdates();
                    mSpotButton.setText(R.string.stop_spot_btn);
                    // tell back end spot started?
                }
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_LONG).show();
            }
        });

        TextView mTestTextView = (TextView)v.findViewById(R.id.test_text_view);
        //mTestTextView.setText(mTestContent);
        LinkedInProfile profile = new LinkedInProfile(mTestContent);
        mTestTextView.setText(profile.toString());

        return v;
    }

    @Override
    public void onReceiveResult(int receiverId, int resultCode, Bundle resultData) {

    }
}

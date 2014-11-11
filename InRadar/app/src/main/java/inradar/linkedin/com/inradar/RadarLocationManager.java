package inradar.linkedin.com.inradar;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by bili on 11/9/14.
 */
public class RadarLocationManager {
    private static final String TAG = "LocationManager";

    public static final String ACTION_LOCATION = "com.linkedin.inradar.ACTION_LOCATION";

    private static RadarLocationManager sRadarLocationManager;
    private Context mAppContext;
    private LocationManager mLocationManager;

    private RadarLocationManager(Context appContext) {
        mAppContext = appContext;
        mLocationManager = (LocationManager)mAppContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public static RadarLocationManager get(Context c) {
        if (sRadarLocationManager == null) {
            sRadarLocationManager = new RadarLocationManager(c.getApplicationContext());
        }
        return sRadarLocationManager;
    }

    private PendingIntent getLocationPendingintent(boolean shouldCreate) {
        Intent broadcast = new Intent(ACTION_LOCATION);
        int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
        return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
    }

    public void startLocationUpdates() {
        String provider = LocationManager.GPS_PROVIDER;

        PendingIntent pi = getLocationPendingintent(true);
        mLocationManager.requestLocationUpdates(provider, 5000, 0, pi);
    }

    public void stopLocationUpdates() {
        PendingIntent pi = getLocationPendingintent(false);
        if (pi != null) {
            mLocationManager.removeUpdates(pi);
            pi.cancel();
        }
    }

    public boolean isTrackingRun() {
        return getLocationPendingintent(false) != null;
    }
}

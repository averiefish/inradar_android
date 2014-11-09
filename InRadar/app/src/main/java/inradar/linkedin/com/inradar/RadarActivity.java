package inradar.linkedin.com.inradar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by bili on 11/8/14.
 */
public class RadarActivity extends SingleFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment(Intent intent) {
        RadarFragment fragment = new RadarFragment();
        fragment.setArguments(intent.getExtras());
        return fragment;
    }
}

package inradar.linkedin.com.inradar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bili on 11/8/14.
 */
public class RadarFragment extends Fragment {
    private static final String TAG = "RadarFragment";
    private String mTestContent;

    public RadarFragment() {
        super();
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mTestContent = args.getString(LinkedInLoginFragment.EXTRA_PROFILE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radar, container, false);

        TextView mTestTextView = (TextView)v.findViewById(R.id.test_text_view);
        mTestTextView.setText(mTestContent);

        return v;
    }
}

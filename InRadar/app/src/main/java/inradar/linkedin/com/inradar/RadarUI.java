package inradar.linkedin.com.inradar;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by cfeng on 11/10/14.
 */
public class RadarUI {
    final FrameLayout mFrameLayout;

    public RadarUI(View v, int ID) {
        mFrameLayout = (FrameLayout)v.findViewById(ID);
    }

    public void UpdateLayout (int duration, int offset) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(duration);
        animation.setStartOffset(offset);

        image.startAnimation(animation);
    }
}

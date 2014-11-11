package inradar.linkedin.com.inradar;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by cfeng on 11/9/14.
 */
public class RadarAnimation {
    final ImageView mImage;

    public RadarAnimation(View v, int ID) {
        mImage = (ImageView)v.findViewById(ID);
    }

    public void fadeInAnimation (int duration, int offset) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);

        animation.setDuration(duration);
        animation.setStartOffset(offset);

        mImage.startAnimation(animation);
    }
}

package inradar.linkedin.com.inradar;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class BaseResultReceiver extends ResultReceiver {
    private final int mReceiverId;// similar to loaderId, used by caller to
    // distinguish different result receivers
    private WeakReference<ReceiverCallBack> mCallBackRef;

    public static interface ReceiverCallBack {
        public void onReceiveResult(int receiverId, int resultCode, Bundle resultData);

    }

    public BaseResultReceiver(Handler handler, int receiverId, ReceiverCallBack callBack) {
        super(handler);
        this.mReceiverId = receiverId;
        mCallBackRef = new WeakReference<BaseResultReceiver.ReceiverCallBack>(callBack);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        ReceiverCallBack callBack = mCallBackRef == null ? null : mCallBackRef.get();
        if (callBack != null) {
            callBack.onReceiveResult(mReceiverId, resultCode, resultData);
        }
    }

}

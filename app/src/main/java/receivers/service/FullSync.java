package receivers.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by BAPI1 on 25-02-2016.
 */
public class FullSync extends IntentService {
    private static final String TAG = FullSync.class.getSimpleName();
    public FullSync() {
        super("FullSync Service started!!");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        for (int i=0; i<5; i++){
            Log.d(TAG, "onHandleIntent: "+i);
        }
    }
}

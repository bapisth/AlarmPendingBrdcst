package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by BAPI1 on 25-02-2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "My alarm Service is also working"+NetworkService.isAvailableNetwork, Toast.LENGTH_SHORT).show();
        if (NetworkService.isAvailableNetwork && NetworkService.Bandwidth > 8) {
            String actionName = intent.getAction();
            if (actionName == null || actionName.isEmpty()) {
                Toast.makeText(context, "Performing quick sync.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Performing Full sync.", Toast.LENGTH_SHORT).show();
            }
        } else if (NetworkService.isAvailableNetwork) {
            Toast.makeText(context, "Not enough bandwidth available. Skipping data sync.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "No internet connection available. Skipping data sync.", Toast.LENGTH_LONG).show();
        }
    }
}

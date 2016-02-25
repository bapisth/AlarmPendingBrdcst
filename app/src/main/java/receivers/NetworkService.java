package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by BAPI1 on 25-02-2016.
 */
public class NetworkService extends BroadcastReceiver {
    public static boolean isAvailableNetwork = false;
    public static int Bandwidth;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        TelephonyManager telephonyManager;

        if (networkInfo != null){
            isAvailableNetwork = networkInfo.isConnectedOrConnecting();
            if (1 == networkInfo.getType()) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);//"wifi"
                if (wifiManager == null || wifiManager.getConnectionInfo() == null) {
                    isAvailableNetwork = false;
                    Bandwidth = 0;
                    return;
                }

                Bandwidth = wifiManager.getConnectionInfo().getLinkSpeed();
                return;
            }else if (networkInfo.getType() == 0) {
                telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); //"phone"
                if (telephonyManager != null) {
                    Bandwidth = telephonyManager.getNetworkType();
                    return;
                }
                isAvailableNetwork = false;
                Bandwidth = 0;
                return;
            } else {
                return;
            }
        }
        Toast.makeText(context, "Network Status:"+isAvailableNetwork, Toast.LENGTH_LONG).show();
    }

    public static void Initialize(Context context) {
        NetworkInfo activeNetInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();//"connectivity"
        if (activeNetInfo != null) {
            isAvailableNetwork = activeNetInfo.isConnectedOrConnecting();
            if (1 == activeNetInfo.getType()) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);//"wifi"
                if (wifiManager != null && wifiManager.getConnectionInfo() != null) {
                    Bandwidth = wifiManager.getConnectionInfo().getLinkSpeed();
                }
            } else if (activeNetInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); //"phone"
                if (telephonyManager != null) {
                    Bandwidth = telephonyManager.getNetworkType();
                }
            }
        }
    }
}

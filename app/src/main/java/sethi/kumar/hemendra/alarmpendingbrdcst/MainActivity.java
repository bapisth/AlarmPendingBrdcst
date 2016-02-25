package sethi.kumar.hemendra.alarmpendingbrdcst;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import receivers.AlarmReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_NO_CREATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        calendar.add(Calendar.SECOND, 10);
        if(pendingIntent == null){
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000, pendingIntent);
            myIntent.setAction("receivers.service.FullSync");
            PendingIntent pendingIntentFullSync = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000, pendingIntentFullSync);
        }

    }
}

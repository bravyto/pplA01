package ppla01.foodo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Bravyto on 16/05/2016.
 */
public class AlarmReciever extends BroadcastReceiver
{
    SharedPreferences spref;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO Auto-generated method stub


        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

        // Show the toast  like in above screen shot
        spref = context.getSharedPreferences("my_data", 0);
        editor = spref.edit();

        editor.putString("log","2");
        editor.commit();
    }

}

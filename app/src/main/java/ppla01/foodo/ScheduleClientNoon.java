package ppla01.foodo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.Calendar;

/**
 * Created by Gema Raditya on 5/14/2016.
 */
public class ScheduleClientNoon {
    private ScheduleServiceNoon mBoundServiceNoon;
    // The context to start the service in
    private Context mContextNoon;
    // A flag if we are connected to the service or not
    private boolean mIsBoundNoon;


    public ScheduleClientNoon(Context context) {
        mContextNoon = context;
    }

    /**
     * Call this to connect your activity to your service
     */
    public void doBindServiceNoon() {
        // Establish a connection with our service
        mContextNoon.bindService(new Intent(mContextNoon, ScheduleServiceNoon.class), mConnectionNoon, Context.BIND_AUTO_CREATE);
        mIsBoundNoon = true;
    }

    /**
     * When you attempt to connect to the service, this connection will be called with the result.
     * If we have successfully connected we instantiate our service object so that we can call methods on it.
     */
    private ServiceConnection mConnectionNoon = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with our service has been established,
            // giving us the service object we can use to interact with our service.
            mBoundServiceNoon = ((ScheduleServiceNoon.ServiceBinderNoon) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundServiceNoon = null;
        }
    };

    /**
     * Tell our service to set an alarm for the given date
     * @param c a date to set the notification for
     */
    public void setAlarmForNotificationNoon(Calendar c, int code){
        // Log.i("ScheduleClient", "bbbbbbb"+ code);


        mBoundServiceNoon.setAlarmNoon(c,code);
    }

    /**
     * When you have finished with the service call this method to stop it
     * releasing your connection and resources
     */
    public void doUnbindServiceNoon() {
        if (mIsBoundNoon) {
            // Detach our existing connection.
            mContextNoon.unbindService(mConnectionNoon);
            mIsBoundNoon = false;
        }
    }
}

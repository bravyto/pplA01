package ppla01.foodo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.Calendar;

/**
 * Created by Gema Raditya on 5/15/2016.
 */
public class ScheduleClientNight {
    private ScheduleServiceNight mBoundServiceNight;
    // The context to start the service in
    private Context mContextNight;
    // A flag if we are connected to the service or not
    private boolean mIsBoundNight;


    public ScheduleClientNight(Context context) {
        mContextNight = context;
    }

    /**
     * Call this to connect your activity to your service
     */
    public void doBindServiceNight() {
        // Establish a connection with our service
        mContextNight.bindService(new Intent(mContextNight, ScheduleServiceNight.class), mConnectionNight, Context.BIND_AUTO_CREATE);
        mIsBoundNight = true;
    }

    /**
     * When you attempt to connect to the service, this connection will be called with the result.
     * If we have successfully connected we instantiate our service object so that we can call methods on it.
     */
    private ServiceConnection mConnectionNight = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with our service has been established,
            // giving us the service object we can use to interact with our service.
            mBoundServiceNight = ((ScheduleServiceNight.ServiceBinderNight) service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundServiceNight = null;
        }
    };

    /**
     * Tell our service to set an alarm for the given date
     * @param c a date to set the notification for
     */
    public void setAlarmForNotificationNight(Calendar c, int code){
        // Log.i("ScheduleClient", "bbbbbbb"+ code);


        mBoundServiceNight.setAlarmNight(c,code);
    }

    /**
     * When you have finished with the service call this method to stop it
     * releasing your connection and resources
     */
    public void doUnbindServiceNight() {
        if (mIsBoundNight) {
            // Detach our existing connection.
            mContextNight.unbindService(mConnectionNight);
            mIsBoundNight = false;
        }
    }
}

package nz.ac.aut.helloworldexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by xfn on 9/24/2017.
 */

public class MyService extends Service {

    // The system calls this method when another component wants to bind with the service by
    // calling bindService(). If you implement this method, you must provide an interface that
    // clients use to communicate with the service, by returning an IBinder object. You must always
    // implement this method, but if you don't want to allow binding, then you should return null.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // The system calls this method when another component, such as an activity, requests that
    // the service be started, by calling startService(). If you implement this method, it is your
    // responsibility to stop the service when its work is done, by calling stopSelf()
    // or stopService() methods.
    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();

        return START_STICKY;
    }

    // The system calls this method when the service is no longer used and is being destroyed.
    // Your service should implement this to clean up any resources such as threads, registered
    // listeners, receivers, etc.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_LONG).show();
    }
}

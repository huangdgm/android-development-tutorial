package nz.ac.aut.helloworldexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xfn on 9/25/2017.
 */

public class MyReceiver extends BroadcastReceiver {

    // This method gets called upon receiving any of the registered intent.
    // The registered intents can be defined in the AndroidManifest.xml file.
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent detected.", Toast.LENGTH_LONG).show();
    }
}

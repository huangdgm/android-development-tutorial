package nz.ac.aut.helloworldexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

// When the activity is first started, the onStart() and the onResume() get called.
// When the user click the Home button directly from the activity, the onPause() and the onStop() get called.
// When the user relaunch the activity again, the onRestart(), onStart() and the onResume() get called.
// When the user lock the screen directly from the activity, the onPause() and the onStop() get called.
// When the user unlock the screen, then the activity will be displayed on the screen, the onRestart(), onStart() and the onResume() get called.
// When the user click the Back button, the activity will be destroyed, the onPause(), onStop() and the onDestroy() get called.
public class MainActivity extends AppCompatActivity {

    // This is the first callback and called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Loads UI components from res/layout/activity_main.xml file
        setContentView(R.layout.activity_main);

        // This is an example of accessing the resource in the Java code.
        // Get the view by id. The view is a resource.
        // R is an auto-generated class who manages all the resources defined in the res/ directory.
        TextView tv = (TextView) findViewById(R.id.textview);
        tv.setText("Dong Huang");
    }


    // This callback is called when the activity becomes visible to the user.
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Android:", "The onStart() event");
    }

    // This is called when the user starts interacting with the application.
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Android:", "The onResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Android:", "The onPause() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Android:", "The onStop() event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Android:", "The onRestart() event");
    }

    // This callback is called before the activity is destroyed by the system.
    // For example, if you click the Back button on the device, then this activity will be destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Android:", "The onDestroy() event");
    }
}

package nz.ac.aut.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonSMS;
    Button buttonBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSMS = (Button) findViewById(R.id.buttonSMS);
        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SMSActivity.class);
                startActivity(intent);
            }
        });

        buttonBluetooth = (Button) findViewById(R.id.buttonBluetooth);
        buttonBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
                startActivity(intent);
            }
        });
    }
}

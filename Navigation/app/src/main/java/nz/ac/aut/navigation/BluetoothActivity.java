package nz.ac.aut.navigation;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {
    Button btnTurnOnBluetooth;
    Button btnTurnOffBluetooth;
    Button btnSetDeviceVisible;
    Button btnListPairedDevice;

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btnTurnOnBluetooth = (Button) findViewById(R.id.buttonTurnOnBluetooth);
        btnTurnOffBluetooth = (Button) findViewById(R.id.buttonTurnOffBluetooth);
        btnSetDeviceVisible = (Button) findViewById(R.id.buttonSetDeviceVisible);
        btnListPairedDevice = (Button) findViewById(R.id.buttonListPairedDevice);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        listView = (ListView) findViewById(R.id.listView);
    }

    public void turnOnBluetooth(View view) {
        if(!bluetoothAdapter.isEnabled()) {
            Intent intentTurnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(intentTurnOn, 0);

            Toast.makeText(getApplicationContext(), "Bluetooth turned on.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth already turned on.", Toast.LENGTH_LONG).show();
        }
    }

    public void turnOffBluetooth(View view) {
        bluetoothAdapter.disable();

        Toast.makeText(getApplicationContext(), "Bluetooth turned off.", Toast.LENGTH_LONG).show();
    }

    public void setDeviceVisible(View view) {
        Intent intentSetDeviceVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        startActivityForResult(intentSetDeviceVisible, 0);
    }

    public void listPairedDevice(View view) {
        pairedDevices = bluetoothAdapter.getBondedDevices();

        ArrayList<String> list = new ArrayList<String>();

        for(BluetoothDevice bluetoothDevice : pairedDevices) {
            list.add(bluetoothDevice.getName());
        }

        Toast.makeText(getApplicationContext(), "Showing paired devices.", Toast.LENGTH_LONG).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}
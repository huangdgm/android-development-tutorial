package nz.ac.aut.navigation;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SMSActivity extends AppCompatActivity {

    private static final int SEND_SMS_PERMISSIONS_REQUEST = 0;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    ArrayList<String> messages = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    private static SMSActivity instance;

    Button sendBtn;
    ListView listView;

    EditText txtPhoneNo;
    EditText txtMessage;

    String phoneNo;
    String message;

    public static SMSActivity instance() {
        return instance;
    }

    @Override
    public void onStart() {
        super.onStart();
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sendBtn = (Button) findViewById(R.id.buttonSendMessage);
        listView = (ListView) findViewById(R.id.listView);
        txtPhoneNo = (EditText) findViewById(R.id.editTextPhoneNumber);
        txtMessage = (EditText) findViewById(R.id.editTextMessage);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        listView.setAdapter(arrayAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(SMSActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    getPermissionToSendSMS();
                } else {
                    sendSMS();
                }
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        } else {
            refreshSmsInbox();
        }
    }

    private void getPermissionToReadSMS() {
        if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS))) {
            Toast.makeText(this, "Please allow permission for reading messages.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, READ_SMS_PERMISSIONS_REQUEST);
        }
    }

    private void getPermissionToSendSMS() {
        if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))) {
            Toast.makeText(this, "Please allow permission for sending messages.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSIONS_REQUEST);
        }
    }

    public void sendSMS() {
        phoneNo = txtPhoneNo.getText().toString();
        message = txtMessage.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, message, null, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                    Toast.makeText(getApplicationContext(), "SMS sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS sent failed! Please try again.", Toast.LENGTH_LONG).show();
                }

                break;
            case READ_SMS_PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Read SMS permission granted.", Toast.LENGTH_LONG).show();
                    refreshSmsInbox();
                } else {
                    Toast.makeText(getApplicationContext(), "Read SMS permission denied.", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);

        int indexOfAddress = cursor.getColumnIndex("address");
        int indexOfBody = cursor.getColumnIndex("body");

        if (!(indexOfBody < 0 || !cursor.moveToFirst())) {
            arrayAdapter.clear();

            do {
                String string = "From: " + cursor.getString(indexOfAddress) + "\n" + cursor.getString(indexOfBody) + "\n";
                arrayAdapter.add(string);
            } while (cursor.moveToNext());
        }
    }

    public void updateInbox(final String message) {
        arrayAdapter.insert(message, 0);
        arrayAdapter.notifyDataSetChanged();
    }
}

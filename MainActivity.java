  package com.example.pc.broadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

  public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECEIVE_SMS},1);

        PowerReceiver pr = new PowerReceiver();

        registerReceiver(pr, new IntentFilter(
                Intent.ACTION_POWER_CONNECTED
        ));

        registerReceiver(pr, new IntentFilter(
                Intent.ACTION_POWER_DISCONNECTED
        ));

        SmsReceiver sr =new SmsReceiver();
        registerReceiver(sr, new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED"
        ));
    }
    class PowerReceiver extends BroadcastReceiver
        {

            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction()== Intent.ACTION_POWER_CONNECTED)
                {
                    Toast.makeText(getBaseContext(),"power connected",Toast.LENGTH_SHORT).show();
                }
                else if(intent.getAction()== Intent.ACTION_POWER_DISCONNECTED)
                {
                    Toast.makeText(MainActivity.this,"power disconnected",Toast.LENGTH_SHORT).show();
                }
            }
        }
      class SmsReceiver extends BroadcastReceiver {

          private static final String TAG = "SmsBroadcastReceiver";

          @Override
          public void onReceive(Context context, Intent intent) {

//              if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
//                      String smsSender = "";
//                      String smsBody = "";
//                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                          for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
//                              smsSender = smsMessage.getDisplayOriginatingAddress();
//                              smsBody += smsMessage.getMessageBody();
//                          }
//                      } else {
//                      Bundle smsBundle = intent.getExtras();
//                      if (smsBundle != null) {
//                          Object[] pdus = (Object[]) smsBundle.get("pdus");
//                          if (pdus == null) {
//                              // Display some error to the user
//                              Log.e(TAG, "SmsBundle had no pdus key");
//                              return;
//                          }
//                          SmsMessage[] messages = new SmsMessage[pdus.length];
//                          for (int i = 0; i < messages.length; i++) {
//                              messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                              smsBody += messages[i].getMessageBody();
//                          }
//                          smsSender = messages[0].getOriginatingAddress();
//                      }
//                  }

              Bundle smsBundle = intent.getExtras();
              if (smsBundle != null) {
                  Object[] pdus = (Object[]) smsBundle.get("pdus");
                  SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);
                  String sender = message.getDisplayOriginatingAddress();
                  String text = message.getDisplayMessageBody();
                  TextView tv = (TextView) findViewById(R.id.tv);
                  tv.setText(sender + " : " + text);
                  if (pdus == null) {
                      // Display some error to the user
                      Log.e(TAG, "SmsBundle had no pdus key");
                      return;
                  }

              }
          }
      }
  }



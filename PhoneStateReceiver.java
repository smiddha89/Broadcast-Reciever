package com.example.pc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by PC on 20-Jan-18.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.w("MY_DEBUG_TAG", state);


            String phone = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, phone + " disconnected", Toast.LENGTH_SHORT).show();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Toast.makeText(context, phone + " received", Toast.LENGTH_SHORT).show();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, phone + "ringing", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



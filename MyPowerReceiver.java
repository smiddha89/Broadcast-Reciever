package com.example.pc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by PC on 20-Jan-18.
 */

public class MyPowerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()== Intent.ACTION_POWER_CONNECTED)
        {
            Toast.makeText(context,"power connected",Toast.LENGTH_SHORT).show();
        }
        else if(intent.getAction()== Intent.ACTION_POWER_DISCONNECTED)
        {
            Toast.makeText(context,"power disconnected",Toast.LENGTH_SHORT).show();
        }
    }
}

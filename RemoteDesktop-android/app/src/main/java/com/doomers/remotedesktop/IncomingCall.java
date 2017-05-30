package com.doomers.remotedesktop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class IncomingCall extends BroadcastReceiver {
    Context context;
    public IncomingCall(MainActivity context) {
        this.context = (MainActivity)context;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            Log.e("Tag", "Called");
            // TELEPHONY MANAGER class object to register one listner
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            //Create Listner
            // MyPhoneStateListener PhoneListener = new MyPhoneStateListener();

            // Register listener for LISTEN_CALL_STATE
            PhoneStateListener phoneStateListener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    if (state == 1) {

                        String msg = "New Phone Call Event. Incomming Number : " + incomingNumber;
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, msg, duration);
                        toast.show();
                        MainActivity mainActivity = (MainActivity) context;
                        mainActivity.incomingcall(incomingNumber);

                    }
                }
            };
            tmgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);


        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
    }
}

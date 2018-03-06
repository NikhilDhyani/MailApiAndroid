package com.example.nikhil.mailapiandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by NIKHIL on 05-03-2018.
 */

public class ServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {


  /*      TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                System.out.println("incomingNumber : "+incomingNumber);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
        */

       final MainActivity mains = new MainActivity();
        TelephonyManager telephonyManager =
                (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        PhoneStateListener callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber)
            {
                if(state==TelephonyManager.CALL_STATE_RINGING){
                    if (incomingNumber.isEmpty())
                    {
                        Log.d(TAG,"Empty");
                        Toast.makeText(context,"No Number",Toast.LENGTH_LONG).show();

                    }else
                    {
                        mains.sendEmail();
                        Log.d(TAG,incomingNumber);
                       Toast.makeText(context,incomingNumber+state,Toast.LENGTH_LONG).show();
                    }
                    String states = TelephonyManager.EXTRA_STATE;
                    String num = incomingNumber;
                    Log.d(TAG,states);

                    Toast.makeText(context,"Phone Is Riging "+ num +"dones",
                            Toast.LENGTH_LONG).show();



                }
               /* if(state==TelephonyManager.CALL_STATE_OFFHOOK){
                    Toast.makeText(context,"Phone is Currently in A call",
                            Toast.LENGTH_LONG).show();
                }

                if(state==TelephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(context,"phone is neither ringing nor in a call",
                            Toast.LENGTH_LONG).show();
                }
                */
            }
        };
        telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);


    }
}

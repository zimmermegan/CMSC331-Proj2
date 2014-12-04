package com.patchx.umbcringer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by frank on 12/4/14.
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent)
    {
        String texty = intent.getStringExtra("blah");

        Toast.makeText(context, texty, Toast.LENGTH_LONG).show();
    }

};

package com.patchx.umbcringer;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent)
    {

        int mode = intent.getIntExtra("mode",1);
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (mode == 0){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Toast.makeText(context, "SILENT", Toast.LENGTH_LONG).show();
        } else if (mode == 1){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            Toast.makeText(context, "VIBRATE", Toast.LENGTH_LONG).show();
        } else if (mode == 2){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(context, "NORMAL", Toast.LENGTH_LONG).show();
        }

        //String texty = intent.getStringExtra("blah");
        //Toast.makeText(context, texty, Toast.LENGTH_LONG).show();
    }

};

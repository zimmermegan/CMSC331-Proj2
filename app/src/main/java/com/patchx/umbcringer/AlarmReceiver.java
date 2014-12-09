/**************************************************************
 * File:    AlarmReceiver.java
 * Project: CMSC 331 - Project 2
 * Author : Frank Zastawnik
 * Date   : 10-December-2014
 * Section: Lecture-01
 * E-mail:  frankz2@umbc.edu
 *
 * This is the receiver that sets the ringer mode when the
 * scheduled time occurs.
 *************************************************************/
package com.patchx.umbcringer;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.media.AudioManager;



public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent)
    {
        // Get the mode passed in.  Default to silent.
        int mode = intent.getIntExtra("mode",0);
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (mode == 0){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } else if (mode == 1){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        } else if (mode == 2){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }


    }

};

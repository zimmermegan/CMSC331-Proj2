/**************************************************************
 * File:    Ringer.java
 * Project: CMSC 331 - Project 2
 * Author : Frank Zastawnik
 * Date   : 10-December-2014
 * Section: Lecture-01
 * E-mail:  frankz2@umbc.edu
 *
 * This activity schedules the ringer toggles as well as builds
 * the main user interface.  It also stores the list of
 * sections the user has.
 *************************************************************/


package com.patchx.umbcringer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

//import java.util.ArrayList;


public class Ringer extends Activity  {


    int ringerMode = 0;       // Storing the ringer mode
    private int sections = 4; // How many sections.  This is hard coed to 4 for now.
    private Section [] sectionList = new Section[sections];  // A array of sections


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringer);

        // Populate the section list.
        GetSections();

        //Populate the buttons based on the sections
        PopulateButtons();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ringer, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void  openSettings() {

        Intent settings_intent = new Intent(Ringer.this, SettingsActivity.class);
        startActivity(settings_intent);
    }

    //Builds the button for each section
    public void PopulateButtons() {

        ViewGroup mylayout = (ViewGroup) findViewById(R.id.ringlayout);

        int i = 1;
        for (i = 0; i < sections; i++) {
            // Create a new toggle button
            ToggleButton tb = new ToggleButton(this);

            // Start it checked
            tb.setChecked(true);

            // Give it an id
            tb.setId(i);

            // Build the default text for it
            String texty;
                    texty = sectionList[i].getSubject()
                    + "-"
                    + sectionList[i].getCourseNumber()
                    +"\n"
                    + sectionList[i].getDescription()
                    + "\n"
                    + sectionList[i].getTimes();

            // Default
            tb.setText(texty + "\nOn");
            // Text when the button is on
            tb.setTextOn(texty + "\nOn");

            // Text when the button is off
            tb.setTextOff(texty + "\nOff");
            tb.setTextColor(Color.WHITE);

            //set default alarms on
            int d = 0;
            while ( (sectionList[i].getDays())[d] != 0   ){
                int day = (sectionList[i].getDays())[d++];
                int mode = 0;
                int h = sectionList[i].getStartHour();
                int m = sectionList[i].getStartMinute();
                int sid = sectionList[i].getStartID(day);
                scheduleAlarm(mode, day, h, m, sid);
                mode = 2;
                h = sectionList[i].getEndHour();
                m = sectionList[i].getEndMinute();
                sid = sectionList[i].getEndID(day);
                scheduleAlarm(mode, day, h, m, sid);
            }

            // Create an onClick listener
            tb.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    int id = arg0.getId(); // you get ID of your dynamic button
                    boolean on = ((ToggleButton) arg0).isChecked();

                    MediaPlayer clicker = MediaPlayer.create(com.patchx.umbcringer.Ringer.this, R.raw.click);
                    clicker.start();

                    //String texty;

                    if (on){
                        //texty = "Schedule On";
                        //Set schedule
                        int d = 0;

                        // Build a call to schedule the alarm
                        while ( (sectionList[id].getDays())[d] != 0   ){
                            int day = (sectionList[id].getDays())[d++];
                            int mode = 0;
                            int h = sectionList[id].getStartHour();
                            int m = sectionList[id].getStartMinute();
                            int sid = sectionList[id].getStartID(day);
                            // Start of the alarm
                            scheduleAlarm(mode, day, h, m, sid);
                            mode = 2;
                            h = sectionList[id].getEndHour();
                            m = sectionList[id].getEndMinute();
                            sid = sectionList[id].getEndID(day);
                            //End of the alarm
                            scheduleAlarm(mode, day, h, m, sid);
                        }


                    }else {
                        int d = 0;

                        //texty = "Schedule Off";
                        while ( (sectionList[id].getDays())[d] != 0   ){
                            int day = (sectionList[id].getDays())[d++];
                            // Cancel both start and end
                            cancelAlarm(sectionList[d].getStartID(d));
                            cancelAlarm(sectionList[d].getEndID(d));
                        }
                        // Turn the ringer back on
                        GoNormal();

                    }
               }
                                });
            // Set the size attributes.
            tb.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.FILL_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));

            // Finally get the button on the layout
            mylayout.addView(tb);

        }


    }


    // Hard coded right now, but this will build the section list.
    private void GetSections(){


        int [] tdays = new int[7];
        for (int i = 0; i < sections; i++) {

            sectionList[i] = new Section();

        }


        sectionList[0].setClassNumber(7138);
        sectionList[0].setSectionNumber("03");
        sectionList[0].setSubject("CMSC");
        sectionList[0].setCourseNumber("313");
        sectionList[0].setDescription("Comp Organ & Assemb Lang");
        sectionList[0].setStartHour(11);
        sectionList[0].setStartMinute(30);
        sectionList[0].setEndHour(12);
        sectionList[0].setEndMinute(45);
        tdays[0]=3;
        tdays[1]=5;
        sectionList[0].setDays(tdays);


        for (int i = 0; i < 7; i++) {
            tdays[i] = 0;
        }

        sectionList[1].setClassNumber(1142);
        sectionList[1].setSectionNumber("01");
        sectionList[1].setSubject("CMSC");
        sectionList[1].setCourseNumber("331");
        sectionList[1].setDescription("Prin Of Prog Languages");
        sectionList[1].setStartHour(13);
        sectionList[1].setStartMinute(00);
        sectionList[1].setEndHour(14);
        sectionList[1].setEndMinute(15);
        tdays[0]=3;
        tdays[1]=5;
        sectionList[1].setDays(tdays);

        for (int i = 0; i < 7; i++) {
            tdays[i] = 0;
        }


        sectionList[2].setClassNumber(1144);
        sectionList[2].setSectionNumber("02");
        sectionList[2].setSubject("CMSC");
        sectionList[2].setCourseNumber("341");
        sectionList[2].setDescription("Data Structures");
        sectionList[2].setStartHour(13);
        sectionList[2].setStartMinute(00);
        sectionList[2].setEndHour(14);
        sectionList[2].setEndMinute(15);
        tdays[0]=2;
        tdays[1]=4;
        tdays[2]=6;
        sectionList[2].setDays(tdays);

        for (int i = 0; i < 7; i++) {
            tdays[i] = 0;
        }

        sectionList[3].setClassNumber(2663);
        sectionList[3].setSectionNumber("03");
        sectionList[3].setSubject("MATH");
        sectionList[3].setCourseNumber("221");
        sectionList[3].setDescription("Introduction To Linear Algebra");
        sectionList[3].setStartHour(14);
        sectionList[3].setStartMinute(30);
        sectionList[3].setEndHour(15);
        sectionList[3].setEndMinute(45);
        tdays[0]=2;
        tdays[1]=4;
        sectionList[3].setDays(tdays);





    }


    // Sets the ringer to silent
    // Currently unused.
    public void GoSilent() {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        switch (audioManager.getRingerMode()) {
            case AudioManager.RINGER_MODE_NORMAL:
                ringerMode = AudioManager.RINGER_MODE_NORMAL;
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                ringerMode = AudioManager.RINGER_MODE_VIBRATE;
                break;
        }

        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }
    // Set ringer to normal
    public void GoNormal() {

        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }


    // Set ringer to vibarate
    // Currently unused
    public void GoVibrate() {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }



// Set a schedule and queues it to the alarmmanager
private void scheduleAlarm(int mode, int DOW, int hour, int minute, int ID){

    Calendar calendar = new GregorianCalendar();
    long repeater = 1000 * 60;// * 60 * 24 * 7;


    // If today is passed the day of the week add a week then check again so we get the NEXT
    // day of the week (ie next Tuesday if we are passed Wednesday)
    if (calendar.get(Calendar.DAY_OF_WEEK)> DOW){
        calendar.add(Calendar.WEEK_OF_MONTH,1);
        calendar.set(Calendar.DAY_OF_WEEK, DOW);
    } else {
        calendar.set(Calendar.DAY_OF_WEEK, DOW);
    }

    // Set the time we want to have the alarm happen
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND,0);

    long time = calendar.getTimeInMillis();

    // Build the intent and add the mode so the receiver knows what to do.
    Intent intentAlarm = new Intent(this, AlarmReceiver.class);
    intentAlarm.putExtra("mode",mode);


    // Create the alarm manager call and send it.
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,time,1000*60,
            PendingIntent.getBroadcast(this, ID, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

}
    // Cancel an alarm by ID (this is from the section itself)
    private void cancelAlarm(int ID){

        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(PendingIntent.getBroadcast(this, ID, intentAlarm,
                PendingIntent.FLAG_CANCEL_CURRENT));
    }

}


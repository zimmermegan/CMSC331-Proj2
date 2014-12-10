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
//import com.patchx.umbcringer.Section;
//import com.patchx.umbcringer.AlarmReceiver;

//import java.lang.reflect.Array;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

//import java.util.ArrayList;


public class Ringer extends Activity  {


    private int ringerMode;
    private int sections = 4;
    private Section [] sectionList = new Section[sections];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringer);

        GetSections();
        PopulateButtons();

        //scheduleAlarm(2, 6, 1, 11, 42);



        //cancelAlarm(42);

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
        //opening settings
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


    public void PopulateButtons() {

        ViewGroup mylayout = (ViewGroup) findViewById(R.id.ringlayout);

        int i = 1;
        for (i = 0; i < 4; i++) {

            ToggleButton tb = new ToggleButton(this);

            tb.setChecked(true);
            tb.setId(i);
            String texty;
                    texty = sectionList[i].getSubject()
                    + "-"
                    + sectionList[i].getCourseNumber()
                    +"\n"
                    + sectionList[i].getDescription()
                    + "\n"
                    + sectionList[i].getTimes();

            tb.setText(texty + "\nOn");
            tb.setTextOn(texty + "\nOn");
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

                        while ( (sectionList[id].getDays())[d] != 0   ){
                            int day = (sectionList[id].getDays())[d++];
                            int mode = 0;
                            int h = sectionList[id].getStartHour();
                            int m = sectionList[id].getStartMinute();
                            int sid = sectionList[id].getStartID(day);
                            scheduleAlarm(mode, day, h, m, sid);
                            mode = 2;
                            h = sectionList[id].getEndHour();
                            m = sectionList[id].getEndMinute();
                            sid = sectionList[id].getEndID(day);
                            scheduleAlarm(mode, day, h, m, sid);
                        }


                    }else {
                        int d = 0;

                        //texty = "Schedule Off";
                        while ( (sectionList[id].getDays())[d] != 0   ){
                            int day = (sectionList[id].getDays())[d++];
                            cancelAlarm(sectionList[d].getStartID(d));
                            cancelAlarm(sectionList[d].getEndID(d));
                        }
                        GoNormal();

                    }
               

                    //Toast.makeText(getApplicationContext(),texty , Toast.LENGTH_SHORT).show();
        }
                                });

            tb.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.FILL_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));

            mylayout.addView(tb);




        }


    }

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

    private void saveState(){

    }


    public void GoSilent() {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);

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

    public void GoNormal() {
        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);
        //tb.setText("Make Silent");

        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    public void GoVibrate() {
        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);
        //tb.setText("Make Silent");
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }



private void scheduleAlarm(int mode, int DOW, int hour, int minute, int ID){

    Calendar calendar = new GregorianCalendar();
    long repeater = 1000 * 60;// * 60 * 24 * 7;


    if (calendar.get(Calendar.DAY_OF_WEEK)> DOW){
        calendar.add(Calendar.WEEK_OF_MONTH,1);
        calendar.set(Calendar.DAY_OF_WEEK, DOW);
    } else {
        calendar.set(Calendar.DAY_OF_WEEK, DOW);
    }


    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND,0);

    long time = calendar.getTimeInMillis();

    Intent intentAlarm = new Intent(this, AlarmReceiver.class);
    intentAlarm.putExtra("mode",mode);
    intentAlarm.putExtra("blah","Alarm Set");

    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,time,1000*60,
            PendingIntent.getBroadcast(this, ID, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));





}

    private void cancelAlarm(int ID){

        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(PendingIntent.getBroadcast(this, ID, intentAlarm,
                PendingIntent.FLAG_CANCEL_CURRENT));
    }


/*
private void scheduleAlarm(){
    long time =  new GregorianCalendar().getTimeInMillis()+ 6*1000;
    Intent intentAlarm = new Intent(this, AlarmReceiver.class);
    Intent intentAlarm2 = new Intent(this, AlarmReceiver.class);

    Calendar calendar = new GregorianCalendar();
    //calendar.add(Calendar.WEEK_OF_MONTH,1);
    calendar.set(Calendar.HOUR_OF_DAY, 22);
    calendar.set(Calendar.MINUTE, 51);
    calendar.set(Calendar.SECOND,0);
    int year = calendar.get(calendar.DAY_OF_MONTH);

    long testy = calendar.getTimeInMillis();



    intentAlarm.putExtra("blah", "Intent1" );
    intentAlarm2.putExtra("blah", "Intent2" );

    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


    String texty = "" + year;

    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,time,10*1000,
            PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,time+10*1000,10*1000,
            PendingIntent.getBroadcast(this, 2, intentAlarm2, PendingIntent.FLAG_UPDATE_CURRENT));


    //Toast.makeText(this, texty, Toast.LENGTH_LONG).show();

}

private void cancelAlarm(){
    Intent intentAlarm = new Intent(this, AlarmReceiver.class);
    intentAlarm.putExtra("blah", "Intent2" );
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


    alarmManager.cancel(PendingIntent.getBroadcast(this, 2, intentAlarm,
            PendingIntent.FLAG_UPDATE_CURRENT));

    alarmManager.cancel(PendingIntent.getBroadcast(this, 1, intentAlarm,
            PendingIntent.FLAG_UPDATE_CURRENT));
}

*/














}


package com.patchx.umbcringer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private int ringerMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       /*
        ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);

        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        switch( audioManager.getRingerMode() ){
            case AudioManager.RINGER_MODE_NORMAL:
                tb.setText("Normal");
                ringerMode = AudioManager.RINGER_MODE_NORMAL;
                break;
            case AudioManager.RINGER_MODE_SILENT:
                tb.setText("Silent");
                ringerMode = AudioManager.RINGER_MODE_SILENT;
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                tb.setText("Vibrate");
                ringerMode = AudioManager.RINGER_MODE_VIBRATE;
                break;
        }
*/
        PopulateButtons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
         //((ToggleButton) view).setText("Help");
        //boolean on = (ToggleButton) findViewById(R.id.toggle1).isChecked();
        if (on) {
           GoSilent();
        } else if (ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                GoNormal();
            }
        else {
                GoVibrate();
        }
    }



    public void GoSilent(){
        AudioManager audioManager= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);

        switch( audioManager.getRingerMode() ){
            case AudioManager.RINGER_MODE_NORMAL:
          //      tb.setText("Turn on to normal");
                ringerMode = AudioManager.RINGER_MODE_NORMAL;
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
            //    tb.setText("Turn on to vibrate");
                ringerMode = AudioManager.RINGER_MODE_VIBRATE;
                break;
        }

        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    public void GoNormal(){
        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);
        //tb.setText("Make Silent");

        AudioManager audioManager= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }
    public void GoVibrate(){
        //ToggleButton tb = (ToggleButton) this.findViewById(R.id.toggle1);
        //tb.setText("Make Silent");
        AudioManager audioManager= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }


    public void PopulateButtons(){

        ViewGroup mylayout = (ViewGroup) findViewById(R.id.mainlayout);

        int i = 1;
        for (i = 1; i <= 4; i++) {

            ToggleButton tb = new ToggleButton(this);

            tb.setChecked(true);
            tb.setText("Course - " + i + "\nOn");
            tb.setTextOn("Course - " + i + "\nOn");
            tb.setTextOff("Course - " + i + "\nOff");

            tb.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.FILL_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));

            mylayout.addView(tb);

        }

        //tb1.layout();


    }











}

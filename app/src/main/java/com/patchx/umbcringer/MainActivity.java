package com.patchx.umbcringer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import com.patchx.umbcringer.Section;


public class MainActivity extends Activity {

    /*public TextView user_name ;
    public TextView pword;*/

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String prefUsername = "user_nameKey";
    public static final String prefPassword = "passwordKey";

    public static SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Intent intent = new Intent(this, Ringer.class);
        startActivity(intent);



        //ringer = new Ringer(savedInstanceState);



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
        //ringer.PopulateButtons();
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
        /*
        if (on) {
           //GoSilent();
        } else if (ringer.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
                //GoNormal();
            }
        else {
                //GoVibrate();
        }*/
    }



    }












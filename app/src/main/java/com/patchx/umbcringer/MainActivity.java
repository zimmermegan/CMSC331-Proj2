/**************************************************************
 * File:    MainActivity.java
 * Project: CMSC 331 - Project 2
 * Author : Frank Zastawnik, Grace Chandler
 * Date   : 10-December-2014
 * Section: Lecture-01
 * E-mail:  frankz2@umbc.edu
 *
 * This activy is called from splash and will, in the future,
 * check the configuration and offer a screen.  It calls the
 * Ringer class activity.
 *************************************************************/
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

        //these get the settings to be stored in SharedPrefs eventually
        setContentView(R.layout.activity_main);
        // Get the preferences
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        // Start the ringer activity
        Intent intent = new Intent(this, Ringer.class);
        startActivity(intent);
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





    }












/**************************************************************
 * File:    SplashScreen.java
 * Project: CMSC 331 - Project 2
 * Author : Frank Zastawnik
 * Date   : 10-December-2014
 * Section: Lecture-01
 * E-mail:  frankz2@umbc.edu
 *
 * This handles creating the splash screen then calls the main
 * activity after two seconds.  Eventually this will also be
 * shown as the app starts and network calls are being made to
 * fetch data.
 *************************************************************/

package com.patchx.umbcringer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
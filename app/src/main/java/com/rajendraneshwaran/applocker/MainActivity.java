package com.rajendraneshwaran.applocker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;


public class MainActivity extends AppCompatActivity {

    PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    public static final String TAG ="Pinview";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String accessPin = "AccessPin";
    SharedPreferences sharedpreferences;
    String password;
    public boolean isSuccess = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);

        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();
        mPinLockView.getDeleteButtonPressedColor();

        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String pinValue = sharedpreferences.getString("AccessPin", "");
        if(pinValue.toString().isEmpty())
        {
            // Create Pin
            if(pinValue == password)
            {
                // Password correct!!
            }
        }
        if(!pinValue.toString().isEmpty())
        {
            // Already Pin Created
        }

    }

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d(TAG, "Pin complete: " + pin);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(accessPin, pin);
            editor.commit();

            if(isSuccess)
            {
                isSuccess = false;
                Intent intent = new Intent(MainActivity.this, PinView.class);
                startActivity(intent);
            }

        }

        @Override
        public void onEmpty() {
            Log.d(TAG, "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };

}

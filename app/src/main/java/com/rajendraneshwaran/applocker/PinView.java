package com.rajendraneshwaran.applocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import com.andrognito.pinlockview.PinLockView;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
public class PinView extends AppCompatActivity {

    android.support.v7.widget.GridLayout mainGrid;
    public static final String TAG ="Pinview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_view);

        mainGrid = (android.support.v7.widget.GridLayout) findViewById(R.id.mainGrid);
        setSingleGridLayout(mainGrid);

    }

    private void setSingleGridLayout(android.support.v7.widget.GridLayout mainGrid) {
        for(int i = 0; i < mainGrid.getChildCount();i++)
        {
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalIndex = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (finalIndex) {
                        case 0:
                            Intent intent = new Intent(PinView.this, AppsLock.class);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent pl = new Intent(PinView.this, PhotoLock.class);
                            startActivity(pl);
                            break;
                        case 2:
                            Intent vl = new Intent(PinView.this, VideoLock.class);
                            startActivity(vl);
                            break;
                        case 3:
                            Intent fl = new Intent(PinView.this, FolderLock.class);
                            startActivity(fl);
                            break;
                        case 4:
                            Intent gl = new Intent(PinView.this, GroupAppLock.class);
                            startActivity(gl);
                            break;
                        case 5:
                            Intent flo = new Intent(PinView.this, FilesLock.class);
                            startActivity(flo);
                            break;
                        case 6:
                            Intent sl = new Intent(PinView.this, SiteLock.class);
                            startActivity(sl);
                            break;
                        case 7:
                            Intent nl = new Intent(PinView.this, NoteLock.class);
                            startActivity(nl);
                            break;
                    /*case 8:
                        Intent intent = new Intent(MainActivity.this, AppsLock.class);
                        startActivity(intent);
                        break;
                    case 9:
                        Intent intent = new Intent(MainActivity.this, AppsLock.class);
                        startActivity(intent);
                        break;*/



                        default:
                            break;
                    }
                }
            });
        }
    }
    
}

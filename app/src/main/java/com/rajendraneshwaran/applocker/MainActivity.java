package com.rajendraneshwaran.applocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleGridLayout(mainGrid);
    }

    private void setSingleGridLayout(GridLayout mGridLayout)
    {
        for(int i = 0; i < mGridLayout.getChildCount();i++)
        {
            CardView cardView = (CardView)mGridLayout.getChildAt(i);
            final int finalIndex = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (finalIndex) {
                        case 0:
                        Intent intent = new Intent(MainActivity.this, AppsLock.class);
                        startActivity(intent);
                        break;
                        case 1:
                            Intent pl = new Intent(MainActivity.this, PhotoLock.class);
                            startActivity(pl);
                            break;
                        case 2:
                            Intent vl = new Intent(MainActivity.this, VideoLock.class);
                            startActivity(vl);
                            break;
                        case 3:
                            Intent fl = new Intent(MainActivity.this, FolderLock.class);
                            startActivity(fl);
                            break;
                        case 4:
                            Intent gl = new Intent(MainActivity.this, GroupAppLock.class);
                            startActivity(gl);
                            break;
                        case 5:
                            Intent flo = new Intent(MainActivity.this, FilesLock.class);
                            startActivity(flo);
                            break;
                        case 6:
                            Intent sl = new Intent(MainActivity.this, SiteLock.class);
                            startActivity(sl);
                            break;
                        case 7:
                            Intent nl = new Intent(MainActivity.this, NoteLock.class);
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

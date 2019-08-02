package com.rajendraneshwaran.applocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public GridLayout mainGridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGridLayout = (GridLayout)findViewById(R.id.mainGrid);
        setSingleGridLayout(mainGridLayout);
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
                    }
                }
            });
        }
    }
}

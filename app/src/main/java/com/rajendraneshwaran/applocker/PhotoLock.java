package com.rajendraneshwaran.applocker;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.rajendraneshwaran.applocker.Adapter.PhotoGridAdapter;

import java.io.File;

public class PhotoLock extends AppCompatActivity {

    private String rootPath = "/AppLocker/";
    private GridView gridView;
    private String txt[] ={"1","2","3","4","5"};
    private int gridImage[]={R.drawable.plus1,R.drawable.plus1,R.drawable.test1,R.drawable.test2,R.drawable.test3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_lock);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Photo Lock");

        File dir = new File(Environment.getExternalStorageDirectory()+rootPath+"PhotoLock");
        try{
            if(!dir.exists()) {
                dir.mkdir();
                System.out.println("Directory created");
            } else {
                System.out.println("Directory is not created");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        gridView = (GridView)findViewById(R.id.photoGridView);
        PhotoGridAdapter gridAdapter = new PhotoGridAdapter(PhotoLock.this,gridImage,txt);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), txt[position],Toast.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}

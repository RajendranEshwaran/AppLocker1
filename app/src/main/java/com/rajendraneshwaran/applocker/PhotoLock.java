package com.rajendraneshwaran.applocker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import java.util.ArrayList;
import java.util.List;

public class PhotoLock extends AppCompatActivity {

    private String rootPath = "/AppLocker/";
    private GridView gridView;
    private List<String> imageName= new ArrayList<String>();
    //private int gridImage[]={R.drawable.plus1};
    private List<String> gridImage = new ArrayList<String>();
    private PhotoGridAdapter gridAdapter;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    String imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_lock);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Photo Lock");

        // imageUri = "drawable://" + R.drawable.plus1;

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

        String imageUrl = getURLForResource(R.drawable.plus1);

        // Values adding into ArrayList
        imageName.add("1");
        gridImage.add(imageUrl);

        gridAdapter = new PhotoGridAdapter(PhotoLock.this,gridImage,imageName);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), imageName.get(position),Toast.LENGTH_SHORT ).show();

        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_PICTURE);


            }
        });
    }

    public String getURLForResource (int resourceId) {
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);

                reloadGridView();
               // img.setImageURI(selectedImageUri);
               // imageView.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void reloadGridView()
    {
        imageName.add("3");
        gridAdapter.notifyDataSetChanged();
        gridView.setAdapter(gridAdapter);
        System.out.println("Image Path : " + gridImage.size());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }
}

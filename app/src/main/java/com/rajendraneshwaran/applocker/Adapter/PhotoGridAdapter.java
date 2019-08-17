package com.rajendraneshwaran.applocker.Adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.speech.RecognitionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rajendraneshwaran.applocker.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PhotoGridAdapter extends BaseAdapter {


    //private int icons[];
    private List<String> icons = new ArrayList<String>();
    private List<String> iconsname = new ArrayList<String>();
    private Context context;
    private LayoutInflater layoutInflater;

    public PhotoGridAdapter(Context context, List<String> icons, List<String> iconsname)
    {
        this.context = context;
        this.icons = icons;
        this.iconsname = iconsname;
    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return iconsname.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return iconsname.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) { //items.indexOf(getItem(position));
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       // View gridview = convertView;
        if(convertView == null)
        {
            ContextWrapper cw = new ContextWrapper(context);
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_photolock,null );
            ImageView icon = (ImageView) convertView.findViewById(R.id.icons);

            if(position == 0)
            {
                    icon.setImageResource(R.drawable.plus1);
            }
            else
            {
                try {
                    String path = icons.get(--position);
                    String stringYouWant = path.substring(path.lastIndexOf("/"), path.length());
                    String rootPath="";
                    String sourcePath =stringYouWant;

                    File f=new File("/storage/emulated/0/DCIM/Camera/", stringYouWant);
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                    icon.setImageBitmap(b);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

//            icon.setImageResource(Integer.parseInt(icons.get(position)));
//            File directory = cw.getDir("/Camera/", Context.MODE_PRIVATE);
//            File mypath=new File(directory,"IMG_20190815_120632.jpg");
//            icon.setImageDrawable(Drawable.createFromPath(mypath.toString()));
            //scaleImage(icon);
        }
        return convertView;
    }

    private void scaleImage(ImageView view) {
        Drawable drawing = view.getDrawable();
        if (drawing == null) {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int xBounding = ((View) view.getParent()).getWidth();//EXPECTED WIDTH
        int yBounding = ((View) view.getParent()).getHeight();//EXPECTED HEIGHT

        float xScale = ((float) xBounding) / width;
        float yScale = ((float) yBounding) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(xScale, yScale);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();
        BitmapDrawable result = new BitmapDrawable(context.getResources(), scaledBitmap);

        view.setImageDrawable(result);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }
}

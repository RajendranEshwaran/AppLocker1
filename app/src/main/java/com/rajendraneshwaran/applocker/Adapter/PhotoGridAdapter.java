package com.rajendraneshwaran.applocker.Adapter;

import android.content.Context;
import android.speech.RecognitionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rajendraneshwaran.applocker.R;

public class PhotoGridAdapter extends BaseAdapter {


    private int icons[];
    private String iconsname[];
    private Context context;
    private LayoutInflater layoutInflater;

    public PhotoGridAdapter(Context context,int icons[],String iconsname[])
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
        return iconsname.length;
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
        return iconsname[position];
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
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_photolock,null );
            ImageView icon = (ImageView) convertView.findViewById(R.id.icons);
            icon.setImageResource(icons[position]);
        }
        return convertView;
    }
}

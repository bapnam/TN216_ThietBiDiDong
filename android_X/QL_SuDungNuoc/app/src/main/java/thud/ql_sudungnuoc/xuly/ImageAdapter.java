package thud.ql_sudungnuoc.xuly;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] Images;

    public ImageAdapter(Context context, Integer[] images){
        mContext = context;
        Images = images;
    }

    public  void  setImages(Integer[] images){
        Images = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public Object getItem(int position) {
        return Images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(Images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(
                new ViewGroup.LayoutParams(360, 360)
        );
        imageView.setPadding(10, 30, 10, 30);
        return imageView;
    }
}

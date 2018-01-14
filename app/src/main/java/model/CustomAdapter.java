package model;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yumnaasim.rtirpc.R;
import com.squareup.picasso.Picasso;

public class CustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Context mainActivity, String[] osNameList, int[] osImages) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=mainActivity;
        imageId=osImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LinearLayout gridView;

        if (convertView == null) {

            // get layout from text_item.xml
            gridView = (LinearLayout)inflater.inflate(R.layout.sample_gridlayout, null);

            int screenHeight = ((Activity) context).getWindowManager()
                    .getDefaultDisplay().getHeight();
            int density = Math.round(context.getResources().getDisplayMetrics().density);
            screenHeight = screenHeight-(125*density);
            Log.v("","Height is "+screenHeight+" Density is "+density);

            gridView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.FILL_PARENT, screenHeight/2));

            //gridView.setMinimumHeight(screenHeight/9);
            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.texts);
            textView.setText(result[position]);

            ImageView imageView = (ImageView) gridView.findViewById(R.id.images);
            Picasso.with(context)
                    .load(imageId[position]).into(imageView);
            //imageView.setImageResource(imageId[position]);

        } else {
            gridView = (LinearLayout) convertView;
        }

        return gridView;
    }

}
package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yumnaasim.rtirpc.R;

/**
 * Created by Usman on 3/19/2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null)
        {
            LayoutInflater li;
            li = LayoutInflater.from(context);
            v = li.inflate(R.layout.row_list_view,null);
        }
        TextView recordNo = (TextView) v.findViewById(R.id.recordNo);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView time = (TextView) v.findViewById(R.id.time);

        return v;
    }
}

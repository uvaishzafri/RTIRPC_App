package model;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.yumnaasim.rtirpc.R;

/**
 * Created by Usman on 3/19/2018.
 */
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Records> implements View.OnClickListener {

    public CustomAdapter(Context context,ArrayList<Records> data) {
        super(context, R.layout.row_list_view, data);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Records dataModel = getItem(position);

        View v = convertView;
        if (v==null)
        {
            LayoutInflater li;
            li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.row_list_view, parent, false);
        }
        TextView recordNo = (TextView) v.findViewById(R.id.recordNo);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView time = (TextView) v.findViewById(R.id.time);
        TextView area = (TextView) v.findViewById(R.id.area);

        recordNo.setText("Record # "+dataModel.getRecordNum());
        date.setText("  "+dataModel.getDate());
        time.setText("  "+dataModel.getTime());
        area.setText("Area: "+dataModel.getArea());
        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}

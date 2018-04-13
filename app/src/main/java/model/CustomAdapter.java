package model;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import neduet.softwareeng.yumnaasim.rtirpc.DetailedReportActivity;

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


        recordNo.setText("Record # "+dataModel.getRecordNum());
        date.setText("  "+dataModel.getDate());
        time.setText("  "+dataModel.getTime());

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        TextView recordNum = (TextView) v.findViewById(R.id.recordNo);
        String text = recordNum.getText().toString();
        String[] array = text.split("# ");
        Log.v("Adapter",array[1]);
        int recordNumber = Integer.parseInt(array[1]);

        Intent intent = new Intent(getContext(),DetailedReportActivity.class);
        intent.putExtra("RecordNumber",recordNumber);
        getContext().startActivity(intent);

    }
}

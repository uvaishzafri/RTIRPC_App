package com.example.yumnaasim.rtirpc;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import static com.example.yumnaasim.rtirpc.R.id.graph;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        this.setTitle("Reports");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        displayGraph(graph);

        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        displayGraphLocation(graph1);
    }

    private void displayGraphLocation(GraphView graph) {

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Gulberg", "Uni Rd", "North Naz", "Tariq Rd"});

        GridLabelRenderer gridLabelRenderer =  graph.getGridLabelRenderer();
        gridLabelRenderer.setLabelFormatter(staticLabelsFormatter);
        gridLabelRenderer.setHorizontalAxisTitle("Locations");
        gridLabelRenderer.setVerticalAxisTitle("No. of Accidents");

        graph.setTitle("Accident Reports");
        graph.setTitleTextSize(48);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 12),
                new DataPoint(2, 7),
                new DataPoint(3, 8),
        });
        graph.addSeries(series);
        series.setAnimated(true);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(30);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

    }

    private void displayGraph(GraphView graph) {

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct","Nov", "Dec"});

        GridLabelRenderer gridLabelRenderer =  graph.getGridLabelRenderer();
        gridLabelRenderer.setLabelFormatter(staticLabelsFormatter);
        gridLabelRenderer.setHorizontalAxisTitle("Months");
        gridLabelRenderer.setVerticalAxisTitle("No. of Accidents");

        graph.setTitle("Accident Reports");
        graph.setTitleTextSize(48);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 10),
                new DataPoint(2, 15),
                new DataPoint(3, 20),
                new DataPoint(4, 4),
                new DataPoint(5, 5),
                new DataPoint(6, 10),
                new DataPoint(7, 15),
                new DataPoint(8, 20),
                new DataPoint(9, 19),
                new DataPoint(10, 5),
                new DataPoint(11, 10)
        });
        graph.addSeries(series);
        series.setAnimated(true);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

    }
}

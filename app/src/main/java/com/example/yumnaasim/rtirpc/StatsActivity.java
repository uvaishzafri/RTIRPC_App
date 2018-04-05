package com.example.yumnaasim.rtirpc;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import static com.example.yumnaasim.rtirpc.R.id.cancel_action;
import static com.example.yumnaasim.rtirpc.R.id.container;
import static com.example.yumnaasim.rtirpc.R.id.graph;
import static com.example.yumnaasim.rtirpc.R.id.useLogo;

public class StatsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static View rootView;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle bundle = getArguments();
            int sectionNumber = bundle.getInt(ARG_SECTION_NUMBER);
            rootView = inflater.inflate(R.layout.fragment_stats, container, false);
            TextView textView1 = (TextView) rootView.findViewById(R.id.txt1);
            TextView textView2 = (TextView) rootView.findViewById(R.id.txt2);
            Log.v("Stats", " " + sectionNumber);

            GraphView graphView = (GraphView) rootView.findViewById(R.id.graph);

            switch (sectionNumber){
                case 0:

                    textView1.setText(getResources().getString(R.string.stats2_txt1));
                    textView2.setText(getResources().getString(R.string.stats2_txt2));
                    displayGraphLocation(graphView);
                    break;
                case 1:

                    textView1.setText(getResources().getString(R.string.stats1_txt1));
                    textView2.setText(getResources().getString(R.string.stats1_txt2));
                    displayGraph(graphView);
                    break;

                case 2:

                    textView1.setText(getResources().getString(R.string.stats3_txt1));
                    textView2.setText(getResources().getString(R.string.stats3_txt2));
                    displayGraph3(graphView);
                    break;
            }

                updateViews(rootView);
                return rootView;
        }

        private void updateViews(final View rootView) {
                 /*setting data on spinners*/
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.date_range, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String dateRange = parent.getItemAtPosition(position).toString();

                    TextView textView = (TextView) rootView.findViewById(R.id.date_range);
                    textView.setText(dateRange);

                    TextView textView1 = (TextView) rootView.findViewById(R.id.date_range1);
                    textView1.setText(dateRange);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        private void displayGraphLocation(GraphView graph) {

            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(new String[] {"Highway", "Uni Rd", "Sharea Faisal ", "Tariq Rds"});

            GridLabelRenderer gridLabelRenderer =  graph.getGridLabelRenderer();
            gridLabelRenderer.setLabelFormatter(staticLabelsFormatter);

            gridLabelRenderer.setVerticalAxisTitle("No. of Accidents");

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
            staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "Feb", "Mar", "Apr", "Mays"});

            GridLabelRenderer gridLabelRenderer =  graph.getGridLabelRenderer();
            gridLabelRenderer.setLabelFormatter(staticLabelsFormatter);
            gridLabelRenderer.setVerticalAxisTitle("No. of Accidents");


            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 2),
                    new DataPoint(1, 12),
                    new DataPoint(2, 8),
                    new DataPoint(3, 16),
                    new DataPoint(4, 5)
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

        private void displayGraph3(GraphView graph) {

            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(new String[] {"Car", "Truck", "Bus", "Bike"});

            GridLabelRenderer gridLabelRenderer =  graph.getGridLabelRenderer();
            gridLabelRenderer.setLabelFormatter(staticLabelsFormatter);
            gridLabelRenderer.setVerticalAxisTitle("No. of Accidents");


            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 5),
                    new DataPoint(1, 10),
                    new DataPoint(2, 7),
                    new DataPoint(3, 2),
                    new DataPoint(4, 16)
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
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "STATS 1";
                case 1:
                    return "STATS 2";
                case 2:
                    return "STATS 3";
            }
            return null;
        }
    }
}
package neduet.softwareeng.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yumnaasim.rtirpc.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("About");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

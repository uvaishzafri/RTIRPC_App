package neduet.softwareeng.yumnaasim.rtirpc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yumnaasim.rtirpc.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TextView tv = (TextView) findViewById(R.id.text);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),NavDrawerActivity.class);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }
}

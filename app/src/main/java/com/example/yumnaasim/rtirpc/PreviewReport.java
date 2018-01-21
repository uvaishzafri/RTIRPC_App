package com.example.yumnaasim.rtirpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;

public class PreviewReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_report);
        getSupportActionBar()
                .setTitle(Html.fromHtml("<font color=\"#FFFFFF\">" + "Preview Report" + "</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getView();
    }

    private void getView() {

        WebView webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("file:///android_asset/file.html");
    }
}

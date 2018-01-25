package com.example.billy.teamviewer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WikipediaInformationActivity extends AppCompatActivity {

    WebView webView;

    // variables for parsing
    Document doc;
    Elements e;
    String url;

    TextView webCaptionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia_information);

        // wire widgets
        webView = (WebView) findViewById(R.id.webView);
        webCaptionTV = (TextView) findViewById(R.id.webCaptionTextView);

        // Get Player data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Player player = (Player) bundle.getSerializable("data");

        // Get player url
        url = player.getUrl();

        webView.getSettings().setJavaScriptEnabled(false);

        webView.setWebViewClient(new WebViewClient());

        // starts the asynchronous task
        new LoadPage().execute();

    }

    // Asynchronous task for loading, and parsing webpage
    private class LoadPage extends AsyncTask<Void, Void, Void> {

        ProgressDialog pd = new ProgressDialog(WikipediaInformationActivity.this);

        @Override
        protected void onPreExecute(){
            pd.setTitle("Scraping Wikipedia");
            pd.setMessage("Loading Information");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params){

            try {
                // Get the webpage
                doc = Jsoup.connect(url).get();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            if(doc!=null){

                String classToSelect = "table.infobox.vcard";

                e = doc.select(classToSelect);

                //Select first caption (player's name) and remove.
                Element ele = e.select("caption").first();
                //Set the caption to TextView
                webCaptionTV.setText(ele.text().toString());
                //Remove the caption from document
                ele.remove();

                //Select last <tr> and remove, it contains useless information
                ele = e.select("tr").last();
                ele.remove();

                String html = e.toString();
                String mime = "text/html";
                String enc = "utf-8";

                webView.loadDataWithBaseURL(url, html, mime, enc, null);

                pd.cancel();

            }
        }
    }
}

package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.wordpress.ayo218.androidlibrary.JokeActivity;
import com.wordpress.ayo218.javalibrary.JokeTelling;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private JokeTelling jokeTelling;
    private ProgressBar progressBar;
    private String joke_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeTelling = new JokeTelling();
        progressBar = findViewById(R.id.progress_bar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void tellJoke(View view) {
        progressBar.setVisibility(View.VISIBLE);
        new EndPointAsyncTask().execute(this);
    }

    public void displayJoke(){
        Intent intent  = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.INTENT_JOKE, joke_result);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    class EndPointAsyncTask extends AsyncTask<Context, Void, String> {
        private static final String TAG = "EndPointAsyncTask";
        private MyApi myApi = null;
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            if (myApi == null) {
                MyApi.Builder builder =  new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                request.setDisableGZipContent(true);
                            }
                        });
                myApi = builder.build();
            }
            context = params[0];

            try{
                return myApi.getJoke().execute().getData();
            } catch (IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e(TAG, "onPostExecute: " + s);
            joke_result = s;
            progressBar.setVisibility(View.INVISIBLE);
            displayJoke();
        }
    }
}

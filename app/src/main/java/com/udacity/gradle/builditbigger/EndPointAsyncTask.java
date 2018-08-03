package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndPointAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApi = null;
    private JokeListener listener;

    public EndPointAsyncTask(JokeListener listener) {
        this.listener = listener;
    }

    public EndPointAsyncTask(){}

    @Override
    protected String doInBackground(Void... voids) {
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

        try{
            return myApi.getJoke().execute().getData();
        } catch (IOException e){
            Log.e("EndPointAsyncTask", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.receiveJoke(s);
    }
}

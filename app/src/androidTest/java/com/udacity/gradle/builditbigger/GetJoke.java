package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.os.AsyncTask;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class GetJoke extends InstrumentationTestCase{
    public static final String EMPTY_STRING = "";

    MyApi myApi = null;
    Context context = InstrumentationRegistry.getContext();

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void tellJoke(){

        onView(withId(R.id.btn)).perform(click());

        onView(withId(R.id.txt_joke)).check(matches(not(withText(EMPTY_STRING))));
    }

    @Test
    public void testJoke() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);


        final AsyncTask<Context, Void, String> endPointAsyncTask = new AsyncTask<Context, Void, String>(){

            @Override
            protected String doInBackground(Context... contexts) {
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
                context = contexts[0];

                try{
                    return myApi.getJoke().execute().getData();
                } catch (IOException e){
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                signal.countDown();
            }
        };



        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                endPointAsyncTask.execute();
            }
        });
        signal.await(30, TimeUnit.SECONDS);


    }
}

package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GetJoke{
    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void getTest() throws Throwable {
        final EndPointAsyncTask task = new EndPointAsyncTask(){
            @Override
            protected String doInBackground(Void... voids) {
                return super.doInBackground(voids);
            }

            @Override
            protected void onPostExecute(String s) {
                assertNotNull(s);
                assertFalse(s.equals(""));
                signal.countDown();
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        };

        runnable.run();

        signal.await(30, TimeUnit.SECONDS);
    }
}

package com.example.joke;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


import android.os.Handler;
import android.widget.Toast;

import java.util.logging.LogRecord;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DelayedMessageService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";
    private Handler handler;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        handler= new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
               wait(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            String text=intent.getStringExtra(EXTRA_MESSAGE);
            showText(text);
        }
    }

    private void showText(final String text) {

       handler.post(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
           }
       });

    }

}

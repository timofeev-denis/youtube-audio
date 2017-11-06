package ru.code4fun.youtubeaudio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by denis on 11/6/17.
 */

public class PlayerService extends Service {

    public static final String TAG = "--- PlayerService ---";
    private final IBinder mBinder = new PlayerServiceBinder();

    public class PlayerServiceBinder extends Binder {
        PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    public void play() {
        Log.v(TAG, "start playback");
    }

    public void stop() {
        Log.v(TAG, "stop playback");
    }

}

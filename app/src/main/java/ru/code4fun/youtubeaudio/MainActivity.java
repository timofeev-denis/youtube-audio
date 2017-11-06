package ru.code4fun.youtubeaudio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playAudioButton;
    private Button stopAudioButton;
    private Button stopServiceButton;
    private Intent intent;
    private PlayerService playerService;
    private ServiceConnection mConnection = new PlayerServiceConnection();
    public static final String TAG = "*** MainActivity ***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate");

        playAudioButton = (Button) findViewById(R.id.btn_play);
        playAudioButton.setOnClickListener(this);
        stopAudioButton = (Button) findViewById(R.id.btn_stop);
        stopAudioButton.setOnClickListener(this);
        stopServiceButton = (Button) findViewById(R.id.btn_stop_service);
        stopServiceButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
        this.intent = new Intent(this, PlayerService.class);
        startService(this.intent);
        bindService(this.intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
        unbindService(mConnection);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(playAudioButton)) {
            Log.v(TAG, "Play button clicked");
            playerService.play();
        } else if (view.equals(stopAudioButton)) {
            Log.v(TAG, "Stop button clicked");
            playerService.stop();
        } else if (view.equals(stopServiceButton)) {
            Log.v(TAG, "Stop SERVICE button clicked");
            stopService(this.intent);
        }
    }

    private class PlayerServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            playerService = ((PlayerService.PlayerServiceBinder) iBinder).getService();
            Log.v(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(TAG, "onServiceDisconnected");
        }
    };

}

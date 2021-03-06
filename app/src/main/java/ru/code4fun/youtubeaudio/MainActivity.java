package ru.code4fun.youtubeaudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.youtube.YouTube;

public class MainActivity extends AppCompatActivity {

    public static final String DEVELOPER_KEY = "AIzaSyDQITJULzxdC6WT_xtYijNqYzsWsQxgtDk";
    private static final JsonFactory JSON_FACTORY = AndroidJsonFactory.getDefaultInstance();
    private static HttpTransport HTTP_TRANSPORT;
    private static YouTube youTube;
    private Button playButton;
    private WebView wv_view;

    static {
        try {
            HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.btn_play);
        wv_view = (WebView) findViewById(R.id.wv_video);
        wv_view.getSettings().setJavaScriptEnabled(true);
        wv_view.setWebChromeClient(new WebChromeClient());

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                wv_view.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7kD0ZYzJbYo\" frameborder=\"0\" allowfullscreen></iframe>", "text/html", "utf-8");
            }
        });
    }

    protected YouTube getYouTubeService() {

        return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, null).build();
    }
}

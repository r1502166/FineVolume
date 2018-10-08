package com.example.android.finevolume;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AudioManager audio;
    private int currentVolume;
    private int maxVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        displayVolume();

        Button btnMinus = findViewById(R.id.minus_button);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (currentVolume > 0) {
                    currentVolume--;
                }
                displayVolume();
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

        Button btnPlus = findViewById(R.id.plus_button);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (currentVolume < maxVolume) {
                    currentVolume++;
                }
                displayVolume();
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

    }

    private void displayVolume() {
        TextView label = findViewById(R.id.volume_textView);
        label.setText(Integer.toString(currentVolume)+'/'+Integer.toString(maxVolume));
    }
}

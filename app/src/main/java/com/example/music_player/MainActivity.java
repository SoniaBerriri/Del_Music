package com.example.music_player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
/** Ajout automatique de la lecture **/

private static final String TAG = "MainActivity"; // pour tester



MediaPlayer mediaPlayer = new MediaPlayer();


/**** Méthode pour le fonctionnement de l'application ****/

public void play(View view){

    mediaPlayer.start();
    Log.i (TAG,"Play");


}

public void  pause (View view){
    mediaPlayer.pause();

}
// START volume
private void volume(){
    // Association de side barre en java
    SeekBar sbVolume = findViewById(R.id.sbVolume);

    //Initialiser le manager en tant que service
    AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

    // Volume max du terminal
    int volumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    // Valorisation de cette valeur au max de seekbar
    sbVolume.setMax(volumeMax);

    int currentVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    sbVolume.setProgress(currentVolume);


    sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i(TAG, "onProgressChanged : Volume = "+ Integer.toString(progress));
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });


}
// END volume

    private  void position(){
    SeekBar sbPosition = findViewById(R.id.sbPosition);
    // Définir la  valeu max de la seekbar
        sbPosition.setMax(mediaPlayer.getDuration());

        // gestion  one la gestion du déplacement du curseur par l utisateur
         sbPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                 Log.i(TAG, "Position dans la morceau :" + Integer.toString(progress));
                 

             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {
                 pause(sbPosition);

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {
                 play(sbPosition);
                 mediaPlayer.seekTo(sbPosition.getProgress());
             }
         });

         //Part Two  gestion du déplacement du curseur par l'application
         new Timer(). scheduleAtFixedRate(new TimerTask() {
             @Override
             public void run() {
                 //Deplacement automatique
                 sbPosition.setProgress(mediaPlayer.getCurrentPosition());
             }
         },0,300);

    }

    /**************Cycles de vie ***********/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /**Lecture automatique du son mediaPlayer = MediaPlayer. create(this,R.raw.sound);
       * mediaPlayer.start();
       * **/

      /** Méthode 2  avec les boutons **/
        //mediaPlayer = MediaPlayer.create(this,R.raw.sound);
      // mediaPlayer.start();
        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

         volume();
         position();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
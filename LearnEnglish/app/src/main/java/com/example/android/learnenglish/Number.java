package com.example.android.learnenglish;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.STREAM_MUSIC;

public class Number extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager am;
    private MediaPlayer.OnCompletionListener comletionListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

               releaseMediaPlayer();
        }
    };
private  AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
    @Override
    public void onAudioFocusChange(int focusChange) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
            // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
            // our app is allowed to continue playing sound but at a lower volume. We'll treat
            // both cases the same way because our app is playing short sound files.

            // Pause playback and reset player to the start of the file. That way, we can
            // play the word from the beginning when we resume playback.
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
            mediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            // The AUDIOFOCUS_LOSS case means we've lost audio focus and
            // Stop playback and clean up resources
            releaseMediaPlayer();
        }
    }

};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

     final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "واحد", R.drawable.number_one,R.raw.number_one ));
        words.add(new Word("two", "اثنان", R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "ثلاثة", R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "اربعه", R.drawable.number_four,R.raw.number_three));
        words.add(new Word("five", "خمسه", R.drawable.number_five,R.raw.number_three));
        words.add(new Word("six", "سته", R.drawable.number_six,R.raw.number_three));
        words.add(new Word("seven", "سبعه", R.drawable.number_seven,R.raw.number_three));
        words.add(new Word("eight", "ثمانيه", R.drawable.number_eight,R.raw.number_three));
        words.add(new Word("nine", "تسعه", R.drawable.number_nine,R.raw.number_three));
        words.add(new Word("ten", "عشره", R.drawable.number_ten,R.raw.number_three));

        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.numberCategory);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Word wordObject=words.get(position);

                releaseMediaPlayer();
             int result  = am.requestAudioFocus (audioFocusChangeListener, STREAM_MUSIC,
                        AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mediaPlayer = MediaPlayer.create(Number.this,wordObject.getAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(comletionListener);
                }



            }
        });










    }
    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            am.abandonAudioFocus(audioFocusChangeListener);

        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

}


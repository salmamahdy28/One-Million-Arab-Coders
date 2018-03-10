package com.example.android.learnenglish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class ColorActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

                 releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

      final  ArrayList<Word> words = new ArrayList<>();
        words.add( new Word("red","احمر",R.drawable.color_red,R.raw.color_red));
        words.add( new Word("green","الاخضر",R.drawable.color_green,R.raw.color_brown));
        words.add(  new Word("blue","الازرق",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(  new Word("white","الابيض",R.drawable.color_white,R.raw.color_dusty_yellow));
        words.add(  new Word("black","الاسود",R.drawable.color_black,R.raw.color_dusty_yellow));
        words.add( new Word("yellow","الاصفر",R.drawable.color_mustard_yellow,R.raw.color_dusty_yellow));
        words.add( new Word("brown","البني",R.drawable.color_brown,R.raw.color_dusty_yellow));
        words.add( new Word("cyan","الرمادي",R.drawable.color_gray,R.raw.color_dusty_yellow));

        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.colorCategory);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordObject=words.get(position);

                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(ColorActivity.this,wordObject.getAudio());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

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
        }
    }

}

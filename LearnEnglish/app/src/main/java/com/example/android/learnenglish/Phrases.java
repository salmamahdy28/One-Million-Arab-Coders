package com.example.android.learnenglish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    MediaPlayer mediaPlayer;
  MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            releaseMediaPlayer();

        }
    };


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

       final ArrayList<Word> words = new ArrayList<>();
        words.add( new Word("what is your name ?","ما اسمك ؟",R.raw.phrase_are_you_coming));
        words.add( new Word("how old are you ?","ما عمرك ؟",R.raw.phrase_how_are_you_feeling));
        words.add(  new Word("the weather is hot ","الطقس حار ",R.raw.bird));


        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.phrasesCategory);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordObject=words.get(position);

                releaseMediaPlayer();



                mediaPlayer = MediaPlayer.create(Phrases.this,wordObject.getAudio());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(completionListener );


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
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

}

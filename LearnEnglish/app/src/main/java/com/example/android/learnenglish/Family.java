package com.example.android.learnenglish;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class Family extends AppCompatActivity {

    MediaPlayer mediaPlayer;

  private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            /**
             * Clean up the media player by releasing its resources.
             */
            releaseMediaPlayer();
            // Toast.makeText(Family.this, "done !!! ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

      final  ArrayList<Word> words = new ArrayList<>();

        words.add( new Word("father","الاب",R.drawable.family_father,R.raw.family_daughter));
        words.add( new Word("mother","الام",R.drawable.family_mother,R.raw.family_mother));
        words.add(  new Word("older sister","الاخت الكبري",R.drawable.family_older_sister,R.raw.family_mother));
        words.add(  new Word("older brother","الاخ الاكبر",R.drawable.family_older_brother,R.raw.family_mother));
        words.add(  new Word("young brother","الاخ الاصغر",R.drawable.family_younger_brother,R.raw.family_mother));
        words.add(  new Word("young sister","الاخت الصغري",R.drawable.family_younger_sister,R.raw.family_mother));
        words.add(  new Word("grandFather","الجد",R.drawable.family_grandfather,R.raw.family_mother));
        words.add(  new Word("grandMother","الجده",R.drawable.family_grandmother,R.raw.family_mother));
        words.add(  new Word("son","الابن",R.drawable.family_son,R.raw.family_mother));
        words.add(  new Word("daughter","الابنه",R.drawable.family_daughter,R.raw.family_mother));




        // ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.familyCategory);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word wordObject=words.get(position);

                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(Family.this,wordObject.getAudio());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletionListener);
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

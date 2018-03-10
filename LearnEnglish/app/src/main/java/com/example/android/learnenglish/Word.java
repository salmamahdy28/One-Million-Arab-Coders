
package com.example.android.learnenglish;
/**
 * Created by saloom on 17/02/2018.
 */

public class Word {

    private String englishWord ;
    private String arabicWord ;
    private int ImageResourceId = NO_IMAGE_PROVIDED;
    private  int audio;

    private static  final int NO_IMAGE_PROVIDED = -1;

    public int getAudio() {
        return audio;
    }

    public int getImageResourceId() {
        return ImageResourceId;
    }

    public String getArabicWord() {
        return arabicWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public Word(String englishWord, String arabicWord,int audio){
        this.englishWord = englishWord;
        this.arabicWord = arabicWord;
        this.audio=audio;

    }
    public Word (String englishWord, String arabicWord ,int ImageResourceId, int audio){

        this.englishWord = englishWord;
        this.arabicWord = arabicWord;
        this.ImageResourceId=ImageResourceId;
        this.audio=audio;
    }

public boolean hasImage(){
    return ImageResourceId != NO_IMAGE_PROVIDED;

}

    @Override
    public String toString() {
        return "Word{" +
                "englishWord='" + englishWord + '\'' +
                ", arabicWord='" + arabicWord + '\'' +
                ", ImageResourceId=" + ImageResourceId +
                ", audio=" + audio +
                '}';
    }
}

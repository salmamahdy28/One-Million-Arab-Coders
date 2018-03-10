package com.example.android.learnenglish;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saloom on 23/02/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColor ;

    /* this is a constructor that differ from the constructor of super class
    the context i s used to inflate layout file

    @param context . The current context, used to inflate the layout file .
    @param listItems . A List of Word objects to display in a list
  */

    public WordAdapter(Context context, ArrayList<Word> listItems ,int color) {

        // here, we itialize the ArrayAdapter's internal storage for context and list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
// Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,listItems);
        backgroundColor= color;
    }

    /* provide view for (ListView, GridView,....etc )
    * @param position ,The position of data list item that should be displayed in the listView
   * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
   * @return The View for the position in the AdapterView
 */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWordObject = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView arabicTextView = (TextView) listItemView.findViewById(R.id.arabicTextView);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        arabicTextView.setText(currentWordObject.getArabicWord());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.englishTextView);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishTextView.setText(currentWordObject.getEnglishWord());

        // find the ImageView in the list_item.xml layout
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.img);

        if(currentWordObject.hasImage()) {

            // get the resource id for the image from the current object
            // set image to the ImageView
            imageView.setImageResource(currentWordObject.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }

        View textContainer =  listItemView.findViewById(R.id.layoutOf2TextViews);
     int color = ContextCompat.getColor(getContext(),backgroundColor);
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }






}
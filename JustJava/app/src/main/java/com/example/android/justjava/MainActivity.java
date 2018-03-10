package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        // if user choose cream
        boolean hasWhippedCream;
        CheckBox checkBox=(CheckBox) findViewById(R.id.whippedCream);
        hasWhippedCream =checkBox.isChecked();

        // if user choose chocolate
        boolean  hasChocolate;
        CheckBox checkBox2=(CheckBox) findViewById(R.id.choclate);
        hasChocolate =checkBox2.isChecked();

        //get name of user
        EditText editText=(EditText) findViewById(R.id.nameOfUser);
        String name=editText.getText().toString();


        int price = calculatePrice(quantity,hasWhippedCream,hasChocolate);
        String message=createOrderSummary(price,hasWhippedCream,hasChocolate,name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
       // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject,name) );
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);
        }
    }

    public void increment(View view) {
        if(quantity ==100) {
          return;
        }
            quantity = quantity + 1;
            display(quantity);
    }


    public void decrement(View view) {
        if(quantity == 1){
            return;}
        quantity = quantity - 1;
        display(quantity);
    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     *  @param hasChocolate is whether the user choose chocolate
     *  @param hasWhippedCream is whether the user choose cream
     *  @return price total of order
     */
    private int calculatePrice(int quantity,boolean hasWhippedCream,boolean hasChocolate) {
        int price;
        if(hasWhippedCream && hasChocolate){
              price = (1+5+2) * quantity ;
        }
        else if(hasChocolate){
            price = (5+2) * quantity ;
        }
        else if (hasWhippedCream){
            price = (1+5) * quantity ;
        }
        else{
            price = 5 * quantity ;
        }

        return price;
    }

   // @SuppressLint("StringFormatInvalid")
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name ) {
        String priceMessage =getString(R.string.orderSummaryName,name)+"\n";
        priceMessage +=getString(R.string.orderSummary_AddWhippedCream,hasWhippedCream)+"\n";
        priceMessage +=getString(R.string.orderSummary_AddChocolate,hasChocolate)+"\n";
        priceMessage +=getString(R.string.QuantityOrder_summary,quantity) + "\n";
        priceMessage += getString(R.string.orderSummaryTotal,price) + "\n";
        priceMessage += getString(R.string.thank_you);

        return priceMessage;
    }


}
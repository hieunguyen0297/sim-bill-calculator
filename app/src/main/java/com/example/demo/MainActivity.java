package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private EditText priceAmount;
    private SeekBar seekBar;
    private TextView percent;
    private TextView tipAmount;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceAmount = findViewById(R.id.priceAmount);
        seekBar = findViewById(R.id.seekBar);
        percent = findViewById(R.id.percentLabel);
        tipAmount = findViewById(R.id.tipAmount);
        totalAmount = findViewById(R.id.totalAmount);

        //Give the percent label a initial value
        percent.setText("0%");

        //Add event for the seek bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("main", "onProgressChanged: "+i);
                percent.setText(i + "%");
                computeTipAndTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        priceAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("textedit", "afterTextChanged: " + editable);
                //call a function
                computeTipAndTotal();
            }
        });

    }

    private void computeTipAndTotal() {
        //Get value of the price and tip percent
        int price = Integer.parseInt(String.valueOf(priceAmount.getText()));
        int tipPercent = seekBar.getProgress();

        //Compute tip & total
        double tip = tipPercent * price / 100;
        double total = price + tip;

        //Update the UI to show the result, because the UI contains string text so have to convert
        tipAmount.setText(String.valueOf(tip));
        totalAmount.setText(String.valueOf(total));




    }


}
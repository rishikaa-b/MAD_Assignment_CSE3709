package com.example.q1_currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    Spinner spFrom, spTo;
    Button btnConvert;
    TextView tvResult;

    String[] currencies = {"INR", "USD", "EUR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML with Java
        etAmount = findViewById(R.id.etAmount);
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        // Spinner setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);

        // Button click
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountStr = etAmount.getText().toString();

        if (amountStr.isEmpty()) {
            tvResult.setText("Enter amount");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        String from = spFrom.getSelectedItem().toString();
        String to = spTo.getSelectedItem().toString();

        double result = amount;

        if (from.equals("INR") && to.equals("USD")) {
            result = amount / 83;
        } else if (from.equals("USD") && to.equals("INR")) {
            result = amount * 83;
        } else if (from.equals("INR") && to.equals("EUR")) {
            result = amount / 90;
        } else if (from.equals("EUR") && to.equals("INR")) {
            result = amount * 90;
        }

        tvResult.setText("Result: " + result);
    }
}
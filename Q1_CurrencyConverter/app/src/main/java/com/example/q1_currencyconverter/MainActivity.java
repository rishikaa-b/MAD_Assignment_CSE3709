package com.example.q1_currencyconverter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    Spinner spFrom, spTo;
    Button btnConvert;
    TextView tvResult;
    Switch themeSwitch;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Load saved theme BEFORE super.onCreate
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML
        themeSwitch = findViewById(R.id.themeSwitch);
        etAmount = findViewById(R.id.etAmount);
        spFrom = findViewById(R.id.spFrom);
        spTo = findViewById(R.id.spTo);
        btnConvert = findViewById(R.id.btnConvert);
        tvResult = findViewById(R.id.tvResult);

        // Set switch state
        themeSwitch.setChecked(isDark);

        // Theme toggle logic
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
                editor.putBoolean("dark_mode", isChecked);
                editor.apply();

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

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
            public void onClick(View view) {
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

        // INR ↔ USD
        if (from.equals("INR") && to.equals("USD")) {
            result = amount / 83;
        } else if (from.equals("USD") && to.equals("INR")) {
            result = amount * 83;
        }

        // INR ↔ EUR
        else if (from.equals("INR") && to.equals("EUR")) {
            result = amount / 90;
        } else if (from.equals("EUR") && to.equals("INR")) {
            result = amount * 90;
        }

        // USD ↔ EUR
        else if (from.equals("USD") && to.equals("EUR")) {
            result = amount * 0.9;
        } else if (from.equals("EUR") && to.equals("USD")) {
            result = amount * 1.1;
        }

        // INR ↔ JPY
        else if (from.equals("INR") && to.equals("JPY")) {
            result = amount * 1.8;
        } else if (from.equals("JPY") && to.equals("INR")) {
            result = amount / 1.8;
        }

        // USD ↔ JPY
        else if (from.equals("USD") && to.equals("JPY")) {
            result = amount * 150;
        } else if (from.equals("JPY") && to.equals("USD")) {
            result = amount / 150;
        }

        // EUR ↔ JPY
        else if (from.equals("EUR") && to.equals("JPY")) {
            result = amount * 160;
        } else if (from.equals("JPY") && to.equals("EUR")) {
            result = amount / 160;
        }

        tvResult.setText("Result: " + result);
    }
}
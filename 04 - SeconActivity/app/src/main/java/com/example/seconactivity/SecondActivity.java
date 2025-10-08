package com.example.seconactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewReceived = findViewById(R.id.tvMSG); // TextView en tu layout

        // Recuperar el mensaje
        String message = getIntent().getStringExtra("message_key");
        textViewReceived.setText(message);
    }
}

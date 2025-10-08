package eus.ainhizearrese.counterapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import eus.ainhizearrese.counterapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int sumResult = 0;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Textua HardCodeatu gabe jartzeko.
        binding.txtClickSum.setText(String.valueOf(sumResult) + " " + getString(R.string.textContador));

        /* Bestela aplikazioa bugeau eitten da:
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        binding.btnSum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sumResult++;
                binding.txtClickSum.setText(String.valueOf(sumResult) + " "  + getString(R.string.textContador));

                /*  modura begiratu.
                * mezua = getString(R.string.textContador)
                * */

            }
        });
    }
}
























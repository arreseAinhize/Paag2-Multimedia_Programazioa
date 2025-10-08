package eus.ainhizearrese.p1multicounter;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import eus.ainhizearrese.p1multicounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private  int SumResultTot = 0;
    private  int SumResult1 = 0;
    private  int SumResult2 = 0;
    private  int SumResult3 = 0;
    private  int SumResult4 = 0;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* SUM BUTTONS */
        binding.btnSum1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResult1++;
                SumResultTot++;
                binding.txtCount1.setText(String.valueOf(SumResult1));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });

        binding.btnSum2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResult2++;
                SumResultTot++;
                binding.txtCount2.setText(String.valueOf(SumResult2));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));

            }
        });

        binding.btnSum3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResult3++;
                SumResultTot++;
                binding.txtCount3.setText(String.valueOf(SumResult3));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));

            }
        });

        binding.btnSum4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResult4++;
                SumResultTot++;
                binding.txtCount4.setText(String.valueOf(SumResult4));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));

            }
        });

        /* RESET BUTTONS */
        binding.btnRestAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResult1 = 0;
                SumResult2 = 0;
                SumResult3 = 0;
                SumResult4 = 0;
                SumResultTot = 0;
                binding.txtCount1.setText(String.valueOf(SumResult1));
                binding.txtCount2.setText(String.valueOf(SumResult2));
                binding.txtCount3.setText(String.valueOf(SumResult3));
                binding.txtCount4.setText(String.valueOf(SumResult4));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });

        binding.btnRest1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResultTot -= SumResult1 ;
                SumResult1 = 0;
                binding.txtCount1.setText(String.valueOf(SumResult1));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });

        binding.btnRest2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResultTot -= SumResult2 ;
                SumResult2 = 0;
                binding.txtCount2.setText(String.valueOf(SumResult2));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });

        binding.btnRest3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResultTot -= SumResult3 ;
                SumResult3 = 0;
                binding.txtCount3.setText(String.valueOf(SumResult3));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });

        binding.btnRest4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SumResultTot -= SumResult4 ;
                SumResult4 = 0;
                binding.txtCount4.setText(String.valueOf(SumResult4));
                binding.txtSumTot.setText(String.valueOf(SumResultTot));
            }
        });
    }
}
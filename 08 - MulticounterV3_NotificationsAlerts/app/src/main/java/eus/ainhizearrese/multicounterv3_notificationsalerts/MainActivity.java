package eus.ainhizearrese.multicounterv3_notificationsalerts;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import eus.ainhizearrese.multicounterv3_notificationsalerts.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int SumResultTot, SumResult1, SumResult2, SumResult3, SumResult4;
    private static final String CHANNEL_ID = "CounterChannel";
    private static final int NOTIFICATION_ID = 1001;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        botoienJokaera();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void botoienJokaera() {
        /* SUM BUTTONS */
        binding.btnSum1.setOnClickListener(view -> {
            SumResult1++;
            SumResultTot++;
            binding.txtCount1.setText(String.valueOf(SumResult1));
            binding.txtSumTot.setText(String.valueOf(SumResultTot));

            Snackbar.make(view, "1. kontadorera balio bat gehitu da", Snackbar.LENGTH_LONG)
                    .setAction("desegin", v -> {
                        SumResult1--;
                        SumResultTot--;
                        binding.txtCount1.setText(String.valueOf(SumResult1));
                        binding.txtSumTot.setText(String.valueOf(SumResultTot));
                    }).show();
        });

        binding.btnSum2.setOnClickListener(v -> {
            SumResult2++;
            SumResultTot++;
            binding.txtCount2.setText(String.valueOf(SumResult2));
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
            Toast.makeText(MainActivity.this, "2. botoiak balioa gehitu du.", Toast.LENGTH_SHORT).show();
        });

        binding.btnSum3.setOnClickListener(v -> {
            SumResult3++;
            SumResultTot++;
            binding.txtCount3.setText(String.valueOf(SumResult3));
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
            Toast.makeText(MainActivity.this, "3. botoiak balioa gehitu du.", Toast.LENGTH_SHORT).show();
        });

        binding.btnSum4.setOnClickListener(v -> {
            SumResult4++;
            SumResultTot++;
            binding.txtCount4.setText(String.valueOf(SumResult4));
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
            Toast.makeText(MainActivity.this, "4. botoiak balioa gehitu du.", Toast.LENGTH_SHORT).show();
        });

        /* RESET ALL BUTTON con AlertDialog */
        binding.btnRestAll.setOnClickListener(view -> {
            SumResult1 = 0;
            SumResult2 = 0;
            SumResult3 = 0;
            SumResult4 = 0;
            SumResultTot = 0;
            binding.txtCount1.setText(SumResult1);
            binding.txtCount2.setText(SumResult2);
            binding.txtCount3.setText(SumResult3);
            binding.txtCount4.setText(String.valueOf(SumResult4));
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
        });

        /* RESET individuales */
        binding.btnRest1.setOnClickListener(v -> {
            SumResultTot -= SumResult1;
            SumResult1 = 0;
            binding.txtCount1.setText("0");
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
        });

        binding.btnRest2.setOnClickListener(v -> {
            SumResultTot -= SumResult2;
            SumResult2 = 0;
            binding.txtCount2.setText("0");
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
        });

        binding.btnRest3.setOnClickListener(v -> {
            SumResultTot -= SumResult3;
            SumResult3 = 0;
            binding.txtCount3.setText("0");
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
        });

        binding.btnRest4.setOnClickListener(v -> {
            SumResultTot -= SumResult4;
            SumResult4 = 0;
            binding.txtCount4.setText("0");
            binding.txtSumTot.setText(String.valueOf(SumResultTot));
        });
    }
}

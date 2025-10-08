package eus.ainhizearrese.bizitza_zikloa;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BizitzaZikloa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showMessage("onCreate exekutatu da");

    }

    @Override
    protected void onStart() {
        super.onStart();
        showMessage("onStart exekutatu da");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showMessage("onResume exekutatu da");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showMessage("onPause exekutatu da");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showMessage("onStop exekutatu da");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showMessage("onRestart exekutatu da");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showMessage("onDestroy exekutatu da");
    }

    private void showMessage(String mezua) {
        // Toast pantailan
        Toast.makeText(this, mezua, Toast.LENGTH_SHORT).show();

        // Logcat-en agertuko da
        Log.d(TAG, mezua);
    }
}
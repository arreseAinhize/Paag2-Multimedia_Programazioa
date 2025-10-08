package eus.arreseainhize.seconactivityv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText textSend;
    private Button btnSend;
    private TextView tvMsgReceived;
    private TextView tvMSG;

    public static final String EXTRA_MESSAGE = "message_key";
    public static final String EXTRA_REPLY = "reply_key";

    // Launcher para abrir SecondActivity y recibir la respuesta
    private final ActivityResultLauncher<Intent> startForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String reply = result.getData().getStringExtra(EXTRA_REPLY);
                            if (reply != null) {
                                // Mostrar el título "Mensaje recibido"
                                tvMsgReceived.setVisibility(TextView.VISIBLE);

                                // Mostrar el contenido de la respuesta
                                tvMSG.setText(reply);
                                tvMSG.setVisibility(TextView.VISIBLE);
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Log.d("MainActivity", "SecondActivity cancelada");
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSend = findViewById(R.id.textSend);
        btnSend = findViewById(R.id.btnSend);
        tvMsgReceived = findViewById(R.id.tvMsgReceived);
        tvMSG = findViewById(R.id.tvMSG);

        btnSend.setOnClickListener(v -> launchSecondActivity());
    }

    private void launchSecondActivity() {
        String message = textSend.getText().toString();
        if (!message.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, SeconActivity.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            textSend.setText("");
            startForResult.launch(intent);
        }
    }
}

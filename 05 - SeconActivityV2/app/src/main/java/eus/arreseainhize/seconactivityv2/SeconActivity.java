package eus.arreseainhize.seconactivityv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SeconActivity extends AppCompatActivity {

    private TextView tvReceived;
    private EditText etReply;
    private Button btnReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvReceived = findViewById(R.id.tvReceived);
        etReply = findViewById(R.id.etReply);
        btnReply = findViewById(R.id.btnReply);

        // Mostrar el mensaje recibido de MainActivity
        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message != null) {
            tvReceived.setText(message);
        }

        // Enviar respuesta de vuelta
        btnReply.setOnClickListener(v -> {
            String reply = etReply.getText().toString();
            if (!reply.isEmpty()) {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(MainActivity.EXTRA_REPLY, reply);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}

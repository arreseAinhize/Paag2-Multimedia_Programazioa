package eus.ainhizearrese.implicitactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import eus.ainhizearrese.implicitactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // WEB ORRIAK ZALATU
        binding.btnWebUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log-ak, nondik goazen jakiteko dira. --> TAG: appInfoBtn

                Log.i("appInfoBtn","btnWebSite botoia sakatu da.");

                String url = binding.etWebUrl.getText().toString().trim();  // EditText-etik erabiltzaileak idatzitakoa lortu eta hutsuneak kendu

                // Erabiltzaileak ezer idatzi ez badu, ez jarraitu eta log-ean errorea erakutsi
                if (url.isEmpty()) {
                    Log.e("appInfoBtn", "URL hutsa dago");
                    return;
                }

                // URLa http:// edo https://-rekin hasten ez bada, automatikoki gehitu
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }

                Uri webpage = Uri.parse(url); // Testua Uri objektu bihurtu (Android-ek ulertzeko formatua)

                Intent intent = new Intent(Intent.ACTION_VIEW, webpage); // Intent inplizitua sortu --> nabigatzailean URL hau ireki nahi dugu

                try { // Intent abiarazi --> nabigatzailea ireki eta URLa erakutsi

                    startActivity(intent);
                    Log.i("appInfoBtn","Intent ondo abiarazi da");
                } catch (Exception e) { // Ez badago aplikaziorik intent hau kudeatzeko (adib. nabigatzailerik ez),
                    Log.e("appInfoBtn","Ez dago aplikaziorik intent hau kudeatzeko: " + e.getMessage());
                }
            }
        });

        // KOKAPENA MAPS EN ZABALDU
        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log-ak, nondik goazen jakiteko dira. --> TAG: appInfoBtn
                Log.i("appInfoBtn","btnLocation botoia sakatu da.");

                String location = binding.etLocationUrl.getText().toString().trim();  // EditText-etik erabiltzaileak idatzitakoa lortu eta hutsuneak kendu

                if (location.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Kokapena idatzi mesedez", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Uri "geo:" eskemarekin sortu --> Maps aplikazioak ulertzen du
                // q= parametroarekin bilaketa egiten du
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));

                // Intent inplizitua sortu --> ACTION_VIEW eta Uri
                // Ahal bada, Maps aplikazioari zuzenean bidali
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                try {
                    startActivity(mapIntent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Ez dago aplikaziorik kokapena irekitzeko", Toast.LENGTH_SHORT).show();
                    Log.e("infoBtn","Errorea: " + e.getMessage());
                }
            }
        });

        // MEZUAK BIDALI/PARTEKATU
        binding.btnShareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log-ak, nondik goazen jakiteko dira. --> TAG: appInfoBtn
                Log.i("appInfoBtn","btnShareText botoia sakatu da.");

                String msgToSend = binding.etShareText.getText().toString();  // EditText-etik erabiltzaileak idatzitakoa lortu

                if (msgToSend.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bidali nahi duzun mezua idatzi mesedez", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, msgToSend);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);

                try {
                    startActivity(shareIntent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Ez dago mesurik bidaltzeko", Toast.LENGTH_SHORT).show();
                    Log.e("infoBtn","Errorea: " + e.getMessage());
                }
            }
        });

    }


}
package eus.ainhizearrese.implicitactivityv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

import eus.ainhizearrese.implicitactivityv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // WEB ORRIAK ZALATU
        binding.btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log-ak, nondik goazen jakiteko dira. --> TAG: appInfoBtn

                Log.i("appInfoBtn","btnWebSite botoia sakatu da.");

                String url = binding.etWeb.getText().toString().trim();  // EditText-etik erabiltzaileak idatzitakoa lortu eta hutsuneak kendu

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

        // KAmERA ATALA
        // ActivityResultLauncher-eko callback-a
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Eliminar foto anterior (si existe)
                        int index = binding.containerLayout.indexOfChild(binding.divider7) + 1;
                        if (binding.containerLayout.getChildCount() > index) {
                            binding.containerLayout.removeViewAt(index);
                        }

                        // Crear ImageView dinámico
                        ImageView imageView = new ImageView(this);
                        imageView.setImageURI(photoUri);

                        // Ajustes de tamaño y márgenes
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                250,
                                250

                        );
                        params.setMargins(0, 20, 0, 20);
                        imageView.setLayoutParams(params);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                        // Insertar ImageView justo debajo del divisor
                        binding.containerLayout.addView(imageView, index);

                        Log.i("appInfoBtn", "Argazkia gehitu da LinearLayout-en");
                    }
                });

        binding.btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log-ak, nondik goazen jakiteko dira. --> TAG: appInfoBtn
                Log.i("appInfoBtn","btnPhoto botoia sakatu da.");
                try {
                    // Crear archivo temporal para la foto
                    File photoFile = new File(getExternalFilesDir("Pictures"),
                            "foto_" + System.currentTimeMillis() + ".jpg");
                    if (!photoFile.exists()) photoFile.createNewFile();

                    // Crear Uri con FileProvider
                    photoUri = FileProvider.getUriForFile(
                            MainActivity.this,
                            getPackageName() + ".fileprovider",
                            photoFile
                    );

                    // Intent de la cámara con EXTRA_OUTPUT
                    Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
                    cameraLauncher.launch(takePictureIntent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}
        );
    }
}
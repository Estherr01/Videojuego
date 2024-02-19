package com.example.permisos_peligrosos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int estadoPermiso= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
                if (estadoPermiso==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Permiso concedido", Toast.LENGTH_SHORT).show();
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},50);
                }

            }
        });
    }
}
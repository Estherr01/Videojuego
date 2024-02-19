package com.example.abrircorreo9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button enviar=findViewById(R.id.enviar);
        EditText mail=findViewById(R.id.email);
        EditText asunto=findViewById(R.id.asunto);
        EditText contenido=findViewById(R.id.contenido);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mandarmail = new Intent(Intent.ACTION_SEND);
                mandarmail.setData(Uri.parse("mailto"));
                String para[]={mail.getText().toString()};
                mandarmail.putExtra(Intent.EXTRA_EMAIL,para); //destinatario, si quieres mandarselo a mas, pones una coma y lo mandas
                mandarmail.putExtra(Intent.EXTRA_SUBJECT,asunto.getText().toString()); //asunto
                mandarmail.putExtra(Intent.EXTRA_TEXT,contenido.getText().toString()); //contenido del mail
                mandarmail.setType("message/rfc822");



                startActivity(Intent.createChooser(mandarmail,"Elegir aplicacion"));
                Log.d("MainActivity", "Email: " + mail.getText().toString());
                Log.d("MainActivity", "Asunto: " + asunto.getText().toString());
                Log.d("MainActivity", "Contenido: " + contenido.getText().toString());
                //luego pongo un toast





            }
        });




    }
}
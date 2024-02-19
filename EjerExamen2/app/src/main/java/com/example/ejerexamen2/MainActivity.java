package com.example.ejerexamen2;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton botonImagen = findViewById(R.id.enviar);
        EditText mail = findViewById(R.id.email);
        Spinner spinner = findViewById(R.id.spinner);
        EditText contenido = findViewById(R.id.contenido);

        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Resto del código para enviar el correo
                Intent mandarmail = new Intent(Intent.ACTION_SEND);
                mandarmail.setData(Uri.parse("mailto"));
                String para[] = {mail.getText().toString()};
                mandarmail.putExtra(Intent.EXTRA_EMAIL, para);

                // Obtén el asunto seleccionado del Spinner
                String asuntoSeleccionado = spinner.getSelectedItem().toString();
                mandarmail.putExtra(Intent.EXTRA_SUBJECT, asuntoSeleccionado);

                mandarmail.putExtra(Intent.EXTRA_TEXT, contenido.getText().toString());
                mandarmail.setType("message/rfc822");

                startActivity(Intent.createChooser(mandarmail, "Elegir aplicacion"));

                Log.d("MainActivity", "Email: " + mail.getText().toString());
                Log.d("MainActivity", "Asunto: " + asuntoSeleccionado);
                Log.d("MainActivity", "Contenido: " + contenido.getText().toString());

            }
        });
    }
}

package com.example.appsppiner;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String>nombres= new ArrayList<String>();
        Spinner sp=(Spinner) findViewById(R.id.spinner);

        nombres.add("España");
        nombres.add("Venezuela");
        int imagenes []={R.drawable.espania,R.drawable.venezuela};

        miadaptador ma=new miadaptador(this,R.layout.disenioo,nombres,imagenes);
        sp.setAdapter(ma);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nombreactual=nombres.get(position);
                Toast.makeText(MainActivity.this, "Usted a seleccionado " + nombreactual, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

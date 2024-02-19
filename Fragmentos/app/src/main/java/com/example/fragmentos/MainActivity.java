package com.example.fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean fragmentVisible = false;
    private primerfragment primerFragment;
    private segundofragment segundoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFragmento(new primerfragment());
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFragmento(new segundofragment());
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarFragmentos();
            }
        });
    }

    private void mostrarFragmento(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true);

        if (fragment instanceof primerfragment) {
            if (primerFragment == null) {
                primerFragment = new primerfragment();
                fragmentTransaction.add(R.id.contenedor1, primerFragment);
            } else {
                fragmentTransaction.show(primerFragment);
            }
            if (segundoFragment != null) {
                fragmentTransaction.hide(segundoFragment);
            }
        } else if (fragment instanceof segundofragment) {
            if (segundoFragment == null) {
                segundoFragment = new segundofragment();
                fragmentTransaction.add(R.id.contenedor1, segundoFragment);
            } else {
                fragmentTransaction.show(segundoFragment);
            }
            if (primerFragment != null) {
                fragmentTransaction.hide(primerFragment);
            }
        }

        fragmentTransaction.commit();
        fragmentVisible = true;
    }

    private void ocultarFragmentos() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (primerFragment != null) {
            fragmentTransaction.hide(primerFragment);
        }
        if (segundoFragment != null) {
            fragmentTransaction.hide(segundoFragment);
        }

        fragmentTransaction.commit();
        fragmentVisible = false;
    }
}
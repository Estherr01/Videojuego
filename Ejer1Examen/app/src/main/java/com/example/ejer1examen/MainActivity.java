package com.example.ejer1examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numero1;
    private EditText numero2;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        resultado = findViewById(R.id.resultado);

        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("/");
            }
        });
    }

    private void realizarOperacion(String operador) {
        try {
            double num1 = Double.parseDouble(numero1.getText().toString());
            double num2 = Double.parseDouble(numero2.getText().toString());

            double resultadoOperacion = 0;

            switch (operador) {
                case "+":
                    resultadoOperacion = num1 + num2;
                    break;
                case "-":
                    resultadoOperacion = num1 - num2;
                    break;
                case "*":
                    resultadoOperacion = num1 * num2;
                    break;
                case "/":
                    resultadoOperacion = num1 / num2;
                    break;
            }

            resultado.setText("Resultado: " + resultadoOperacion);
            resultado.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
            resultado.setText("Error: Ingrese números válidos");
            resultado.setVisibility(View.VISIBLE);
        } catch (ArithmeticException e) {
            resultado.setText("Error: No se puede dividir por cero");
            resultado.setVisibility(View.VISIBLE);
        }
    }
}
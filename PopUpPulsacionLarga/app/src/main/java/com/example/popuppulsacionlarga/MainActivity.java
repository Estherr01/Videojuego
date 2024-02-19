package com.example.popuppulsacionlarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inflar el diseño del PopupWindow
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);

        // Crear el PopupWindow
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // Crear el PopupWindow
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // Configurar los elementos dentro del PopupWindow
        Button closeButton = popupView.findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar el PopupWindow cuando se hace clic en el botón dentro de él
                popupWindow.dismiss();
            }
        });
        Button btnMostrarPopup = findViewById(R.id.btnMostrarPopup);
        btnMostrarPopup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupWindow(v);
                return false;
            }
        });

    }

    private void showPopupWindow(View anchorView) {
        if (popupWindow != null) {
            // Configurar la posición del PopupWindow y mostrarlo
            popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 0);
        }

    }
}

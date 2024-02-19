package com.example.proyectobotonesmenuconjunto;
// Paquete: com.example.tuapp.ui
import android.app.Activity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewSetup {

    public static void setupWithNavController(Activity activity, DrawerLayout drawerLayout) {
        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController((NavigationView) activity.findViewById(R.id.nav_view), navController);
    }
}


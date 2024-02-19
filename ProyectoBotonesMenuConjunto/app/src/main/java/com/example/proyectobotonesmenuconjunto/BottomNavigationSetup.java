package com.example.proyectobotonesmenuconjunto;

// Paquete: com.example.tuapp.ui
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationSetup {

    public static void setupWithNavController(MainActivity activity) {
        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = activity.findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}


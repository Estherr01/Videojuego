package com.example.proyectobotonesmenuconjunto;

// Paquete: com.example.tuapp.ui
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

public class NavigationDrawerHandler {

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public static boolean isDrawerOpen(DrawerLayout drawerLayout) {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }
}


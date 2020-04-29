package com.example.childrenslibrayapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.childrenslibrayapp.mockdata.ObjectGenerator;
import com.example.childrenslibrayapp.objects.Book;
import com.example.childrenslibrayapp.objects.User;
import com.example.childrenslibrayapp.structures.SinglyLinkedList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent busquedalibro = new Intent(view.getContext(), Busqueda.class);
                startActivity(busquedalibro);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        SinglyLinkedList <Book> libros = new SinglyLinkedList<Book>();
        SinglyLinkedList <User> users = new SinglyLinkedList<User>();
        GenerateData(libros, users);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void InfoLIbro(View view) {
        Intent infolibro = new Intent(this, Libro.class);
        startActivity(infolibro);
    }

    public void GenerateData (SinglyLinkedList <Book> libros, SinglyLinkedList <User> users) {
        ObjectGenerator gen = new ObjectGenerator();

        gen.generateBookList(10000, libros);
        gen.generateUsers(10000, users);

        Toast.makeText(getApplicationContext(), "Datos generados", Toast.LENGTH_SHORT).show();
    }

}

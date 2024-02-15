package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            }
    public void pesanan(View v) {
        Intent i = new Intent(this, pesanan.class);
        startActivity(i);
    }
       public void histori(View v) {
        Intent i = new Intent(this, History.class);
        startActivity(i);
    }
    public void akun(View v) {
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }
    public void menu(View v) {
        Intent i = new Intent(this, Menuhal.class);
        startActivity(i);
    }
    public void fasilitas(View v) {
        Intent i = new Intent(this, Fasilitas.class);
        startActivity(i);
    }
}
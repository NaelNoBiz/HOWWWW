package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FL1, new LInFrag());
        ft.commit();
    }

    public void LoginFrag(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FL1, new LInFrag());
        ft.commit();
    }

    public void SignupFrag(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FL1, new Signup());
        ft.commit();
    }
}
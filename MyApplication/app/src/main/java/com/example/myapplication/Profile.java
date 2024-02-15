package com.example.myapplication;



import static com.example.myapplication.ui.main.data.SessionManager.EMAIL;
import static com.example.myapplication.ui.main.data.SessionManager.ID;
import static com.example.myapplication.ui.main.data.SessionManager.NAME;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import com.example.myapplication.ui.main.data.SessionManager;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    private TextView name, email,id;
    private Button btn_logout;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        id = findViewById(R.id.id_user);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_logout = findViewById(R.id.btn_logout);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(ID);
        String mName = user.get(NAME);
        String mEmail = user.get(EMAIL);

        id.setText(mId);
        name.setText(mName);
        email.setText(mEmail);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
    }
}

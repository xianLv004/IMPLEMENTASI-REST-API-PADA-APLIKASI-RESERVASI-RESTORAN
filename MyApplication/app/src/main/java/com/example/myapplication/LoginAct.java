package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.main.data.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginAct extends AppCompatActivity {


    private EditText email, password;
    private Button btn_login;
    private TextView link_regist;
    private ProgressBar loading;
    public static final String login_url = "https://setik.000webhostapp.com/test/login1.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        link_regist = findViewById(R.id.link_regist);

        btn_login.setOnClickListener(v -> {
            String mEmail = email.getText().toString().trim();
            String mPass = password.getText().toString().trim();

            if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                Login(mEmail, mPass);
            } else {
                email.setError("Please insert email");
                password.setError("Please insert password");
            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAct.this, Register.class));
            }
        });

    }

    private void Login(final String email, final String password) {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");

                        if (success.equals("1")) {

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                String name = object.getString("name").trim();
                                String email1 = object.getString("email").trim();
                                String id = object.getString("id").trim();

                                sessionManager.createSession(name, email1,id);

                                Intent intent = new Intent(LoginAct.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                                loading.setVisibility(View.GONE);
                                 }
                        }else {
                            Toast.makeText(LoginAct.this, "Password atau Email Salah ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();

                        Toast.makeText(LoginAct.this, "Password atau Email Salah ", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginAct.this, "Error " +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
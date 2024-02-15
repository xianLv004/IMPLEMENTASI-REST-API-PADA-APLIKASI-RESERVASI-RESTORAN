package com.example.myapplication;

import static android.content.ContentValues.TAG;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.ui.main.data.Rsvadapter;
import com.example.myapplication.ui.main.data.SessionManager;
import com.example.myapplication.ui.main.data.data;
import com.example.myapplication.ui.main.data.pesanAdapter;
import com.example.myapplication.ui.main.data.pesandata;

public class History extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String URLSELECTHIS = "https://setik.000webhostapp.com/test/testing.php";

    ListView list;
    SwipeRefreshLayout swipe;
    List<pesandata> hisList = new ArrayList<pesandata>();
    pesanAdapter adapter;
    String getId;
    TextView nama,tanggal,jam,status;
    ProgressBar progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        nama = findViewById(R.id.id_user);
        progressDialog = new ProgressBar(History.this);
        tanggal = findViewById(R.id.tanggal_reservasi);
        jam = findViewById(R.id.jam_reservasi);
        status = findViewById(R.id.status);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);

        swipe.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           hisList.clear();
                           callVolley();
                       }
                   }
        );
    }
    public void onRefresh() {
        hisList.clear();
        callVolley();

    }

    private void callVolley () {
        hisList.clear();
        // membuat request JSON
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLSELECTHIS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("id_user").trim();
                                    String strtgl = object.getString("tanggal_reservasi").trim();
                                    String strjam = object.getString("jam_reservasi").trim();
                                    String strstatus = object.getString("status").trim();

                                    nama.setText("Id Pelangan   :" + strName);
                                    tanggal.setText("Tanggal Reservasi   :" + strtgl);
                                    jam.setText("Jam Reservasi  :" + strjam);
                                    status.setText("Status Pemesanan    :" + strstatus);

                                }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(History.this, " " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(History.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void trans(View view) {
        Intent intent = new Intent(History.this, transaksi.class);
        startActivity(intent);
    }

}


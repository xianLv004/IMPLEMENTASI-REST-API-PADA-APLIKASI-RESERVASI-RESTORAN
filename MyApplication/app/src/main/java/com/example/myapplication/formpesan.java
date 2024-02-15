package com.example.myapplication;


import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ui.main.data.InputFilterMinMax;
import com.example.myapplication.ui.main.data.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class formpesan extends AppCompatActivity {
    public static final String insert_url = "https://setik.000webhostapp.com/test/insert2.php";
    private static String URL_READ = "https://setik.000webhostapp.com/test/read_detail.php";
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String tanggal_reservasi, nama_lengkap, jumlah, jam_reservasi, no_hp;
    private Button btnpesan;
    private EditText inputNama, inputTelepon, tv_dateresult, inputwaktu, tv_jmlh;
    String getId;
    ProgressBar progressDialog;
    private TextView responseTV;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formpesan);
        btnpesan = findViewById(R.id.btnpesan);
        inputNama = findViewById(R.id.inputNama);
        responseTV = findViewById(R.id.responseTV);
        tv_jmlh = findViewById(R.id.tv_jmlh);
        inputwaktu = findViewById(R.id.inputwaktu);
        inputTelepon = findViewById(R.id.inputTelepon);
        progressDialog = new ProgressBar(formpesan.this);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tv_dateresult = findViewById(R.id.tv_dateresult);
        tv_dateresult.setOnClickListener(view -> showDateDialog());
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        
    }

    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();

                                    inputNama.setText(strName);

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(formpesan.this, "Error Reading Detail " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(formpesan.this, "Error Reading Detail " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }


    private void showDateDialog() {


        Calendar newCalendar = Calendar.getInstance();


        datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {


            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);


            tv_dateresult.setText(dateFormatter.format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }

    public void save(View view) {
        nama_lengkap = inputNama.getText().toString().trim();
        no_hp = inputTelepon.getText().toString().trim();
        tanggal_reservasi = tv_dateresult.getText().toString().trim();
        jam_reservasi = inputwaktu.getText().toString().trim();
        jumlah = tv_jmlh.getText().toString().trim();

        final String id = getId;
        String a = tv_jmlh.getText().toString();
        float a1 = Float.parseFloat(a);
        if  (a1 > 100 || a1 < 15)
        {
            tv_jmlh.setError("Minimal 15 Orang");
            tv_jmlh.setText("");
            tv_jmlh.requestFocus();
        }
        else if (!nama_lengkap.equals("") && !tanggal_reservasi.equals("") && !jam_reservasi.equals("") && !no_hp.equals("") && !jumlah.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                                        if (response.equals("success")) {
                        showDialog();
                    } else if (response.equals("failure")) {
                        responseTV.setText("Pesanan Gagal Dilakukan Silahkan Pilih Tanggal Lain");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("id_user", id);
                    data.put("nama_lengkap", nama_lengkap);
                    data.put("tanggal_reservasi", tanggal_reservasi);
                    data.put("jam_reservasi", jam_reservasi);
                    data.put("no_hp", no_hp);
                    data.put("jumlah", jumlah);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

        else {

            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Klik Ya Untuk Melanjutkan");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Pesanan Berhasil Dilakukan, Silahkan Lakukan Pembayaran!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        Intent myIntent = new Intent(formpesan.this, History.class);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

}
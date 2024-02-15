package com.example.myapplication.ui.main.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class pesanAdapter extends BaseAdapter {
    Activity activity;
    List<pesandata> items;
    private LayoutInflater inflater;


    public pesanAdapter(Activity activity, List<pesandata> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.listhistory, null);

        TextView user = (TextView) convertView.findViewById(R.id.id_user);
        TextView id = (TextView) convertView.findViewById(R.id.tanggal_reservasi);
        TextView nim = (TextView) convertView.findViewById(R.id.jam_reservasi);
        TextView nama = (TextView) convertView.findViewById(R.id.status);


        pesandata pesandata = items.get(position);
        user.setText("Nama Pemesan  :  " +pesandata.getId_user());
        id.setText("Tanggal Reservasi  :  " +pesandata.getTanggal_reservasi());
        nim.setText("Jam Reservasi     :  "  + pesandata.getJam_reservasi());
        nama.setText("Status Reservasi  : " +pesandata.getStatus());

        return convertView;

    }

}
package com.example.myapplication.ui.main.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;


public class QrAdapter extends RecyclerView.Adapter<QrAdapter.QrViewHolder> {

    private Context mCtx;
    private List<qrData> qrList;

    public QrAdapter(Context mCtx, List<qrData> qrList) {
        this.mCtx = mCtx;
        this.qrList = qrList;
    }

    @Override
    public QrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.qr_code, null);
        return new QrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QrViewHolder holder, int position) {
        qrData menu = (qrData) qrList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load("https://setik.000webhostapp.com/images/" + menu.getGambar())
                .into(holder.imageView);

        holder.code.setText(menu.getCode());
    }

    @Override
    public int getItemCount() {
        return qrList.size();
    }

    class QrViewHolder extends RecyclerView.ViewHolder {

        TextView code;
        ImageView imageView;

        public QrViewHolder(View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.code);
            imageView = itemView.findViewById(R.id.qrcode);
        }
    }
}

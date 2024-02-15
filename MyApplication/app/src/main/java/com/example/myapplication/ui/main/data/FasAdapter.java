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

public class FasAdapter extends RecyclerView.Adapter<FasAdapter.FasViewHolder> {

    private Context mCtx;
    private List< Fasilitasdat> fasList;

    public FasAdapter(Context mCtx, List< Fasilitasdat> fasList) {
        this.mCtx = mCtx;
        this.fasList = fasList;
    }

    @Override
    public FasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fas_list, null);
        return new FasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FasViewHolder holder, int position) {
         Fasilitasdat fas = fasList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load("https://setik.000webhostapp.com//src/img/" + fas.getGambar())
                .into(holder.imageView);

        holder.nama_fas.setText(fas.getNama_fas());
        holder.ket_fas.setText(fas.getKet_fas());
    }

    @Override
    public int getItemCount() {
        return fasList.size();
    }

    class FasViewHolder extends RecyclerView.ViewHolder {

        TextView nama_fas, ket_fas;
        ImageView imageView;

        public FasViewHolder(View itemView) {
            super(itemView);

            nama_fas = itemView.findViewById(R.id.nama_fas);
            ket_fas = itemView.findViewById(R.id.ket_fas);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}



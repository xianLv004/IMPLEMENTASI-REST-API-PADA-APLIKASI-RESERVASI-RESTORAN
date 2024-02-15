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

/**
 * Created by Belal on 10/18/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ProductViewHolder> {


    private Context mCtx;
    private List menuList;

    public MenuAdapter(Context mCtx, List menuList) {
        this.mCtx = mCtx;
        this.menuList = menuList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Menu menu = (Menu) menuList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load("https://setik.000webhostapp.com/src/img/" + menu.getGambar())
                .into(holder.imageView);

        holder.nama_menu.setText(menu.getNama());
        holder.status_menu.setText(menu.getStatus());
        holder.kode_menu.setText(menu.getKode_menu());
        holder.harga.setText(String.valueOf(menu.getHarga()));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nama_menu, status_menu, kode_menu, harga;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            nama_menu = itemView.findViewById(R.id.nama_menu);
            status_menu = itemView.findViewById(R.id.status_menu);
            kode_menu = itemView.findViewById(R.id.kode_menu);
            harga = itemView.findViewById(R.id.harga);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

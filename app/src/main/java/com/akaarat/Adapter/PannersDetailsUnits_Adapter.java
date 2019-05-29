package com.akaarat.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akaarat.Model.Banner_details;
import com.akaarat.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class PannersDetailsUnits_Adapter extends RecyclerView.Adapter<PannersDetailsUnits_Adapter.MyViewHolder>{

    private List<Banner_details> filteredList=new ArrayList<>();
    SharedPreferences.Editor share;

    public static String TotalPrice;
    View itemView;
    Context con;
    String prrice;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView Img_Panner;
        public MyViewHolder(View view) {
            super(view);
            Img_Panner=view.findViewById(R.id.Img_Panner);
        }


    }

    public PannersDetailsUnits_Adapter(List<Banner_details> list,Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public PannersDetailsUnits_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner, parent, false);
        return new PannersDetailsUnits_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PannersDetailsUnits_Adapter.MyViewHolder holder, final int position) {


        String i = filteredList.get(position).getFileName();
        if(i!=null) {
            Uri u = Uri.parse(i);
//        holder.progressBar.setVisibility(View.VISIBLE);
            Glide.with(con)
                    .load("http://gmtccco-001-site1.etempurl.com/wwwroot/upload/unit/" + u)
                    .apply(new RequestOptions().override(500, 500).placeholderOf(R.drawable.desfultakaar).error(R.drawable.desfultakaar))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.Img_Panner);
        }

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}


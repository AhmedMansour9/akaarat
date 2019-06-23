package com.akaarat.Tenant_Account.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akaarat.Model.Units_Detail;
import com.akaarat.R;
import com.akaarat.Tenant_Account.Model.Reservation_Details;
import com.akaarat.Tenant_Account.Model.ReservestionsDetail;
import com.akaarat.View.ListUnitDetails_View;
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

public class Reservation_Adapter extends RecyclerView.Adapter<Reservation_Adapter.MyViewHolder>{

    private List<ReservestionsDetail> filteredList=new ArrayList<>();
    View itemView;
    Context con;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Txt_customer_name,Txt_description,T_Date,Title,T_RentTime,T_title;

        public MyViewHolder(View view) {
            super(view);
            Txt_customer_name=view.findViewById(R.id.Txt_customer_name);
            Txt_description=view.findViewById(R.id.Txt_description);
            T_Date=view.findViewById(R.id.T_date);
            T_RentTime=view.findViewById(R.id.T_RentTime);
            T_title=view.findViewById(R.id.T_title);

        }
    }

    public Reservation_Adapter(List<ReservestionsDetail> list, Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Reservation_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reservation, parent, false);
        return new Reservation_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Reservation_Adapter.MyViewHolder holder, final int position) {
        holder.Txt_customer_name.setText(filteredList.get(position).getCustomername());
        holder.Txt_description.setText(filteredList.get(position).getCustomername());
        holder.T_title.setText(filteredList.get(position).getUnitname());
        holder.T_Date.setText(filteredList.get(position).getRentDate());
        holder.T_RentTime.setText(filteredList.get(position).getRenttime());

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


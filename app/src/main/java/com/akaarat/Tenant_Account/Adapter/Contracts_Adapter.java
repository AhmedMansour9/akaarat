package com.akaarat.Tenant_Account.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akaarat.R;
import com.akaarat.Tenant_Account.Model.ContractsDetail;
import com.akaarat.Tenant_Account.Model.Contracts_Details;
import com.akaarat.Tenant_Account.Model.ReservestionsDetail;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Contracts_Adapter extends RecyclerView.Adapter<Contracts_Adapter.MyViewHolder>{

    private List<ContractsDetail> filteredList=new ArrayList<>();
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

    public Contracts_Adapter(List<ContractsDetail> list, Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Contracts_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reservation, parent, false);
        return new Contracts_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Contracts_Adapter.MyViewHolder holder, final int position) {
        holder.Txt_customer_name.setText(filteredList.get(position).getCustomername());
        holder.Txt_description.setText(filteredList.get(position).getCustomername());
        holder.T_title.setText(filteredList.get(position).getCustomername());
        holder.T_Date.setText(filteredList.get(position).getDate());
        holder.T_RentTime.setText(filteredList.get(position).getAmount()+" "+con.getResources().getString(R.string.sar));

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


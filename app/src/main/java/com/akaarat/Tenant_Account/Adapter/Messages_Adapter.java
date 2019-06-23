package com.akaarat.Tenant_Account.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akaarat.R;
import com.akaarat.Tenant_Account.Model.Messages;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Messages_Adapter extends RecyclerView.Adapter<Messages_Adapter.MyViewHolder>{

    public static List<Messages> filteredList=new ArrayList<>();
    View itemView;
    Context con;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView T_Title,T_LastMessage,T_Time,T_Fromuser;
        public MyViewHolder(View view) {
            super(view);
            T_Time=view.findViewById(R.id.T_Time);
            T_LastMessage=view.findViewById(R.id.T_LastMessage);
            T_Title=view.findViewById(R.id.T_Title);
            T_Fromuser=view.findViewById(R.id.T_Fromuser);
        }
    }

    public Messages_Adapter(List<Messages> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    //    public void setClickListener(Details_Service itemClickListener) {
//        this.details_service = itemClickListener;
//    }
    @Override
    public Messages_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_messages, parent, false);
        return new Messages_Adapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final Messages_Adapter.MyViewHolder holder, final int position) {
        holder.T_Time.setText(filteredList.get(position).getCreatedAt());
        holder.T_LastMessage.setText(filteredList.get(position).getMessageBody());
        holder.T_Title.setText(filteredList.get(position).getSubject());
        holder.T_Fromuser.setText(filteredList.get(position).getFromUser());





    }

    @Override
    public int getItemCount() {
        return filteredList.size()  ;
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

package com.akaarat.Adapter;

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

import com.akaarat.Model.Dynamic_Attributes;
import com.akaarat.Model.Units_Detail;
import com.akaarat.R;
import com.akaarat.View.ListUnitDetails_View;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class Dynamic_Attributes_ِAdapter extends RecyclerView.Adapter<Dynamic_Attributes_ِAdapter.MyViewHolder>{

    private List<Dynamic_Attributes> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    ListUnitDetails_View listUnitDetails_view;
    List<Units_Detail> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name,Value;

        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.Name);
            Value=view.findViewById(R.id.Value);
        }
    }

    public Dynamic_Attributes_ِAdapter(List<Dynamic_Attributes> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public Dynamic_Attributes_ِAdapter(List<Dynamic_Attributes> list){
        this.filteredList=list;

    }
    public void setClickListener(ListUnitDetails_View listUnitDetails_view) {
        this.listUnitDetails_view = listUnitDetails_view;

    }

    @Override
    public Dynamic_Attributes_ِAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_dynamicattributes, parent, false);
        return new Dynamic_Attributes_ِAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Dynamic_Attributes_ِAdapter.MyViewHolder holder, final int position) {

      holder.Name.setText(filteredList.get(position).getLabelname());
      holder.Value.setText(filteredList.get(position).getInputvalue());

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


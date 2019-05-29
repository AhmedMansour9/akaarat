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

import com.akaarat.Model.Units_Detail;
import com.akaarat.R;
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

public class Units_Adapter  extends RecyclerView.Adapter<Units_Adapter.MyViewHolder>{

    private List<Units_Detail> filteredList=new ArrayList<>();
    View itemView;
    Context con;
//    Details_Sparts details_sparts;
//    phone_view phoneView;
    String Price;
    ListUnitDetails_View listUnitDetails_view;
    List<Units_Detail> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Address,Price,Distance_Area,T_Date,Title,T_Type,Text_RegularPrice;
        private Button Callnow,Details;
        private ImageView Image_Unit,call;
        private ProgressBar ProgrossSpare;
        private ImageView person_image,Starone,Startwo,StarThree,StarFour,StarFive;
        RelativeLayout relaa;

        public MyViewHolder(View view) {
            super(view);
            Address=view.findViewById(R.id.Address);
            Price=view.findViewById(R.id.Price);
            Distance_Area=view.findViewById(R.id.Distance_Area);
            T_Date=view.findViewById(R.id.T_Date);
            Title=view.findViewById(R.id.Title);
            ProgrossSpare=view.findViewById(R.id.progross);
            Image_Unit=view.findViewById(R.id.Image_Unit);
            T_Type=view.findViewById(R.id.T_Type);
        }
    }

    public Units_Adapter(List<Units_Detail> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public Units_Adapter(List<Units_Detail> list){
        this.filteredList=list;

    }
    public void setClickListener(ListUnitDetails_View listUnitDetails_view) {
        this.listUnitDetails_view = listUnitDetails_view;

    }

    @Override
    public Units_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_unit, parent, false);
        return new Units_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Units_Adapter.MyViewHolder holder, final int position) {

        if (filteredList.get(position).getPurposetype() == 1) {
            holder.T_Type.setText(con.getResources().getString(R.string.forrent));
            Price=String.valueOf(filteredList.get(position).getAnnualrent());
            holder.Price.setText(String.valueOf(filteredList.get(position).getAnnualrent())+" SAR");
        } else  if (filteredList.get(position).getPurposetype() == 2) {
            if(filteredList.get(position).getEnabledivision()!=null){
                holder.T_Type.setText(con.getResources().getString(R.string.forsell));
                Price=String.valueOf(filteredList.get(position).getSellingSharePrice());
                holder.Price.setText(String.valueOf(filteredList.get(position).getSellingSharePrice())+" SAR   "+con.getResources().getString(R.string.sellingshreprice));
            }else {
                holder.T_Type.setText(con.getResources().getString(R.string.forsell));
                Price=String.valueOf(filteredList.get(position).getSellingprice());
                holder.Price.setText(String.valueOf(filteredList.get(position).getSellingprice())+" SAR");

            }
        }
        holder.Address.setText(filteredList.get(position).getLocationdescription());
        holder.Title.setText(filteredList.get(position).getUnitNamear());
        holder.T_Date.setText(filteredList.get(position).getDate());
        holder.Distance_Area.setText(filteredList.get(position).getAreasize());
        String i = filteredList.get(position).getDefaultimg();

        if(i!=null) {
            Uri u = Uri.parse(i);
//            holder.ProgrossSpare.setVisibility(View.VISIBLE);
            Glide.with(con)
                    .load("http://gmtccco-001-site1.etempurl.com/wwwroot/upload/unit/"+ u)
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
                    .into(holder.Image_Unit);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filteredList.get(position).getPurposetype() == 1) {
                    Price=String.valueOf(filteredList.get(position).getAnnualrent());
                } else  if (filteredList.get(position).getPurposetype() == 2) {
                    Price=String.valueOf(filteredList.get(position).getSellingprice());
                }
                Units_Detail units_detail=new Units_Detail();
                units_detail.setId(filteredList.get(position).getId());
                units_detail.setSellingprice(Price);
                units_detail.setUnitdescription(filteredList.get(position).getUnitdescription());
                units_detail.setUnittypeid(filteredList.get(position).getUnittypeid());
                units_detail.setLocationdescription(filteredList.get(position).getLocationdescription());
                units_detail.setUnitNamear(filteredList.get(position).getUnitNamear());
                units_detail.setDate(filteredList.get(position).getDate());
                units_detail.setLastbidprice(filteredList.get(position).getLastbidprice());
                units_detail.setOfficeid(filteredList.get(position).getOfficeid());
                units_detail.setAreasize(filteredList.get(position).getAreasize());
                units_detail.setEnablebid(filteredList.get(position).getEnablebid());
                units_detail.setEnabledivision(filteredList.get(position).getEnabledivision() );
                units_detail.setNumberOfshare(filteredList.get(position).getNumberOfshare());
                units_detail.setShareAria(filteredList.get(position).getShareAria());
                units_detail.setCountOfSoldShare(filteredList.get(position).getCountOfSoldShare());
                listUnitDetails_view.List(units_detail);
            }
        });
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


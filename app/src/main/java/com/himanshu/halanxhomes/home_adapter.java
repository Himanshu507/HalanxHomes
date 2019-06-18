package com.himanshu.halanxhomes;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.himanshu.halanxhomes.model.Result;

import java.util.List;

public class home_adapter extends RecyclerView.Adapter<home_adapter.Home_holder>{

    private Context context;
    private List<Result> houses;

    public home_adapter(Context context, List<Result> houses) {
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public Home_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout,viewGroup,false);
        return new Home_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_holder home_holder, int i) {

        Result house = houses.get(i);
        home_holder.Bhk.setText(house.getAvailableRoomCount().toString());
        home_holder.accomodation_type.setText(house.getAccomodationAllowedStr());
        home_holder.location.setText(house.getAddress().getStreetAddress());
        home_holder.max_rent.setText(house.getSecurityDepositFrom().toString());
        home_holder.min_rent.setText(house.getRentFrom().toString());
        home_holder.bed.setText(house.getAvailableBedCount().toString());

        String img_link = house.getCoverPicUrl();

        Glide.with(context).load(img_link).into(home_holder.home);

    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class Home_holder extends RecyclerView.ViewHolder{

        ImageView home;
        TextView  Bhk, location, accomodation_type, bed, min_rent, max_rent;


        public Home_holder(@NonNull View itemView) {
            super(itemView);
            home = itemView.findViewById(R.id.home_image);
            Bhk = itemView.findViewById(R.id.Bhk);
            location = itemView.findViewById(R.id.location);
            accomodation_type = itemView.findViewById(R.id.boysgirlsfamily);
            bed = itemView.findViewById(R.id.Bed_Type);
            min_rent = itemView.findViewById(R.id.min_rent);
            max_rent = itemView.findViewById(R.id.max_rent);
        }
    }
}

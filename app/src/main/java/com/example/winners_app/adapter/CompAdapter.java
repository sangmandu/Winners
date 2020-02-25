package com.example.winners_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.R;
import com.example.winners_app.TabActivity.CompActivity;
import com.example.winners_app.TabActivity.TabCompete;
import com.example.winners_app.models.Competition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by User on 2/12/2018.
 */

public class CompAdapter extends RecyclerView.Adapter<CompAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Competition> mComps = new ArrayList<>();

    public CompAdapter(Context context, ArrayList<Competition> Comps) {
        mContext = context;
        mComps = Comps;
    }

    @NonNull
    @Override
    public CompAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompAdapter.ViewHolder holder, final int position) {

        try{
            Log.d("CompAdapter", "onBindViewHolder: added!");
            SimpleDateFormat df = new SimpleDateFormat("MM/dd");
            holder.comp_date.setText(df.format(mComps.get(position).getDatetime().getTime()));
            holder.comp_name.setText(mComps.get(position).getName());
            holder.comp_image.setImageDrawable(mComps.get(position).getImage());

        }catch (NullPointerException e){
            Log.d("CompAdapter", "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }

    }

    @Override
    public int getItemCount() {
        return mComps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView comp_date;
        TextView comp_name;
        ImageView comp_image;
        Button comp_desc;
        ImageView iv_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.comp_date = itemView.findViewById(R.id.comp_date);
            this.comp_name = itemView.findViewById(R.id.comp_name);
            this.comp_image = itemView.findViewById(R.id.comp_image);
            this.comp_desc = itemView.findViewById(R.id.comp_desc);
            this.iv_delete = itemView.findViewById(R.id.iv_delete);


            comp_desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CompActivity.class);
                    intent.putExtra("clicked", getAdapterPosition());
                    mContext.startActivity(intent);
                }
            });

            if (TabCompete.comp_delete) {
                itemView.findViewById(R.id.comp_delete).setVisibility(View.VISIBLE);
                iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mComps.remove(getAdapterPosition());
                        if(mComps.size()==0){
                            TabCompete.comp_delete = false;
                        }
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }

}
package com.example.winners_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.PointRankItem;
import com.example.winners_app.R;

import java.util.ArrayList;

public class PointRankAdapter extends RecyclerView.Adapter<PointRankAdapter.ViewHolder> {
    private ArrayList<PointRankItem> mData = null;
    public PointRankAdapter(ArrayList<PointRankItem> list){
        mData = list;
    }

    @NonNull
    @Override
    public PointRankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.pointrank, parent, false);
        PointRankAdapter.ViewHolder vh = new PointRankAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PointRankAdapter.ViewHolder holder, int position) {

        PointRankItem item = mData.get(position);

        holder.icon.setImageDrawable(item.getIcon());
        holder.name.setText(item.getName());
        holder.point.setText(item.getPoint());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView name;
        TextView point;

        ViewHolder(View itemView){
            super(itemView);

            icon = itemView.findViewById(R.id.iv_face);
            name = itemView.findViewById(R.id.tv_name);
            point = itemView.findViewById(R.id.tv_point);


        }

    }
}

package com.example.winners_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.winners_app.R;
import com.example.winners_app.models.User;

import java.util.ArrayList;


/**
 * Created by User on 2/12/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<User> mUsers = new ArrayList<>();

    public UserAdapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comp_people_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, final int position) {

        try{
            Log.d("CompAdapter", "onBindViewHolder: added!");
            holder.profile_name.setText(mUsers.get(position).getName());

            Glide.with(mContext)
                    .asBitmap()
                    .load(mUsers.get(position).getImage())
                    .apply(new RequestOptions().circleCrop())
                    .into(holder.profile_image);

        }catch (NullPointerException e){
            Log.d("CompAdapter", "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView profile_name;
        ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile_name = itemView.findViewById(R.id.profile_name);
            this.profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

}
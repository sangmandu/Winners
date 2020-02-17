package com.example.winners_app.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.R;
import com.example.winners_app.fragments.FragmentCollapseCalender;
import com.example.winners_app.models.Event;

import java.util.ArrayList;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder> {

    private ArrayList<Event> mEvents = new ArrayList<>();

    public CalenderAdapter(ArrayList<Event> mEvents) {
        this.mEvents = mEvents;
    }

    @NonNull
    @Override
    public CalenderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderAdapter.ViewHolder holder, int position) {
        try{
            holder.eventTitle.setText(mEvents.get(position).getmTitle());

            holder.eventColor.setColorFilter(mEvents.get(position).getmColor());

            long diff = FragmentCollapseCalender.getDiff(mEvents.get(position).getmDate(), FragmentCollapseCalender.selectedDay);
            if (diff == 0)
                holder.daysLeft.setText("오늘");
            else
                holder.daysLeft.setText("D-" + diff);


        }catch (NullPointerException e){
            Log.e("CalenderAdapter", "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView eventColor;
        TextView eventTitle;
        TextView daysLeft;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.eventColor = itemView.findViewById(R.id.event_color);
            this.eventTitle = itemView.findViewById(R.id.event_title);
            this.daysLeft = itemView.findViewById(R.id.event_d_left);
        }
    }

}

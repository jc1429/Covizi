package com.example.covizi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<ThingsToKnow> mData;

    public RecyclerViewAdapter(Context mContext, List<ThingsToKnow> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.things_to_know_item_container,parent,false);
        ViewHolder vHolder = new ViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.cases.setImageResource(mData.get(position).getPhoto());
        holder.date.setText(mData.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView app_name;
        private TextView title;
        private TextView date;
        private ImageView app_icon;
        private ImageView cases;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            app_name = itemView.findViewById(R.id.app_name);
            title = itemView.findViewById(R.id.title_things);
            date = itemView.findViewById(R.id.post_date);
            app_icon = itemView.findViewById(R.id.img_covizi);
            cases = itemView.findViewById(R.id.cases);
        }
    }

}

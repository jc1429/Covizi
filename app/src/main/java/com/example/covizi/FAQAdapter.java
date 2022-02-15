package com.example.covizi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.faqVH> {

    List<FAQ> faqList;

    public FAQAdapter(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    public class faqVH extends RecyclerView.ViewHolder {

        TextView title,content;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public faqVH(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_q1);
            content = itemView.findViewById(R.id.content_q1);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FAQ faq = faqList.get(getAdapterPosition());
                    faq.setExpandable(!faq.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }

    @NonNull
    @Override
    public FAQAdapter.faqVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqitem ,parent,false);

        return new faqVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.faqVH holder, int position) {

        FAQ faq = faqList.get(position);
        holder.title.setText(faq.getTitle());
        holder.content.setText(faq.getContent());

        boolean isExpandable = faqList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE :View.GONE);

    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }
}

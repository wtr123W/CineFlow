package com.grassroots.cineflow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.sqlite.SingleItem;

import java.util.ArrayList;
import java.util.List;

public class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.ViewHolder> {

    private final List<SingleItem> list = new ArrayList<>();

    private OnItemAddClickListener onItemAddClickListener;

    @NonNull
    @Override
    public SingleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemAdapter.ViewHolder holder, int position) {
        SingleItem item = list.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvScore.setText(item.getScore());
        holder.tvContent1.setText(item.getContent1());
        holder.tvContent2.setText(item.getContent2());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.ivCover.setImageResource(item.getImageId());
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemAddClickListener != null) {
                    onItemAddClickListener.onAdd(item);
                }
            }
        });
    }

    public void setList(List<SingleItem> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemAddClickListener(OnItemAddClickListener onItemAddClickListener) {
        this.onItemAddClickListener = onItemAddClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemAddClickListener {
        void onAdd(SingleItem item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle, tvScore, tvContent1, tvContent2, tvPrice;

        private final ImageView ivCover, ivAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvContent1 = itemView.findViewById(R.id.tv_content1);
            tvContent2 = itemView.findViewById(R.id.tv_content2);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivCover = itemView.findViewById(R.id.iv_cover);
            ivAdd = itemView.findViewById(R.id.iv_add);
        }
    }
}

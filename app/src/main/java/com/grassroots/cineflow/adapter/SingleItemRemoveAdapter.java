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

public class SingleItemRemoveAdapter extends RecyclerView.Adapter<SingleItemRemoveAdapter.ViewHolder> {

    private final List<SingleItem> list = new ArrayList<>();

    private OnItemRemoveClickListener onItemRemoveClickListener;

    @NonNull
    @Override
    public SingleItemRemoveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_remove, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemRemoveAdapter.ViewHolder holder, int position) {
        SingleItem item = list.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvScore.setText(item.getScore());
        holder.tvContent1.setText(item.getContent1());
        holder.tvContent2.setText(item.getContent2());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.ivCover.setImageResource(item.getImageId());
        holder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                if (onItemRemoveClickListener != null) {
                    onItemRemoveClickListener.onRemove(item);
                }
            }
        });
    }

    public void setList(List<SingleItem> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemRemoveClickListener(OnItemRemoveClickListener onItemRemoveClickListener) {
        this.onItemRemoveClickListener = onItemRemoveClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemRemoveClickListener {
        void onRemove(SingleItem item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle, tvScore, tvContent1, tvContent2, tvPrice, tvRemove;

        private final ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvContent1 = itemView.findViewById(R.id.tv_content1);
            tvContent2 = itemView.findViewById(R.id.tv_content2);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvRemove = itemView.findViewById(R.id.tv_remove);
        }
    }
}

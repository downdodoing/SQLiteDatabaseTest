package com.example.mvp.sqlitedatabasetest.material_design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp.sqlitedatabasetest.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context context;
    private List<Fruit> mList;

    public FruitAdapter(List<Fruit> mList) {
        this.mList = mList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView fruitName;
        ImageView friutImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
            friutImage = (ImageView) itemView.findViewById(R.id.fruit_image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == context) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Fruit fruit = mList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.friutImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mList.get(position);
                FruitActivity.intentData(context, fruit.getName(), fruit.getImageId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

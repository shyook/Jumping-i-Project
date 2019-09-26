package com.jumpingi.arithmetic.ui.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> arrNameList;
    private ArrayList<Integer> arrImageList;

    public InnerRecyclerViewAdapter(ArrayList<String> nameList, ArrayList<Integer> imageList) {
        this.arrNameList = nameList;
        this.arrImageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_expand, parent, false);

        InnerRecyclerViewAdapter.ViewHolder vh = new InnerRecyclerViewAdapter.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(arrNameList.get(position));
        holder.image.setImageResource(arrImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sub_menu_item_tv);
            image = itemView.findViewById(R.id.sub_menu_item_iv);
        }
    }
}

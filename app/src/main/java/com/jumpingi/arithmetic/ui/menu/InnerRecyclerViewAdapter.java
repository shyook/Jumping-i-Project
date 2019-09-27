package com.jumpingi.arithmetic.ui.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> arrNameList;
    private ArrayList<Integer> arrImageList;
    private String mParentName;
    private IMainMenuClickListener mMenuClickListener;

    public InnerRecyclerViewAdapter(int position, String parentName, ArrayList nameList, ArrayList imageList, IMainMenuClickListener listener) {
        this.arrNameList = nameList;
        this.arrImageList = imageList;
        this.mParentName = parentName;
        this.mMenuClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_expand, parent, false);

        InnerRecyclerViewAdapter.ViewHolder vh = new InnerRecyclerViewAdapter.ViewHolder(v, mParentName);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(arrNameList.get(position));
        holder.image.setImageResource(arrImageList.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMenuClickListener != null) {
                    mMenuClickListener.onMenuClick(holder.parent, arrNameList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;
        private CardView cardView;
        private String parent;

        public ViewHolder(View itemView, String data) {
            super(itemView);
            name = itemView.findViewById(R.id.sub_menu_item_tv);
            image = itemView.findViewById(R.id.sub_menu_item_iv);
            cardView = itemView.findViewById(R.id.sub_menu_card_view);
            parent = data;
        }
    }
}

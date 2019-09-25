package com.jumpingi.arithmetic.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<String> image = new ArrayList<String>();
    private ArrayList<Integer> counter = new ArrayList<Integer>();
    private ArrayList<ArrayList> itemNameList = new ArrayList<ArrayList>();
    private Context context;

    public ExpandableRecyclerViewAdapter(Context context,
                                         ArrayList<String> nameList,
                                         ArrayList<ArrayList> itemNameList) {
        this.nameList = nameList;
        this.itemNameList = itemNameList;
        this.context = context;

        for (int i = 0; i < nameList.size(); i++) {
            counter.add(0);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_collapse, parent, false);

        ExpandableRecyclerViewAdapter.ViewHolder vh = new ExpandableRecyclerViewAdapter.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(nameList.get(position));
        InnerRecyclerViewAdapter itemInnerRecyclerView = new InnerRecyclerViewAdapter(itemNameList.get(position));
        holder.cardRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter.get(position) % 2 == 0) {
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    holder.cardRecyclerView.setVisibility(View.GONE);
                }
                counter.set(position, counter.get(position) + 1);
            }
        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description;
        private ImageButton dropBtn;
        private RecyclerView cardRecyclerView;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.main_menu_category_tv);
            description = itemView.findViewById(R.id.main_menu_category_description_tv);
            dropBtn = itemView.findViewById(R.id.main_menu_category_bt);
            cardRecyclerView = itemView.findViewById(R.id.main_menu_detail_sub_rv);
            cardView = itemView.findViewById(R.id.main_menu_card_view);
        }
    }
}

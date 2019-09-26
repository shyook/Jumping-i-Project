package com.jumpingi.arithmetic.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;

public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mMainMenuList;
    private ArrayList<Integer> mMainMenuImage;
    private ArrayList<String> mMainMenuDescriptionList;
    private ArrayList<Integer> counter = new ArrayList<>();
    private ArrayList<ArrayList> mItemNameList;
    private ArrayList<ArrayList> mItemSubImageList;
    private Context mContext;

    public ExpandableRecyclerViewAdapter(Context context,
                                         ArrayList<String> mainMenu,
                                         ArrayList<Integer> mainMenuImage,
                                         ArrayList<String> mainMenuDescription,
                                         ArrayList<ArrayList> itemNameList,
                                         ArrayList<ArrayList> itemSubImageList) {
        this.mMainMenuList = mainMenu;
        this.mMainMenuImage = mainMenuImage;
        this.mMainMenuDescriptionList = mainMenuDescription;
        this.mItemNameList = itemNameList;
        this.mItemSubImageList = itemSubImageList;
        this.mContext = context;

        for (int i = 0; i < mMainMenuList.size(); i++) {
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
        holder.name.setText(mMainMenuList.get(position));   // 메뉴 이름
        holder.mainMenuImage.setImageResource(mMainMenuImage.get(position));    // 메뉴 이미지
        holder.description.setText(mMainMenuDescriptionList.get(position)); // 메뉴 설명
        // 서브 메뉴 셋팅
        InnerRecyclerViewAdapter itemInnerRecyclerView = new InnerRecyclerViewAdapter(mItemNameList.get(position), mItemSubImageList.get(position));
        holder.cardRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter.get(position) % 2 == 0) {
                    holder.cardRecyclerView.setVisibility(View.VISIBLE);
                    holder.dropBtn.setImageResource(R.drawable.arrow_up);
                } else {
                    holder.cardRecyclerView.setVisibility(View.GONE);
                    holder.dropBtn.setImageResource(R.drawable.arrow_down);
                }
                counter.set(position, counter.get(position) + 1);
            }
        });
        holder.cardRecyclerView.setAdapter(itemInnerRecyclerView);

    }

    @Override
    public int getItemCount() {
        return mMainMenuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description;
        private ImageButton dropBtn;
        private RecyclerView cardRecyclerView;
        private CardView cardView;
        private ImageView mainMenuImage;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.main_menu_category_tv);
            description = itemView.findViewById(R.id.main_menu_category_description_tv);
            dropBtn = itemView.findViewById(R.id.main_menu_category_bt);
            cardRecyclerView = itemView.findViewById(R.id.main_menu_detail_sub_rv);
            cardView = itemView.findViewById(R.id.main_menu_card_view);
            mainMenuImage = itemView.findViewById(R.id.main_menu_iv);
        }
    }
}

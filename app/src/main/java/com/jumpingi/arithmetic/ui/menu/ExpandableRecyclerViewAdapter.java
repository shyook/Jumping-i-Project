package com.jumpingi.arithmetic.ui.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;

/**
 * 메인 메뉴를 디스플레이 하기 위한 Adapter.
 */
public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mMainMenuList;                    // 메인 메뉴 이름
    private ArrayList<Integer> mMainMenuImage;                  // 메인 메뉴에 들어가는 이미지 리소스
    private ArrayList<String> mMainMenuDescriptionList;         // 메인 메뉴 이름 아래 상세 설명
    private ArrayList<Integer> counter = new ArrayList<>();     // 현재 선택된 메뉴 펼쳐 보이기 위한 플레그
    private ArrayList<ArrayList> mItemNameList;                 // 서브 메뉴 이름
    private ArrayList<ArrayList> mItemSubImageList;             // 서브 메뉴에 들어가는 이미지 리소스
    private IMainMenuClickListener mListener;                   // 클릭시 전달 받을 리스너
    private Context mContext;

    public ExpandableRecyclerViewAdapter(Context context,
                                         ArrayList<String> mainMenu,
                                         ArrayList<Integer> mainMenuImage,
                                         ArrayList<String> mainMenuDescription,
                                         ArrayList<ArrayList> itemNameList,
                                         ArrayList<ArrayList> itemSubImageList,
                                         IMainMenuClickListener listener) {
        this.mMainMenuList = mainMenu;
        this.mMainMenuImage = mainMenuImage;
        this.mMainMenuDescriptionList = mainMenuDescription;
        this.mItemNameList = itemNameList;
        this.mItemSubImageList = itemSubImageList;
        this.mContext = context;
        this.mListener = listener;

        // 서브 메뉴 펼쳐 보이기 위한 플레그 초기화 셋팅.
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
        String strMainMenuName = mMainMenuList.get(position);
        holder.name.setText(strMainMenuName);   // 메뉴 이름
        holder.mainMenuImage.setImageResource(mMainMenuImage.get(position));    // 메뉴 이미지
        holder.description.setText(mMainMenuDescriptionList.get(position)); // 메뉴 설명
        // 서브 메뉴 셋팅
        InnerRecyclerViewAdapter itemInnerRecyclerView = new InnerRecyclerViewAdapter(position, strMainMenuName, mItemNameList.get(position), mItemSubImageList.get(position), mListener);
        holder.cardRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 클릭에 따라 visible/gone 처리.
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
        private TextView name, description;     // 메인 메뉴 이름, 설명
        private ImageView dropBtn;              // 펼침, 접힘 이미지 버튼
        private RecyclerView cardRecyclerView;  // 펼쳤을때 서브 메뉴 뷰
        private CardView cardView;              // 메인 메뉴 전체 뷰
        private ImageView mainMenuImage;        // 메인 메뉴 이미지

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

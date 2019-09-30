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

/**
 * 서브 메뉴 표시를 위한 Adapter.
 */
public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> arrNameList;                  // 서브 메뉴 이름
    private ArrayList<Integer> arrImageList;                // 서브 메뉴 이미지 리소스
    private String mParentName;                             // 메인 메뉴 이름
    private IMainMenuClickListener mMenuClickListener;      // 클릭 리스너

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
                    // 클릭시 부모 이름과 서브메뉴의 이름을 넘긴다. (두 이름의 조합으로 만들어야 하는 문제를 파악 한다.)
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
        private TextView name;          // 서브 메뉴 이름
        private ImageView image;        // 서브 메뉴 이미지
        private CardView cardView;      // 서브 메뉴 뷰
        private String parent;          // 부모 이름

        public ViewHolder(View itemView, String data) {
            super(itemView);
            name = itemView.findViewById(R.id.sub_menu_item_tv);
            image = itemView.findViewById(R.id.sub_menu_item_iv);
            cardView = itemView.findViewById(R.id.sub_menu_card_view);
            parent = data;
        }
    }
}

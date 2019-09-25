package com.jumpingi.arithmetic.ui.menu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenuActivity extends AppCompatActivity {
    private RecyclerView mExpanderRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        init();
    }

    private void init() {
        mExpanderRecyclerView = findViewById(R.id.main_menu_rv);

        initExpander();
    }

    private void initExpander() {
        // 리소스에서 array를 가져와서 String[] 배열에 대입
        String[] mainMenu = getResources().getStringArray(R.array.main_menu);
        int[] mainMenuImage = getResources().getIntArray(R.array.main_menu_image);

        String[] subMenu = getResources().getStringArray(R.array.sub_menu);
        String[] subMenuFraction = getResources().getStringArray(R.array.sub_menu_fraction);

        ArrayList<String> arrMainMenu = new ArrayList<>(Arrays.asList(mainMenu));
        ArrayList<Integer> arrMainMenuImage = (Integer) Arrays.asList(mainMenuImage);
        ArrayList<ArrayList> arrChildListHolder = new ArrayList<>();

        ArrayList<String> arrChildSubMenu = new ArrayList<>(Arrays.asList(subMenu));
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        ArrayList<String> arrChildSubMenuFraction = new ArrayList<>(Arrays.asList(subMenuFraction));
        arrChildListHolder.add(arrChildSubMenuFraction);

        ExpandableRecyclerViewAdapter expandableCategoryRecyclerViewAdapter =
                new ExpandableRecyclerViewAdapter(getApplicationContext(), arrMainMenu,
                        arrChildListHolder);
        mExpanderRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mExpanderRecyclerView.setAdapter(expandableCategoryRecyclerViewAdapter);
    }
}

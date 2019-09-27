package com.jumpingi.arithmetic.ui.menu;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jumpingi.arithmetic.R;
import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.question.QuestionActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuClickListener {
    private RecyclerView mExpanderRecyclerView;
    private ArrayList<ArrayList> arrChildListHolder;
    private ArrayList<String> arrMainMenu;

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
        String[] mainMenuDescription = getResources().getStringArray(R.array.main_menu_description);
        // 메인 메뉴 이미지를 array에서 읽어 온다.
        TypedArray tArray = getResources().obtainTypedArray(R.array.main_menu_image);
        int count = tArray.length();
        int[] mainMenuImage = new int[count];
        for (int i = 0; i < mainMenuImage.length; i++) {
            mainMenuImage[i] = tArray.getResourceId(i, 0);
        }
        tArray.recycle();

        tArray = getResources().obtainTypedArray(R.array.sub_menu_image);
        count = tArray.length();
        int[] subMenuImage = new int[count];
        for (int i = 0; i < subMenuImage.length; i++) {
            subMenuImage[i] = tArray.getResourceId(i, 0);
        }
        tArray.recycle();

        tArray = getResources().obtainTypedArray(R.array.sub_menu_fraction_image);
        count = tArray.length();
        int[] subMenuFractionImage = new int[count];
        for (int i = 0; i < subMenuFractionImage.length; i++) {
            subMenuFractionImage[i] = tArray.getResourceId(i, 0);
        }
        tArray.recycle();

        String[] subMenu = getResources().getStringArray(R.array.sub_menu);
        String[] subMenuFraction = getResources().getStringArray(R.array.sub_menu_fraction);

        arrMainMenu = new ArrayList<>(Arrays.asList(mainMenu));
        ArrayList<String> arrMainMenuDescription = new ArrayList<>(Arrays.asList(mainMenuDescription));
        ArrayList<Integer> arrMainMenuImage = new ArrayList<>(mainMenuImage.length);
        for (int i = 0; i < mainMenuImage.length; i++) {
            arrMainMenuImage.add(mainMenuImage[i]);
        }

        ArrayList<Integer> arrSubMenuImage = new ArrayList<>(subMenuImage.length);
        for (int i = 0; i < subMenuImage.length; i++) {
            arrSubMenuImage.add(subMenuImage[i]);
        }

        ArrayList<Integer> arrSubMenuFractionImage = new ArrayList<>(subMenuFractionImage.length);
        for (int i = 0; i < subMenuFractionImage.length; i++) {
            arrSubMenuFractionImage.add(subMenuFractionImage[i]);
        }

        arrChildListHolder = new ArrayList<>();
        ArrayList<ArrayList> arrChildListImageHolder = new ArrayList<>();

        ArrayList<String> arrChildSubMenu = new ArrayList<>(Arrays.asList(subMenu));
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListHolder.add(arrChildSubMenu);
        arrChildListImageHolder.add(arrSubMenuImage);
        arrChildListImageHolder.add(arrSubMenuImage);
        arrChildListImageHolder.add(arrSubMenuImage);
        arrChildListImageHolder.add(arrSubMenuImage);

        ArrayList<String> arrChildSubMenuFraction = new ArrayList<>(Arrays.asList(subMenuFraction));
        arrChildListHolder.add(arrChildSubMenuFraction);
        arrChildListImageHolder.add(arrSubMenuFractionImage);

        ArrayList<String> arrChildSubMenuQuiz = new ArrayList<>();
        arrChildSubMenuQuiz.add(getResources().getString(R.string.sub_menu_start));
        arrChildListHolder.add(arrChildSubMenuQuiz);
        ArrayList<Integer> arrChildSubMenuQuizImage = new ArrayList<>();
        arrChildSubMenuQuizImage.add(R.drawable.ic_start);
        arrChildListImageHolder.add(arrChildSubMenuQuizImage);


        ExpandableRecyclerViewAdapter expandableCategoryRecyclerViewAdapter =
                new ExpandableRecyclerViewAdapter(getApplicationContext(), arrMainMenu, arrMainMenuImage, arrMainMenuDescription
                        , arrChildListHolder, arrChildListImageHolder, this);
        mExpanderRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mExpanderRecyclerView.setAdapter(expandableCategoryRecyclerViewAdapter);
    }

    @Override
    public void onMenuClick(String strMainMenu, String strSubMenu) {
        Log.i("TEST", "Main Menu : " + strMainMenu + "  Sub Menu : " + strSubMenu);
        String strType;
        String strClickMenu = strMainMenu + strSubMenu;
        for (int i = 0; i < arrMainMenu.size(); i++) {
            for (int j = 0; j < arrChildListHolder.get(i).size(); j++) {
                strType = arrMainMenu.get(i) + arrChildListHolder.get(i).get(j);

                if (strType.equals(strClickMenu)) {
                    Intent intent = new Intent(MainMenuActivity.this, QuestionActivity.class);
                    intent.putExtra(Constant.IntentParam.INTENT_KEY_MENU_TYPE, strType);
                    startActivity(intent);
                    return;
                }
            }
        }
    }
}

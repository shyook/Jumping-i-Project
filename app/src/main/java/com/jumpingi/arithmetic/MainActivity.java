package com.jumpingi.arithmetic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jumpingi.arithmetic.constants.Constant;
import com.jumpingi.arithmetic.ui.menu.MainMenuActivity;
import com.jumpingi.arithmetic.utils.DialogUtils;
import com.jumpingi.arithmetic.utils.SharedPref;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mWelcomeArea, mNameArea;
    private RelativeLayout mEmailArea;
    private EditText mPassword, mEmail, mName;
    private Spinner mGenderSpinner;
    private Button mStart, mReset;
    private TextView mTitle, mNickname;
    private ImageView mUserImage;
    private SharedPref mSharedPref;
    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mSharedPref = SharedPref.getInstance(this);
        Random rand = new Random();
        final int background = Constant.BACKGROUND_GRADATION[rand.nextInt(Constant.TOTAL_BACKGROUND_GRADATION)];

        LinearLayout introBackground = findViewById(R.id.intro_rl);
        introBackground.setBackgroundResource(background);

        RelativeLayout titleBackground = findViewById(R.id.toolbar_title_area_rl);
        titleBackground.setBackgroundResource(background);

        mPassword = findViewById(R.id.password);   //Password EditText
        mEmail = findViewById(R.id.email);   //email EditText
        mName = findViewById(R.id.name);   //name EditText
        mStart = findViewById(R.id.intro_start_bt);
        mTitle = findViewById(R.id.title_tv);

        mWelcomeArea = findViewById(R.id.intro_welcome_ll);
        mNickname = findViewById(R.id.intro_welcome_nickname_tv);
        mNameArea = findViewById(R.id.linear_name);
        mEmailArea = findViewById(R.id.email_rl);
        mUserImage = findViewById(R.id.intro_image);
        mReset = findViewById(R.id.intro_password_bt);

        // 회원 가입 여부에 따라 UI 구성.
        if (getCheckJoin()) {
            // 이미 가입한 사람.
            setWelcome();
        } else {
            // 회원 가입 페이지
            setJoin();
        }

    }

    /**
     * 이미 가입한 사람은 password 입력 화면 노출.
     */
    private void setWelcome() {
        mTitle.setText(R.string.intro_title_login);
        // View 초기화.
        mWelcomeArea.setVisibility(View.VISIBLE);
        mNickname.setText(mSharedPref.getSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_NAME, ""));
        mNameArea.setVisibility(View.GONE);
        mEmailArea.setVisibility(View.GONE);

        mReset.setText(R.string.intro_welcome_forget_password);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 진짜 초기화 할것인지 묻는 팝업 디스플레이.
                DialogUtils.confirm(MainActivity.this, getString(R.string.popup_default_title)
                        , getString(R.string.popup_reset_message), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DialogUtils.dismissDialog();
                                // 확인 버튼 클릭시 저장된 값 초기화 후 화면 리로드.
                                if (which == DialogInterface.BUTTON_POSITIVE) {
                                    mSharedPref.clearSharedValue();
                                    init();
                                }
                            }
                        });
            }
        });

        String strGender = mSharedPref.getSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_GENDER, getString(R.string.intro_male));
        if (strGender.equals(getString(R.string.intro_male))) {
            mUserImage.setImageResource(R.drawable.king_icon);
        } else {
            mUserImage.setImageResource(R.drawable.queen_icon);
        }

        mStart.setText(R.string.intro_button_start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPasswordAndStart();
            }
        });

    }

    /**
     * 가입전인 사람은 가입 양식 화면 노출.
     */
    private void setJoin() {
        mTitle.setText(R.string.intro_title_join);

        mWelcomeArea.setVisibility(View.GONE);
        mNameArea.setVisibility(View.VISIBLE);
        mEmailArea.setVisibility(View.VISIBLE);

        mUserImage.setImageResource(R.drawable.users_people);
        mReset.setText(R.string.intro_optional);
        mReset.setOnClickListener(null);
        mStart.setText(R.string.intro_login);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkJoinItem();
            }
        });

        mGenderSpinner = findViewById(R.id.spinner);    // Gender Spinner
        String [] strItemGender = {getString(Constant.GENDER[0]), getString(Constant.GENDER[1])};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.item_intro_gender_spinner, strItemGender);
        genderAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(genderAdapter);
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mGender = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * 게임 시작을 위해 비밀번호가 일치하는지 확인 후 일치하면 게임 메뉴 화면으로 이동.
     */
    private void checkPasswordAndStart() {
        // 입력한 비밀번호 가져 오기
        String inputPassword = mPassword.getText().toString();
        // 저장된 비밀번호 가져 오기
        String password = mSharedPref.getSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_PASSWORD, "");

        // 저장된 비밀번호가 null이 아닌 상태에서 패스워드를 입력하지 않은 경우.
        if (! TextUtils.isEmpty(password) && TextUtils.isEmpty(inputPassword)) {
            Toast.makeText(this, R.string.intro_toast_input_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(inputPassword)) {
            // 게임 메뉴 화면으로 이동.
            goGameMenuPage();
        } else {
            Toast.makeText(this, R.string.intro_toast_wrong_password, Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 회원 가입 여부 반환.
     *
     * @return 이미 가입한 사람인 경우 true.
     */
    private boolean getCheckJoin() {
        return mSharedPref.getSharedValueByBoolean(SharedPref.PREF_KEYS.ARITHMETIC_JOIN_YN, false);
    }

    /**
     * 회원 가입을 위한 필수 항목 모두 채웠는지 확인.
     *
     */
    private void checkJoinItem() {
        // 별명, 별명은 필수값임.
        String strNickname = mName.getText().toString();
        if (TextUtils.isEmpty(strNickname)) {
            Toast.makeText(this, R.string.intro_toast_mandatory_error, Toast.LENGTH_SHORT).show();
            return;
        }
        mSharedPref.setSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_NAME, strNickname);

        // 비밀번호
        String strPassword = mPassword.getText().toString();
        if (! TextUtils.isEmpty(strPassword)) {
            mSharedPref.setSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_PASSWORD, strPassword);
        }

        // 이메일
        String strEmail = mEmail.getText().toString();
        if (! TextUtils.isEmpty(strEmail)) {
            mSharedPref.setSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_EMAIL, strEmail);
        }

        // 성별
        mSharedPref.setSharedValueByString(SharedPref.PREF_KEYS.ARITHMETIC_USER_GENDER, mGender);

        // 가입여부
        mSharedPref.setSharedValueByBoolean(SharedPref.PREF_KEYS.ARITHMETIC_JOIN_YN, true);

        goGameMenuPage();

    }

    /**
     * 게임 메뉴 화면으로 이동 한다.
     */
    private void goGameMenuPage() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        this.startActivity(intent);
        finish();
    }
}

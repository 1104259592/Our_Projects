package com.hq.app.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hq.app.R;
import com.hq.app.mylibrary.activitys.BaseActivity;

/**
 * 引导页
 */
public class LoginActivity extends BaseActivity implements TextWatcher, View.OnClickListener {

    private TextInputLayout mTextInputUserName, mTextInputPassword;
    private EditText mEtUserName;
    private TextInputEditText mTextInputEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
    }

    private void initView() {
        mTextInputUserName = (TextInputLayout) findViewById(R.id.text_input_user_name);
        mTextInputPassword = (TextInputLayout) findViewById(R.id.text_input_password);
        mEtUserName = (EditText) findViewById(R.id.et_user_name);
        mTextInputEtPassword = (TextInputEditText) findViewById(R.id.text_input_et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void init() {
        mTextInputUserName.setCounterEnabled(true);
        mTextInputUserName.setCounterMaxLength(10);

        mTextInputEtPassword.addTextChangedListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
        mTextInputPassword.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String passwordText = mTextInputEtPassword.getText().toString();
                if (!TextUtils.isEmpty(passwordText) && passwordText.length() < 6) {
                    mTextInputPassword.setError(getString(R.string.password_error));
                    mTextInputEtPassword.setError(getString(R.string.password_error));
                } else if (!TextUtils.isEmpty(passwordText)) {
                    Intent intent = MainActivity.startMainActivity(this);
                    baseStartIntent(intent);
                }
                break;
        }
    }

    public static Intent startLoginActivity(Context c) {
        Intent intent = new Intent(c, LoginActivity.class);
        return intent;
    }
}

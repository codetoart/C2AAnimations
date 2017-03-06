package codetoart.sampleanimations;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.codetoart.c2aanimations.widget.C2AAnimationLoadingButton;

public class LoadingButtonActivity extends AppCompatActivity {

    private EditText mEditUsername, mEditPassword;
    private C2AAnimationLoadingButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_button);
        initView();
    }

    private void initView() {
        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);
        mButton = (C2AAnimationLoadingButton) findViewById(R.id.button_login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.startLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String username = mEditUsername.getText().toString();
                        String password = mEditPassword.getText().toString();

                        if (isValid(username, password)) {
                            mButton.reset();
                            openMainActivity();
                        } else
                            mButton.loadingFail();
                    }
                }, 5000);
            }
        });
    }

    private void openMainActivity() {
        finish();
    }

    private boolean isValid(String username, String password) {
        if (username.trim().equals("admin") && password.trim().equals("admin")) {
            return true;
        }
        return false;
    }
}

package codetoart.sampleanimations;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.codetoart.c2aanimations.widget.C2AAnimationLoadingButton;

public class LoadingButtonActivity extends AppCompatActivity {

    private EditText mEditUsername, mEditPassword;
    private C2AAnimationLoadingButton mButton;
    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_button);
        initView();
    }

    private void initView() {
        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);
        mRootLayout = (LinearLayout) findViewById(R.id.activity_loading_button);
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
                            StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(
                                    LoadingButtonActivity.this, R.animator.raise);
                            mButton.loadingSuccess(new C2AAnimationLoadingButton.AnimationSuccess() {
                                @Override
                                public void success() {
                                    finish();
                                }
                            });
                        } else
                            mButton.loadingFail();
                    }
                }, 5000);
            }
        });
    }

    private boolean isValid(String username, String password) {
        if (username.trim().equals("admin") && password.trim().equals("admin")) {
            return true;
        }
        return false;
    }
}

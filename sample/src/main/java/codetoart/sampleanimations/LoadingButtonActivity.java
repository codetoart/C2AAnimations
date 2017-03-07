package codetoart.sampleanimations;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.codetoart.c2aanimations.widget.C2AAnimationLoadingButton;

public class LoadingButtonActivity extends AppCompatActivity {

    private EditText mEditUsername, mEditPassword;
    private C2AAnimationLoadingButton mButton;
    private LinearLayout mRootLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_button);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Loading Button");
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean isValid(String username, String password) {
        if (username.trim().equals("admin") && password.trim().equals("admin")) {
            return true;
        }
        return false;
    }
}

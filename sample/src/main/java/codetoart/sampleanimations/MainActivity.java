package codetoart.sampleanimations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            String[] animationArray = getResources().getStringArray(R.array.animation_list);
            RecyclerAdapter animationApisAdapter =
                    new RecyclerAdapter(animationArray, onRecyclerItemClick);
        mRecyclerView.setAdapter(animationApisAdapter);
        /*findViewById(R.id.toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, SwitchButtonActivity.class);
                View sharedView = ((Button)findViewById(R.id.toggle));
                String transitionName = getString(R.string.button1);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());
                //startActivity(i);
            }
        });*/
    }

    private RecyclerAdapter.OnRecyclerItemClick onRecyclerItemClick =
            new RecyclerAdapter.OnRecyclerItemClick() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = null;
                    switch (position) {
                        case 0:
                            intent = new Intent(MainActivity.this, SwitchButtonActivity.class);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, LoadingButtonActivity.class);
                            break;
                    }

                    if (intent != null){
                        String transitionName = getString(R.string.shared_transition);

                        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mRecyclerView, transitionName);
                        startActivity(intent, transitionActivityOptions.toBundle());
                        //startActivity(intent);
                    }
                }
            };
}

package com.example.cents;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btXiuLian,btTupo;
    private ProgressBar mProgressBar;
    private TextView mLevelView;
    private int mCurrProgress = 0;
    private int mAbility = 20;
    private int mThreshold;
    private String[] mLevel = {"练气第一层", "练气第二层", "练气第三层", "练气第四层",
            "练气第五层", "练气第六层", "练气第七层", "练气第八层", "练气第九层", "练气第十层",
            "练气第十一层", "练气第十二层", "练气第十三层"};
    private int mCurrLevel = 0;
    private SharedHelper mSh;
    private static String ABILITY = "ability";
    private static String THRESHOLD = "threshold";
    private static String PROGRESS = "progress";
    private static String CURRENT_LEVEL = "currentLevel";
    private static String TAG = "zengjinhai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        btXiuLian = (ImageButton) findViewById(R.id.xiulian);
        btTupo = (ImageButton) findViewById(R.id.tupo);
        mLevelView = (TextView) findViewById(R.id.level);

        btXiuLian.setTag(1);
        btTupo.setTag(2);
        btXiuLian.setOnClickListener(this);
        btTupo.setOnClickListener(this);

        mSh = new SharedHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAbility = mSh.read(ABILITY);
        mThreshold = mSh.read(THRESHOLD);
        mCurrProgress = mSh.read(PROGRESS);
        mCurrLevel = mSh.read(CURRENT_LEVEL);
        mProgressBar.setProgress(mCurrProgress);
        mLevelView.setText(mLevel[mCurrLevel]);
    }

    @Override
    protected void onPause() {
        mSh.save(ABILITY, mAbility);
        mSh.save(THRESHOLD, mThreshold);
        mSh.save(PROGRESS, mCurrProgress);
        mSh.save(CURRENT_LEVEL, mCurrLevel);
        super.onPause();
    }

    public void onClick(View v){
        int tag = (Integer) v.getTag();
        switch(tag){
            case 1:
                mCurrProgress += mAbility;
                float ratio = (float)mCurrProgress / mThreshold;
                Log.d(TAG, "mCurrProgress: " + mCurrProgress + ", mThreshold" + mThreshold);
                int num = (int) (100 * ratio);
                mProgressBar.setProgress(num);
                break;
            case 2:
                if (mCurrProgress >= mThreshold) {
                    mCurrLevel++;
                    mLevelView.setText(mLevel[mCurrLevel]);
                    mProgressBar.setProgress(0);
                    mAbility += 10;
                    mThreshold *= 2;
                    mCurrProgress = 0;
                }

                break;
            default :
                break;
        }

    }
}
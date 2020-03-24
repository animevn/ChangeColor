package com.haanhgs.app.changecolordemo.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.haanhgs.app.changecolordemo.R;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lnColor)
    LinearLayout lnColor;
    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.bnColor)
    Button bnColor;

    private void hideActionBar(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    private void hideStatusBar(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionBar();
        hideStatusBar();
    }

    @OnClick(R.id.bnColor)
    public void onViewClicked(){
    }
}

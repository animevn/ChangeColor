package com.haanhgs.app.uichangecolor.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.haanhgs.app.uichangecolor.R;
import com.haanhgs.app.uichangecolor.viewmodel.ViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
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

    private ViewModel viewModel;

    private void hideActionBar(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    private void hideStatusBar(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void updateViews(String colorName, int colorRGB, int colorRes){
        tvColor.setText(colorName);
        tvColor.setBackgroundColor(colorRGB);
        lnColor.setBackgroundColor(colorRGB);
        bnColor.setBackgroundTintList(ContextCompat.getColorStateList(this, colorRes));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideActionBar();
        hideStatusBar();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveData().observe(this, model ->
                updateViews(model.getColorName(), model.getColorRGB(), model.getColorRes()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.loadModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.saveModel();
    }

    @OnClick(R.id.bnColor)
    public void onViewClicked(){
        viewModel.iterateThrougList();
    }
}

package com.haanhgs.app.uichangecolor.view;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideStatusBar();
        hideActionBar();
        initViewModel();
    }

    @SuppressWarnings("deprecation")
    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            final WindowInsetsController controller = getWindow().getInsetsController();
            if (controller != null){
                controller.hide(WindowInsets.Type.statusBars());
            }
        }else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }
    }

    private void hideActionBar() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getLiveData().observe(this, model -> {
            tvColor.setText(model.getColorName());
            tvColor.setBackgroundColor(model.getColorRgb());
            lnColor.setBackgroundColor(model.getColorRgb());
            bnColor.setBackgroundTintList(
                    ContextCompat.getColorStateList(MainActivity.this, model.getColorRes())
            );
        });
    }

    @OnClick(R.id.bnColor)
    public void onViewClicked() {
        viewModel.iterateThroughColorList();
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
}

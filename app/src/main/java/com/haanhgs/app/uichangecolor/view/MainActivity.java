package com.haanhgs.app.uichangecolor.view;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import com.haanhgs.app.uichangecolor.databinding.ActivityMainBinding;
import com.haanhgs.app.uichangecolor.viewmodel.ViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideStatusBar();
        hideActionBar();
        initViewModel();
        binding.bnColor.setOnClickListener(view->viewModel.iterateThroughColorList());
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
            binding.tvColor.setText(model.getColorName());
            binding.tvColor.setBackgroundColor(model.getColorRgb());
            binding.lnColor.setBackgroundColor(model.getColorRgb());
            binding.bnColor.setBackgroundTintList(
                    ContextCompat.getColorStateList(MainActivity.this, model.getColorRes())
            );
        });
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

package com.haanhgs.app.changecolordemo;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    private int[]colorIds;
    private String[]colorNames;
    private String colorName;
    private int i = 0;
    private int colorRes;
    private int colorRGB;

    private static final String COLORNAME = "colorName";
    private static final String COLORRES = "colorRes";
    private static final String COLORRGB = "colorRGB";
    private static final String ITERATOR = "iterator";

    private void loadInstanceState(Bundle bundle){
        if (bundle != null){
            i = bundle.getInt(ITERATOR);
            colorRes = bundle.getInt(COLORRES);
            colorRGB = bundle.getInt(COLORRGB);
            colorName = bundle.getString(COLORNAME);
        }

        if (colorName != null) tvColor.setText(colorName);
        if (colorRes != 0){
            bnColor.setBackgroundTintList(ContextCompat.getColorStateList(this, colorRes));
        }
        if (colorRGB != 0){
            tvColor.setTextColor(colorRGB);
            lnColor.setBackgroundColor(colorRGB);
        }
    }

    private void hideActionBar(){
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    private void hideStatusBar(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getColorNamesList(){
        colorNames = getResources().getStringArray(R.array.colors);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadInstanceState(savedInstanceState);
        hideActionBar();
        hideStatusBar();
        getColorNamesList();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(COLORNAME, colorName);
        outState.putInt(COLORRES, colorRes);
        outState.putInt(COLORRGB, colorRGB);
        outState.putInt(ITERATOR, i);
    }

    private void getColorForEachIterate(){
        colorName = colorNames[i];
        colorRes = getResources().getIdentifier(colorName, "color", getPackageName());
        colorRGB = ContextCompat.getColor(this, colorRes);
    }


    private void iterateThroungColorList(){
        getColorForEachIterate();
        lnColor.setBackgroundColor(colorRGB);
        tvColor.setText(colorName);
        tvColor.setTextColor(colorRGB);
        bnColor.setBackgroundTintList(ContextCompat.getColorStateList(this, colorRes));
        i++;
        if (i == colorNames.length) i = 0;
    }

    @OnClick(R.id.bnColor)
    public void onViewClicked(){
        iterateThroungColorList();
    }
}

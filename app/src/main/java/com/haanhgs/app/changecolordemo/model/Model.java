package com.haanhgs.app.changecolordemo.model;

import android.content.Context;

import com.haanhgs.app.changecolordemo.R;

import androidx.core.content.ContextCompat;

public class Model {

    private String[]colorNames;
    private String colorName;
    private int iterator;
    private int colorRes;
    private int colorRGB;

    private void getColorForEachIterate(Context context){
        colorName = colorNames[iterator];
        colorRes = context.getResources()
                .getIdentifier(colorName, "color", context.getPackageName());
        colorRGB = ContextCompat.getColor(context, colorRes);
    }

    public void iterateThroughColorList(Context context){
        getColorForEachIterate(context);
        iterator++;
        if (iterator == colorNames.length) iterator = 0;
    }

    public void setColorNames(String[] colorNames) {
        this.colorNames = colorNames;
    }

    public String getColorName() {
        return colorName;
    }

    public int getIterator() {
        return iterator;
    }

    public int getColorRes() {
        return colorRes;
    }

    public int getColorRGB() {
        return colorRGB;
    }

}

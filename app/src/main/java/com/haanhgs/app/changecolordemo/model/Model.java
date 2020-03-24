package com.haanhgs.app.changecolordemo.model;

import android.content.Context;

import com.haanhgs.app.changecolordemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.core.content.ContextCompat;

public class Model {

    private List<String> colorNames = new ArrayList<>();
    private String colorName;
    private int iterator = 0;
    private int colorRes;
    private int colorRGB;

    public void getColorForEachIterate(Context context){
        colorName = colorNames.get(iterator);
        colorRes = context.getResources()
                .getIdentifier(colorName, "color", context.getPackageName());
        colorRGB = ContextCompat.getColor(context, colorRes);
    }

    public void iterateThroughColorList(Context context){
        iterator++;
        if (iterator == colorNames.size()) iterator = 0;
        getColorForEachIterate(context);
    }

    public void setColorNames(String[] colorNames) {
        Collections.addAll(this.colorNames, colorNames);
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

package com.haanhgs.app.uichangecolor.model;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import androidx.core.content.ContextCompat;

public class Model implements Serializable {

    private final List<String> colorNames = new ArrayList<>();
    private int iterator = 0;
    private String colorName;
    private int colorRes;
    private int colorRGB;

    private static final long serialUID = 220479;

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

    public int getColorRes() {
        return colorRes;
    }

    public int getColorRGB() {
        return colorRGB;
    }
}

package com.haanhgs.app.uichangecolor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model implements Serializable {

    private static final long serialUID = 220479;

    private List<String> colorNames = new ArrayList<>();
    private int iterator = 0;
    private String colorName;
    private int colorRes;
    private int colorRgb;

    public void setColorNames(String[] colorNames){
        Collections.addAll(this.colorNames, colorNames);
    }

    public List<String> getColorNames() {
        return colorNames;
    }

    public int getIterator() {
        return iterator;
    }

    public void setIterator(int iterator) {
        this.iterator = iterator;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }

    public int getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(int colorRgb) {
        this.colorRgb = colorRgb;
    }
}

package com.haanhgs.app.changecolordemo.viewmodel;

import android.app.Application;
import android.content.Context;

import com.haanhgs.app.changecolordemo.R;
import com.haanhgs.app.changecolordemo.model.Model;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private MutableLiveData<Model>liveData = new MutableLiveData<>();
    private Context context;

    private static Model initModel(Context context){
        Model model = new Model();
        model.setColorNames(context.getResources().getStringArray(R.array.colors));
        return model;
    }

    public ViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        liveData.setValue(initModel(context));
    }

    public MutableLiveData<Model> getLiveData() {
        return liveData;
    }

    public void iterateThrougList(){
        if (liveData.getValue() == null) liveData.setValue(initModel(context));
        Model model = liveData.getValue();
        model.iterateThroughColorList(context);
        liveData.setValue(model);
    }

}

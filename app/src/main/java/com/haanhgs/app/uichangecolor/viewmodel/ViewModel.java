package com.haanhgs.app.uichangecolor.viewmodel;

import android.app.Application;
import com.haanhgs.app.uichangecolor.model.Model;
import com.haanhgs.app.uichangecolor.model.Repo;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private final Repo repo;

    public ViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application.getApplicationContext());
    }

    public LiveData<Model> getLiveData(){
        return repo.getLiveData();
    }

    public void iterateThroughColorList(){
        repo.iterateThroughColorList();
    }

    public void loadModel(){
        repo.loadModel();
    }

    public void saveModel(){
        repo.saveModel();
    }

}

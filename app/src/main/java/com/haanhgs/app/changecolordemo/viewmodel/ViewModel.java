package com.haanhgs.app.changecolordemo.viewmodel;

import android.app.Application;
import android.content.Context;
import com.haanhgs.app.changecolordemo.R;
import com.haanhgs.app.changecolordemo.model.Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ViewModel extends AndroidViewModel {

    private final MutableLiveData<Model>liveData = new MutableLiveData<>();
    private final Context context;

    private static Model initModel(Context context){
        Model model = new Model();
        model.setColorNames(context.getResources().getStringArray(R.array.colors));
        model.getColorForEachIterate(context);
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

    public void saveModel(){
        if (liveData.getValue() != null){
            Model model = liveData.getValue();
            try{
                FileOutputStream out = context.openFileOutput("save", Context.MODE_PRIVATE);
                ObjectOutputStream stream = new ObjectOutputStream(out);
                stream.writeObject(model);
                stream.flush();
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void loadModel(){
        Model model = initModel(context);
        File file = new File(context.getFilesDir(), "save");
        if (file.exists()){
            try{
                FileInputStream in = context.openFileInput("save");
                ObjectInputStream stream = new ObjectInputStream(in);
                Object object = stream.readObject();
                model = (Model)object;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        liveData.setValue(model);
    }

}

package com.haanhgs.app.uichangecolor.model;

import android.content.Context;
import android.util.Log;
import com.haanhgs.app.uichangecolor.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

public class Repo {

    private final MutableLiveData<Model> liveData = new MutableLiveData<>();
    private Model model;
    private final Context context;

    public Repo(Context context) {
        this.context = context;
        initModel();
        liveData.setValue(model);
    }

    private void initModel(){
        model = new Model();
        model.setColorNames(context.getResources().getStringArray(R.array.colors));
        getColorForEachIterator();
    }

    private void getColorForEachIterator(){
        model.setColorName(model.getColorNames().get(model.getIterator()));
        model.setColorRes(context.getResources()
                .getIdentifier(model.getColorName(), "color", context.getPackageName()));
        model.setColorRgb(ContextCompat.getColor(context, model.getColorRes()));
    }

    public MutableLiveData<Model> getLiveData(){
        return liveData;
    }

    public void iterateThroughColorList(){
        model.setIterator(model.getIterator() + 1);
        if (model.getIterator() == model.getColorNames().size()){
            model.setIterator(0);
        }
        getColorForEachIterator();
        liveData.setValue(model);
    }

    public void saveModel(){
        try(FileOutputStream outputStream = context.openFileOutput("save", Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(outputStream)
        ){
            stream.writeObject(model);
            stream.flush();
        }catch (Exception e){
            Log.e("Error: ", e.toString());
        }
    }

    public void loadModel(){
        initModel();
        File file = new File(context.getFilesDir(), "save");
        if (file.exists()){
            try(FileInputStream inputStream = context.openFileInput("save");
                ObjectInputStream stream = new ObjectInputStream(inputStream)
            ){
                Object object = stream.readObject();
                model = (Model)object;
            }catch (Exception e){
                Log.e("Error: ", e.toString());
            }
        }
        liveData.setValue(model);
    }

}

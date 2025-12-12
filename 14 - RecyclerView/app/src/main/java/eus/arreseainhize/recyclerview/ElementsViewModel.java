package eus.arreseainhize.recyclerview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ElementsViewModel extends AndroidViewModel {

    ElementsRepository elementsRepository;

    MutableLiveData<List<Element>> listElementsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Element> elementSelected = new MutableLiveData<>();

    public ElementsViewModel(@NonNull Application application) {
        super(application);

        elementsRepository = new ElementsRepository();

        listElementsMutableLiveData.setValue(elementsRepository.get());
    }


    MutableLiveData<List<Element>> get(){
        return listElementsMutableLiveData;
    }

    void add(Element element){
        elementsRepository.insert(element, new ElementsRepository.Callback() {
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }

    void delete(Element element){
        elementsRepository.delete(element, new ElementsRepository.Callback() {
            @Override
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }

    void update(Element element, float rating){
        elementsRepository.update(element, rating, new ElementsRepository.Callback() {
            @Override
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }

    void select(Element element){
        elementSelected.setValue(element);
    }

    MutableLiveData<Element> selected(){
        return elementSelected;
    }
}

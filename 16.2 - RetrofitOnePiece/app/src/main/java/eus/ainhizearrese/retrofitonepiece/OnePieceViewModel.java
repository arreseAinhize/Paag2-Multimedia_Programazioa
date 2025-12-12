package eus.ainhizearrese.retrofitonepiece;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Egindako aldaketak:
 *
 * 1.- Cambiar "MutableLiveData<OnePiece> a MutableLiveData<OnePiece.Content> en la clase
 *
 * 2.- Ajustar el metodo "search" para manejar "Call<OnePiece.Content>".
 *
 * 3.- Agregar un bloque de try-catch para "NumberFormatException" al parsear el texto del
 * SearchView.
 *
 * 4.- Agregar una verificación "if (s != null && !s.isEmpty())" en "onQueryTextChange"
 * para evitar errores al buscar.
 */
public class OnePieceViewModel extends AndroidViewModel {
    MutableLiveData<OnePiece.Content> responseMutableLiveData = new MutableLiveData<>();

    public OnePieceViewModel(@NonNull Application application) {
        super(application);
    }

    public void search(String text){
        try {
            int id = Integer.parseInt(text);
            OnePiece.api.search(id).enqueue(new Callback<OnePiece.Content>() {
                @Override
                public void onResponse(@NonNull Call<OnePiece.Content> call, @NonNull Response<OnePiece.Content> response) {
                    if (response.body() != null) {
                        Log.d("OnePieceViewModel", response.body().toString());
                        responseMutableLiveData.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<OnePiece.Content> call, @NonNull Throwable t) {
                    Log.d("OnePieceViewModel","Error on connection: "+call.toString()+t.getMessage());
                }
            });
        } catch (NumberFormatException e) {
            Log.e("OnePieceViewModel", "Invalid ID: " + text);
        }
    }
}

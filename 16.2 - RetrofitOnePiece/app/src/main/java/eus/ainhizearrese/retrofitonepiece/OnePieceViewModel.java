package eus.ainhizearrese.retrofitonepiece;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnePieceViewModel extends AndroidViewModel {
    MutableLiveData<OnePiece.Response> responseMutableLiveData = new MutableLiveData<>();

    public OnePieceViewModel(@NonNull Application application) {
        super(application);
    }

    public void search(String text){
        OnePiece.api.search(Integer.parseInt(text)).enqueue(new Callback<OnePiece.Response>() {
            @Override
            public void onResponse(@NonNull Call<OnePiece.Response> call, @NonNull Response<OnePiece.Response> response) {
                assert response.body() != null;
                Log.d("OnePieceViewModel", response.body().toString());
                responseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<OnePiece.Response> call, @NonNull Throwable t) {
                Log.d("OnePieceViewModel","Error on connection: "+call.toString()+t.getMessage());
            }
        });
    }
}

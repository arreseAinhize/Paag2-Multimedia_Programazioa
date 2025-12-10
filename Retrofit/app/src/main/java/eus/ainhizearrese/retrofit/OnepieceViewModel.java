package eus.ainhizearrese.retrofit;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnepieceViewModel extends AndroidViewModel {
    MutableLiveData<OnePiece.Response> responseMutableLiveData = new MutableLiveData<>();

    public OnepieceViewModel(@NonNull Application application) {
        super(application);
    }

    public void search(String text){
        OnePiece.api.search(text).enqueue(new Callback<OnePiece.Response>() {
            @Override
            public void onResponse(@NonNull Call<OnePiece.Response> call, @NonNull Response<OnePiece.Response> response) {
                responseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<OnePiece.Response> call, @NonNull Throwable t) {
                Log.d("onFailure","Error on connection");
            }
        });
    }
}

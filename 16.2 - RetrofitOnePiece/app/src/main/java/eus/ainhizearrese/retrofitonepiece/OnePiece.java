package eus.ainhizearrese.retrofitonepiece;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class OnePiece {
    // 1. Class to map the JSON (Response)
    class Response{
        List<Content> results;
    }

    // 1. Class to map the JSON (Content)
    class Content{
        int id;
        String name;
        String description;
        String roman_name;
        String type; //fruit type
        String filename; // fruit img
        String technicalFile;
    }

    public interface Api {
        @GET("fruits/en/{id}")
        Call<Response> search(@Path("id") int text);
    }

    public static Api api = new Retrofit.Builder()
            .baseUrl("https://api.api-onepiece.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
}

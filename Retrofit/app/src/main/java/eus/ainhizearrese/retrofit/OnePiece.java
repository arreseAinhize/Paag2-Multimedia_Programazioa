package eus.ainhizearrese.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class OnePiece {
    // 1. Class to map the JSON (Response)
    class Response{
        List<Content> results;
    }

    // 1. Class to map the JSON (Content)
    class Content{
        String name;
        String description;
        String type; //fruit type
        String filename; // fruit img
    }

    public interface Api {
        @GET("search/")
        Call<Response> search(@Query("term") String text);
    }

    public static Api api = new Retrofit.Builder()
            .baseUrl("https://api.api-onepiece.com/v2/fruits/en/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
}

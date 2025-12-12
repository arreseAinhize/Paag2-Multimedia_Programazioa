package eus.ainhizearrese.retrofiturko;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Itunes {
    // 1. Class to map the JSON (Response)
    class Response{
        List<Content> results;
    }

    // 1. Class to map the JSON (Content)
    class Content{
        String artistName;
        String trackName;
        String artworkUrl100;
    }

    // 2. Interface that defines the HTTP calls
    public interface Api {
        @GET("search/")
        Call<Response> search(@Query("term") String text);
    }

    // 3. Static variable where Retrofit will implement the interface
    public static Api api = new Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
}

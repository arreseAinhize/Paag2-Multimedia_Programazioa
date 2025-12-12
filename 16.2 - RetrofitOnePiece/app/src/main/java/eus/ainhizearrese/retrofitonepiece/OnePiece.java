package eus.ainhizearrese.retrofitonepiece;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Egindako aldaketak:
 *
 * 1.- Eliminar la clase "Renpose" porque la api devuelve directamente el objeto de la fruta,
 * y no una lista de resultados envuelta.
 *
 * 2.- Convertia "Content" en "static" para poder usarla fuera de la clase si fuera necesario.
 *
 * 3.- Actualizar la interfaz "API" para que el metodo "search" devuelva un objeto
 * de la clase "Content".
 */
public class OnePiece {
    // 1. Class to map the JSON (Response)
    class Response{
        List<Content> results;
    }

    // 1. Class to map the JSON (Content)
    static class Content{
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
        Call<Content> search(@Path("id") int id);
    }

    public static Api api = new Retrofit.Builder()
            .baseUrl("https://api.api-onepiece.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
}

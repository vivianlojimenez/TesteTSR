package tsr.com.br.tsr.controller.ws.config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author vivianlo
 * Default class to get the return of requests
 */
public abstract class RestCallback<T> implements Callback<T> {

    public abstract void onSuccess(T response);

    public abstract void onError(int code);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onError(response.code());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(-1);

    }
}
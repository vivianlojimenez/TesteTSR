package tsr.com.br.tsr.controller.ws.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author vivianlo
 * Class used to establish connection and consumption of webservices - REST.
 */
public class APIClient {

    private static String url = "http://ec2-54-171-222-219.eu-west-1.compute.amazonaws.com:8484";

    private static OkHttpClient mClient = getClient();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).client(mClient).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(mClient).build();
        return retrofit.create(serviceClass);
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(mLogging)
                .build();
    }

}



package tsr.com.br.tsr.controller.ws.config;

import retrofit2.Call;
import retrofit2.http.GET;
import tsr.com.br.tsr.model.ws.ResponseWS;

/**
 * @author vivianlo
 * This class must contain all parameters to make requests
 */
public interface IServiceRestListener {

    /** get product list*/
    @GET("/lista/")
    Call<ResponseWS> getListProducts();

}

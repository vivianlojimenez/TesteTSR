package tsr.com.br.tsr.controller.ws;

import tsr.com.br.tsr.controller.ws.config.APIClient;
import tsr.com.br.tsr.controller.ws.config.BaseView;
import tsr.com.br.tsr.controller.ws.config.IServiceRestListener;
import tsr.com.br.tsr.controller.ws.config.RestCallback;
import tsr.com.br.tsr.model.ws.ResponseWS;

/**
 * @author vivianlo
 * Controller to get products list
 */
public class GetListController extends BaseView {

    private IGetListListener listener;

    public GetListController(IGetListListener listener) {
        this.listener = listener;
    }

    //The request is made in thread to not freeze the screen
    @Override
    public void run() {
        updateInfo();
    }

    //request
    private void updateInfo() {

        doRequest(APIClient.createService(IServiceRestListener.class).getListProducts(), new RestCallback<ResponseWS>() {
            @Override
            public void onSuccess(ResponseWS response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(int code) {
                listener.onError(code);
            }
        });
    }


    //listener
    public interface IGetListListener {
        void onSuccess(ResponseWS itens);

        void onError(int code);
    }
}
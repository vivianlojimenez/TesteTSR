package tsr.com.br.tsr.controller.ws.config;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * @author vivianlo
 * Default class  to make all requests
 */
public abstract class BaseView extends Thread {

    private List<Call> mCalls;

    protected <T> void doRequest(final Call call, final RestCallback<T> restCallback) {

        if (mCalls == null)
            mCalls = new ArrayList<>();

        mCalls.add(call);

        call.enqueue(new RestCallback<T>() {
            @Override
            public void onSuccess(T response) {
                mCalls.remove(call);
                restCallback.onSuccess(response);
            }

            @Override
            public void onError(int code) {
                mCalls.remove(call);
                restCallback.onError(code);
            }
        });
    }

    public void cancelCalls() {
        if (mCalls != null) {
            for (Call call : mCalls) call.cancel();
        }
        mCalls.clear();
    }

    public void cancelLastCall() {
        mCalls.get(mCalls.size() - 1).cancel();
        mCalls.clear();
    }
}

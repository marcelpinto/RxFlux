package com.hardsoftstudio.rxflux.sample.ui;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hardsoftstudio.rxflux.RxFlux;
import com.hardsoftstudio.rxflux.action.RxError;
import com.hardsoftstudio.rxflux.dispatcher.RxViewDispatch;
import com.hardsoftstudio.rxflux.sample.SampleApp;
import com.hardsoftstudio.rxflux.store.RxStore;
import com.hardsoftstudio.rxflux.store.RxStoreChange;

import java.util.List;

/**
 * Created by eprendre on 8/23/16.
 */
public class FluxFragment extends Fragment implements RxViewDispatch {

    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
    }

    @Override
    public void onRxError(@NonNull RxError error) {
    }

    @Override
    public void onRxViewRegistered() {
    }

    @Override
    public void onRxViewUnRegistered() {
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return null;
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        List<RxStore> rxStoreList = getRxStoreListToRegister();
        if (rxStoreList != null) {
            for (RxStore rxStore : rxStoreList) {
                rxStore.register();
            }
        }
        onRxViewRegistered();
        RxFlux rxFlux = SampleApp.get(getActivity()).getRxFlux();
        rxFlux.getDispatcher().subscribeRxView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        RxFlux rxFlux = SampleApp.get(getActivity()).getRxFlux();
        List<RxStore> rxStoreList = getRxStoreListToUnRegister();
        if (rxStoreList != null) {
            for (RxStore rxStore : rxStoreList) {
                rxStore.unregister();
                rxFlux.getSubscriptionManager().remove(rxStore.getActionListToUnsubscribe());
            }
        }
        onRxViewUnRegistered();
        rxFlux.getDispatcher().unsubscribeRxView(this);
    }
}

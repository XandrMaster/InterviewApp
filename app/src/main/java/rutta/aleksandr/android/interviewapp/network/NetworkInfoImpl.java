/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.network;

import android.content.Context;

import io.reactivex.Single;

public class NetworkInfoImpl implements NetworkInfo {

    private final Context context;

    public NetworkInfoImpl(Context context) {
        this.context = context;
    }

    @Override
    public Single<Boolean> isNetworkEnable() {
        // TODO: 12/15/18 STUB
        return Single.just(false);
    }
}
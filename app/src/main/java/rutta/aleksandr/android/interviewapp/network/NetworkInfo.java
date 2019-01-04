/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.network;

import io.reactivex.Single;

public interface NetworkInfo {
    Single<Boolean> isNetworkEnable();
}
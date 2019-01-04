/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.network;

import rutta.aleksandr.android.interviewapp.domain.Contacts;

import io.reactivex.Single;

public class RetrofitImpl implements Retrofit {
    @Override
    public Single<Contacts> getContactsFromNetwork() {
        // TODO: 12/15/18 STUB
        return Single.just(new Contacts());
    }
}
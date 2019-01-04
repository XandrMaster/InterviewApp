/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.network;

import rutta.aleksandr.android.interviewapp.domain.Contacts;

import io.reactivex.Single;

public interface Retrofit {
    Single<Contacts> getContactsFromNetwork();
}
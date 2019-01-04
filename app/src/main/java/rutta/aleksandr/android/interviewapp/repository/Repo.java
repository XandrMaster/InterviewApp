/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.repository;

import rutta.aleksandr.android.interviewapp.domain.Contacts;

import io.reactivex.Single;

public interface Repo {
    Single<Contacts> getContacts();
}

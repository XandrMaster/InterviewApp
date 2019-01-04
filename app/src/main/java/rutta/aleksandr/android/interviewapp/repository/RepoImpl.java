/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.repository;

import android.content.Context;

import com.google.gson.Gson;
import rutta.aleksandr.android.interviewapp.domain.Contacts;
import rutta.aleksandr.android.interviewapp.network.NetworkInfo;
import rutta.aleksandr.android.interviewapp.network.Retrofit;
import rutta.aleksandr.android.interviewapp.utils.ResourceUtils;

import java.io.InputStream;

import io.reactivex.Single;

import static rutta.aleksandr.android.interviewapp.Const.CONTACTS_ASSERT;

public class RepoImpl implements Repo {

	private final NetworkInfo networkInfo;
	private final Retrofit retrofit;
	private final Context context;
	private final Gson gson;

	public RepoImpl(Context context
			, NetworkInfo networkInfo
			, Retrofit retrofit
			, Gson gson) {
		this.context = context;
		this.networkInfo = networkInfo;
		this.retrofit = retrofit;
		this.gson = gson;
	}

	@Override
	public Single<Contacts> getContacts() {
		return networkInfo.isNetworkEnable()
				.flatMap(isNetworkEnable -> {
					Single<Contacts> contactsFromLocal = getContactsFromLocal();
					if (isNetworkEnable) {
						return retrofit.getContactsFromNetwork()
								.onErrorResumeNext(contactsFromLocal);
					} else {
						return contactsFromLocal;
					}
				});
	}

	private Single<Contacts> getContactsFromLocal() {
		return Single.just(context)
				.map(context -> {
					InputStream inpStream = context.getAssets().open(CONTACTS_ASSERT);
					String gsonStr = ResourceUtils.streamToString(inpStream);
					return gson.fromJson(gsonStr, Contacts.class);
				});
	}
}
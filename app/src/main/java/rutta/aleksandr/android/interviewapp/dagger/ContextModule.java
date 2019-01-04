/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.dagger;

import android.content.Context;

import com.google.gson.Gson;
import rutta.aleksandr.android.interviewapp.network.NetworkInfo;
import rutta.aleksandr.android.interviewapp.network.NetworkInfoImpl;
import rutta.aleksandr.android.interviewapp.network.Retrofit;
import rutta.aleksandr.android.interviewapp.repository.Repo;
import rutta.aleksandr.android.interviewapp.repository.RepoImpl;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    @NonNull
    private Context context;

    public ContextModule(@NonNull Context context) {
        this.context = context;
    }

    @ContextScope
    @Provides
    @NonNull
    public Context getContext() {
        return context;
    }

    @ContextScope
    @Provides
    @NonNull
    public NetworkInfo getNetworkInfo(Context context) {
        return new NetworkInfoImpl(context);
    }

    @ContextScope
    @Provides
    @NonNull
    public Repo getRepo(Context context
            , NetworkInfo networkInfo
            , Retrofit retrofit
            , Gson gson) {
        return new RepoImpl(context, networkInfo, retrofit, gson);
    }
}
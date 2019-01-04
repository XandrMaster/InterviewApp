/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import rutta.aleksandr.android.interviewapp.network.Retrofit;
import rutta.aleksandr.android.interviewapp.network.RetrofitImpl;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @AppScope
    @Provides
    @NonNull
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    @AppScope
    @Provides
    @NonNull
    public Retrofit getRetrofit() {
        return new RetrofitImpl();
    }
}
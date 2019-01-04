/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.dagger;

import com.google.gson.Gson;
import rutta.aleksandr.android.interviewapp.network.Retrofit;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    Gson getGson();

    Retrofit getRetrofit();
}

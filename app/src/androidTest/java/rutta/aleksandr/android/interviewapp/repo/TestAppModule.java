/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.repo;

import rutta.aleksandr.android.interviewapp.dagger.AppModule;
import rutta.aleksandr.android.interviewapp.network.Retrofit;
import rutta.aleksandr.android.interviewapp.network.RetrofitImpl;

import org.mockito.Mockito;

import androidx.annotation.NonNull;

public class TestAppModule extends AppModule {

    @NonNull
    @Override
    public Retrofit getRetrofit() {
        return Mockito.mock(RetrofitImpl.class);
    }
}

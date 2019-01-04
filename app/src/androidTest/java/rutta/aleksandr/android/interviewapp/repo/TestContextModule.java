/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.repo;

import android.content.Context;

import rutta.aleksandr.android.interviewapp.dagger.ContextModule;
import rutta.aleksandr.android.interviewapp.network.NetworkInfo;
import rutta.aleksandr.android.interviewapp.network.NetworkInfoImpl;

import org.mockito.Mockito;

import androidx.annotation.NonNull;

public class TestContextModule extends ContextModule {

    public TestContextModule(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public NetworkInfo getNetworkInfo(Context context) {
        return Mockito.mock(NetworkInfoImpl.class);
    }
}

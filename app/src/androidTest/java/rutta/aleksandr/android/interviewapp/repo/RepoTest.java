/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.repo;

import android.content.Context;

import rutta.aleksandr.android.interviewapp.dagger.AppComponent;
import rutta.aleksandr.android.interviewapp.dagger.ContextComponent;
import rutta.aleksandr.android.interviewapp.dagger.DaggerAppComponent;
import rutta.aleksandr.android.interviewapp.dagger.DaggerContextComponent;
import rutta.aleksandr.android.interviewapp.dagger.DaggerManager;
import rutta.aleksandr.android.interviewapp.domain.Contacts;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class RepoTest {

    private ContextComponent contextComponent;
    private ContextComponent mockContextComponent;
    private AppComponent mockAppComponent;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        contextComponent = DaggerManager.builder()
                .buildContext(context)
                .getContextComponent();

        mockAppComponent = DaggerAppComponent.builder()
                .appModule(new TestAppModule())
                .build();

        mockContextComponent = DaggerContextComponent.builder()
                .appComponent(mockAppComponent)
                .contextModule(new TestContextModule(context))
                .build();
    }

    @Test
    public void repoTest() {
        List<Contacts> values = contextComponent.getRepo()
                .getContacts()
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors()
                .values();

        assertNotNull(values);
        assertTrue(values.size() > 0);
        Contacts item = values.get(0);
        assertNotNull(item);
        assertNotNull(item.getGroups());
        assertTrue(item.getGroups().size() > 0);
    }

    @Test
    public void repoTestWithNetwException() {
        when(mockAppComponent.getRetrofit().getContactsFromNetwork())
                .thenReturn(Single.error(new Exception("Mocked test exception for emulate bad request to server.")));
        when(mockContextComponent.getNetworkInfo().isNetworkEnable())
                .thenReturn(Single.just(true));

        List<Contacts> values = mockContextComponent.getRepo()
                .getContacts()
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors()
                .values();

        assertNotNull(values);
        assertTrue(values.size() > 0);
        Contacts item = values.get(0);
        assertNotNull(item);
        assertNotNull(item.getGroups());
        assertTrue(item.getGroups().size() > 0);
    }
}
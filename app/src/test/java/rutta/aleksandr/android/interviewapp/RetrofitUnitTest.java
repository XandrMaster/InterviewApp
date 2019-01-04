/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp;

import rutta.aleksandr.android.interviewapp.dagger.AppComponent;
import rutta.aleksandr.android.interviewapp.dagger.DaggerManager;
import rutta.aleksandr.android.interviewapp.domain.Contacts;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RetrofitUnitTest {

    private AppComponent appComponent;

    @Before
    public void setUp() throws Exception {
        appComponent = DaggerManager.builder().getAppComponent();
    }

    @Test
    public void retrofitTest() {

        List<Contacts> values = appComponent.getRetrofit()
                .getContactsFromNetwork()
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
        // TODO: 12/16/18
//        assertTrue(item.getGroups().size() > 0);
    }
}
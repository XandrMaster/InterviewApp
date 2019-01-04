/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp;

import rutta.aleksandr.android.interviewapp.dagger.AppComponent;
import rutta.aleksandr.android.interviewapp.dagger.DaggerManager;
import rutta.aleksandr.android.interviewapp.domain.Contacts;
import rutta.aleksandr.android.interviewapp.utils.ResourceUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class GsonUnitTest {

    private AppComponent appComponent;

    @Before
    public void setUp() throws Exception {
        appComponent = DaggerManager.builder().getAppComponent();
    }

    @Test
    public void parseTest() {

        String strGson = getGsonStr(Const.CONTACTS_ASSERT);
        Contacts contacts = appComponent.getGson().fromJson(strGson, Contacts.class);
        assertFalse(contacts.getGroups().isEmpty());

    }

    private String getGsonStr(String gsonFilename) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(gsonFilename);
        assertNotNull(inputStream);
        String str = ResourceUtils.streamToString(inputStream);
        assertNotNull(str);
        assertFalse(str.isEmpty());

        return str;
    }
}
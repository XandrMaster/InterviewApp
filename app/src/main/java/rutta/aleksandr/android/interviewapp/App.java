/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import rutta.aleksandr.android.interviewapp.dagger.DaggerManager;

public class App extends Application {

    private DaggerManager daggerManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        initDagger();
    }

    private void initDagger() {
        daggerManager = DaggerManager.builder()
                .buildContext(getApplicationContext());
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public DaggerManager getDaggerManager() {
        return daggerManager;
    }
}
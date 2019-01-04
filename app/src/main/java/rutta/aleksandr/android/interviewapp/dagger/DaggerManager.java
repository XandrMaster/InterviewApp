/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.dagger;

import android.content.Context;

import androidx.annotation.Nullable;
import io.reactivex.internal.functions.ObjectHelper;

public class DaggerManager {

    private AppComponent appComponent;
    private ContextComponent contextComponent;
    private AppModule appModule;
    private ContextModule contextModule;
    @Nullable
    private Context context;

    public static DaggerManager builder() {
        return new DaggerManager();
    }

    public synchronized AppComponent getAppComponent() {

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(getAppModule())
                    .build();
        }

        return appComponent;
    }

    public synchronized ContextComponent getContextComponent() throws NullPointerException {

        if (contextComponent == null) {
            ObjectHelper.requireNonNull(context, " Empty context ! Should invoke buildContext(...) before. ");
            contextComponent = DaggerContextComponent.builder()
                    .appComponent(getAppComponent())
                    .contextModule(getContextModule(context))
                    .build();
        }

        return contextComponent;
    }

    private AppModule getAppModule() {
        if (appModule == null) {
            appModule = new AppModule();
        }
        return appModule;
    }

    private ContextModule getContextModule(Context context) {
        if (contextModule == null) {
            contextModule = new ContextModule(context);
        }
        return contextModule;
    }

    public DaggerManager buildContext(Context applicationContext) {
        this.context = applicationContext;
        return this;
    }
}
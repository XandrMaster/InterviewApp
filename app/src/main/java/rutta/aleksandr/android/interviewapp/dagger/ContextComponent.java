/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.dagger;

import rutta.aleksandr.android.interviewapp.network.NetworkInfo;
import rutta.aleksandr.android.interviewapp.repository.Repo;

import dagger.Component;

@ContextScope
@Component(dependencies = {AppComponent.class},
        modules = {ContextModule.class})
public interface ContextComponent {

    NetworkInfo getNetworkInfo();

    Repo getRepo();
}
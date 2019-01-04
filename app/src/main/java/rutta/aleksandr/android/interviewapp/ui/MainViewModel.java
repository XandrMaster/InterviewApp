/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import android.app.Application;
import android.util.Pair;

import rutta.aleksandr.android.interviewapp.App;
import rutta.aleksandr.android.interviewapp.utils.StrUtils;
import rutta.aleksandr.android.interviewapp.dagger.ContextComponent;
import rutta.aleksandr.android.interviewapp.domain.Contacts;
import rutta.aleksandr.android.interviewapp.domain.ContactsResult;
import rutta.aleksandr.android.interviewapp.domain.Group;
import rutta.aleksandr.android.interviewapp.domain.Person;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static rutta.aleksandr.android.interviewapp.Const.TEXT_INPUT_DELAY_MS;

public class MainViewModel extends AndroidViewModel {

    private ContextComponent contextComponent;
    private MutableLiveData<ContactsResult> liveData;
    private Contacts contacts;
    private CompositeDisposable compositeDisposable;
    private PublishSubject<Pair<String, Contacts>> publishSubject;

    public MainViewModel(@NonNull Application application) {
        super(application);
        liveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        initDagger(application);
        initsearchSequenceSubject();
    }

    private void initsearchSequenceSubject() {
        publishSubject = PublishSubject.create();

        Disposable disposable = publishSubject
                .debounce(TEXT_INPUT_DELAY_MS, TimeUnit.MILLISECONDS)
                .map(pair -> cloneFilterContacts(pair.first, pair.second))
                .subscribeOn(Schedulers.computation())
                .subscribe(cont -> liveData.postValue(ContactsResult.success(cont)),
                        throwable -> liveData.postValue(ContactsResult.error(throwable)));

        compositeDisposable.add(disposable);
    }

    private void refreshContactsData() {
        if (liveData.hasObservers()) {
            compositeDisposable.add(contextComponent.getRepo()
                    .getContacts()
                    .doOnSubscribe(__ -> liveData.postValue(ContactsResult.loading()))
                    .subscribeOn(Schedulers.computation())
                    .subscribe(cont -> {
                                contacts = cont;
                                liveData.postValue(ContactsResult.success(cont));
                            }
                            ,
                            throwable -> liveData.postValue(ContactsResult.error(throwable))));
        }
    }

    void getContactsData() {
        ContactsResult currValue = liveData.getValue();
        if (currValue == null) {
            refreshContactsData();
        }
    }

    private void initDagger(Application application) {
        contextComponent = ((App) application).getDaggerManager()
                .getContextComponent();
    }

    MutableLiveData<ContactsResult> getLiveData() {
        return liveData;
    }

    public void searchOnTextChanged(CharSequence text) {
        if (contacts != null && text != null && liveData.hasObservers()) {
            publishSubject.onNext(new Pair<>(text.toString(), contacts));
        }
    }

    private boolean filterSearch(@NonNull String searchText, Person person) {
        if (!searchText.isEmpty() && person != null) {
            return StrUtils.contains(person.getFirstName(), searchText)
                    || StrUtils.contains(person.getLastName(), searchText);
        }
        return true;
    }

    @NonNull
    private Contacts cloneFilterContacts(@NonNull String searchText,
                                         @NonNull Contacts contacts) {

        Contacts result = new Contacts();
        if (contacts.isValid()) {
            for (Group group : contacts.getGroups()) {
                Group newGroup = new Group();
                for (Person person : group.getPeople()) {
                    if (filterSearch(searchText, person)) {
                        newGroup.getPeople().add(person);
                    }
                }
                if (newGroup.getPeople().size() > 0 || searchText.isEmpty()) {
                    newGroup.setGroupName(group.getGroupName());
                    result.getGroups().add(newGroup);
                }
            }
        }
        return result;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (publishSubject != null
                && !publishSubject.hasComplete()) {
            publishSubject.onComplete();
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.domain;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static rutta.aleksandr.android.interviewapp.domain.Status.ERROR;
import static rutta.aleksandr.android.interviewapp.domain.Status.LOADING;
import static rutta.aleksandr.android.interviewapp.domain.Status.SUCCESS;

public class ContactsResult {

    public final Status status;

    @Nullable
    public final List<ExpandableGroup> groups;

    @Nullable
    public final Throwable error;

    private ContactsResult(Status status, Contacts data, Throwable error) {
        this.status = status;
        this.groups = convertToGroup(data);
        this.error = error;
    }

    public static ContactsResult loading() {
        return new ContactsResult(LOADING, null, null);
    }

    public static ContactsResult success(@NonNull Contacts data) {
        return new ContactsResult(SUCCESS, data, null);
    }

    public static ContactsResult error(@NonNull Throwable error) {
        return new ContactsResult(ERROR, null, error);
    }

    private List<ExpandableGroup> convertToGroup(Contacts data) {
        List<ExpandableGroup> expGroups = new ArrayList<>();
        if (data != null) {
            for (Group item : data.getGroups()) {
                ExpandableGroup<Person> group = new ExpandableGroup<>(item.getGroupName()
                        , item.getPeople());
                expGroups.add(group);
            }
        }
        return expGroups;
    }
}
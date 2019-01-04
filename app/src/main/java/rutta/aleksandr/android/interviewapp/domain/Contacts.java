/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.domain;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Contacts {

    private List<Group> groups = new ArrayList<>();

    @NonNull
    public List<Group> getGroups() {
        return groups == null ? groups = new ArrayList<>() : groups;
    }

    public boolean isValid() {
        if (groups != null) {
            for (Group item : groups) {
                if (item.isValid()) {
                    return true;
                }
            }
        }

        return false;
    }
}
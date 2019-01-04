/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.domain;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Group {

    private String groupName = "";

    private List<Person> people = new ArrayList<>();

    @NonNull
    public String getGroupName() {
        return groupName == null ? "" : groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @NonNull
    public List<Person> getPeople() {
        return people == null ? people = new ArrayList<>() : people;
    }

    public boolean isValid() {
        return groupName != null
                && people != null;
    }
}
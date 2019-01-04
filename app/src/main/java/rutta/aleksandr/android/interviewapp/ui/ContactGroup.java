/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import rutta.aleksandr.android.interviewapp.domain.Person;

import java.util.List;

public class ContactGroup extends ExpandableGroup {

    public ContactGroup(String title, List<Person> items) {
        super(title, items);
    }

}

/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import rutta.aleksandr.android.interviewapp.databinding.GroupRecyclerItemBinding;
import rutta.aleksandr.android.interviewapp.databinding.PersonRecyclerItemBinding;
import rutta.aleksandr.android.interviewapp.domain.Person;

import java.util.List;

public class ContactsAdapter extends ExpandableRecyclerViewAdapter<GroupHolder, ChildHolder> {

    private LayoutInflater inflater;

    public ContactsAdapter(List<ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public GroupHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        GroupRecyclerItemBinding binding = GroupRecyclerItemBinding.inflate(inflater, parent, false);
        return new GroupHolder(binding);
    }

    @Override
    public ChildHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        PersonRecyclerItemBinding binding = PersonRecyclerItemBinding.inflate(inflater, parent, false);
        return new ChildHolder(binding);
    }

    @Override
    public void onBindChildViewHolder(ChildHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        List<Person> childItems = group.getItems();
        if (childItems != null && !childItems.isEmpty()) {
            holder.bind(childItems.get(childIndex));
        }
    }

    @Override
    public void onBindGroupViewHolder(GroupHolder holder, int flatPosition, ExpandableGroup group) {
        holder.bind(group);
    }
}
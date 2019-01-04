/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import rutta.aleksandr.android.interviewapp.databinding.GroupRecyclerItemBinding;

class GroupHolder extends GroupViewHolder {

    private final GroupRecyclerItemBinding binding;

    GroupHolder(GroupRecyclerItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(ExpandableGroup group) {
        binding.setGroup(group);
    }

    @Override
    public void expand() {
        super.expand();
        binding.setIsExpanded(true);
    }

    @Override
    public void collapse() {
        super.collapse();
        binding.setIsExpanded(false);
    }
}
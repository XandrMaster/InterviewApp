/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import rutta.aleksandr.android.interviewapp.databinding.PersonRecyclerItemBinding;
import rutta.aleksandr.android.interviewapp.domain.Person;

class ChildHolder extends ChildViewHolder {

    private final PersonRecyclerItemBinding binding;

    ChildHolder(PersonRecyclerItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Person person) {
        binding.setPerson(person);
    }
}
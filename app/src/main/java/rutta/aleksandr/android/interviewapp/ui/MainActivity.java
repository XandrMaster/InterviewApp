/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import rutta.aleksandr.android.interviewapp.R;
import rutta.aleksandr.android.interviewapp.databinding.ActivityMainBinding;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.getLiveData().observe(this, contactsResult -> {
            switch (contactsResult.status) {
                case ERROR:
                    Logger.e(contactsResult.error.toString());
                    break;
                case LOADING:
                    Logger.i("Loading data ...");
                    break;
                case SUCCESS:
                    updateContacts(contactsResult.groups);
                    break;

            }
        });
        viewModel.getContactsData();
    }

    private void updateContacts(List<ExpandableGroup> groups) {
        binding.recyclerView.setAdapter(new ContactsAdapter(groups));
    }
}
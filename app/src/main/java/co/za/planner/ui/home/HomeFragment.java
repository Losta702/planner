package co.za.planner.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import co.za.planner.MainActivity;
import com.example.planner.R;
import co.za.planner.dao.DailyPlanDAO;
import com.example.planner.databinding.FragmentHomeBinding;
import co.za.planner.dto.TodoItem;

import java.util.Iterator;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainActivity mainActivity;

    private Button deleteButton;

    private DailyPlanDAO dailyPlanDAO;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        mainActivity = (MainActivity) requireActivity();
        Button deleteButton = root.findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCheckedItems();
                mainActivity.refreshData();
                updateDeleteButtonVisibility();

            }
        });

        if (mainActivity.getTodoList() != null && !mainActivity.getTodoList().isEmpty()) {
            deleteButton.setVisibility(View.VISIBLE);
        } else {
            deleteButton.setVisibility(View.GONE);
        }
        dailyPlanDAO = new DailyPlanDAO(mainActivity.getContext());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.refreshData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void deleteCheckedItems() {
        Iterator<TodoItem> iterator = mainActivity.getTodoList().iterator();
        while (iterator.hasNext()) {
            TodoItem todoItem = iterator.next();
            if (todoItem.isChecked()) {
                dailyPlanDAO.deleteDailyPlan(todoItem.getId());
                iterator.remove();
            }
        }
        mainActivity.refreshData();


        updateDeleteButtonVisibility();
    }

    private void updateDeleteButtonVisibility() {
        if (mainActivity.getTodoList() != null && !mainActivity.getTodoList().isEmpty()) {
            deleteButton.setVisibility(View.VISIBLE);
        } else {
            deleteButton.setVisibility(View.GONE);
        }
    }
}
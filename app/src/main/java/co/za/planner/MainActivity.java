package co.za.planner;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.planner.R;
import co.za.planner.dao.DailyPlanDAO;
import co.za.planner.dto.TodoItem;
import co.za.planner.adapter.TodoAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.databinding.ActivityMainBinding;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private RecyclerView recyclerView;

    private TodoAdapter adapter;

    private List<TodoItem> todoList;

    private DailyPlanDAO dailyPlanDAO;

    private boolean isLoaded = false;

    private Context context;

    @Override
    protected void onResume() {
        System.out.println("ola");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        context = this;
        dailyPlanDAO = new DailyPlanDAO(context);

    }

    public List<TodoItem> getTodoList () {
        return todoList;
    }

    public Context getContext() {
        return context;
    }

    private ArrayList<TodoItem> fetchUpdateList () {
        Cursor data = dailyPlanDAO.getAllDailyPlans();
        TodoItem toDoItem;
        ArrayList<TodoItem> temptodoList = new ArrayList<>();
        if (data.moveToFirst()) {
            do {
                int id = Integer.parseInt(data.getString(data.getColumnIndexOrThrow("id")));
                Date date = Date.valueOf(data.getString(data.getColumnIndexOrThrow("date")));
                String mustDo = data.getString(data.getColumnIndexOrThrow("must_do"));
                String appointment = data.getString(data.getColumnIndexOrThrow("appointment"));
                String toDoList = data.getString(data.getColumnIndexOrThrow("to_do_list"));
                String note = data.getString(data.getColumnIndexOrThrow("notes"));

                toDoItem = new TodoItem(id, date, mustDo, appointment, toDoList, note, false);
                temptodoList.add(toDoItem);
            }while (data.moveToNext()) ;
        }
        return temptodoList ;
}

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean refreshData() {

        if (adapter==null) {
            adapter = new TodoAdapter(fetchUpdateList());
            recyclerView.setAdapter(adapter);
            isLoaded = true;
            return true;
        } else {
            adapter.updateData(fetchUpdateList());
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
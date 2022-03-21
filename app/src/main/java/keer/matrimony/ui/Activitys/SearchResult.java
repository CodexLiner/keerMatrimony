package keer.matrimony.ui.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import keer.matrimony.R;
import keer.matrimony.databinding.ActivitySearchResultBinding;
import keer.matrimony.ui.dashboard.DashboardFragment;

public class SearchResult extends AppCompatActivity {
    ActivitySearchResultBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        ActionBar actionBar = getSupportActionBar();
         getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.nav_host_fragment_activity_home, new DashboardFragment(null , this))
        .commit();

        actionBar.setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Search Result's");
        }
        setContentView(binding.getRoot());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return false;
    }
    public void setActionBarTitle(String title){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(title);
        }
    }

}
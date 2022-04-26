package keer.matrimony.ui.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import keer.matrimony.R;
import keer.matrimony.databinding.ActivitySearchResultBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.dashboard.DashboardFragment;
import keer.matrimony.utils.MyException;

public class SearchResult extends AppCompatActivity {
    ActivitySearchResultBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
            ActionBar actionBar = getSupportActionBar();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_home, new DashboardFragment(CONSTANTS.SEARCHRESULT, this))
                    .commit();

            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Search Result's");
            }
            setContentView(binding.getRoot());
        }catch (Exception e){
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setActionBarTitle("Search Result's");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CONSTANTS.SEARCHRESULT = null;
    }
}
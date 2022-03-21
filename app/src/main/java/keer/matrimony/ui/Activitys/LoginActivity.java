package keer.matrimony.ui.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import keer.matrimony.R;
import keer.matrimony.UIFragments.LoginFragment;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.ContainerMain, LoginFragment.class, null)
                .commit();

    }
}
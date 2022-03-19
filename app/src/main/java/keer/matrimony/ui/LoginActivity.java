package keer.matrimony.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import keer.matrimony.R;
import keer.matrimony.UIFragments.LoginFragment;
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
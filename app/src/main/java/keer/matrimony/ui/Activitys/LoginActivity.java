package keer.matrimony.ui.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;

import keer.matrimony.R;
import keer.matrimony.UIFragments.LoginFragment;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.other.PermisionClass;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PermissionCheck();
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.ContainerMain, LoginFragment.class, null)
                .commit();

    }
    private void PermissionCheck() {
        if (PermisionClass.hasPermision(LoginActivity.this , PermisionClass.permisions)){
//            button2.setText("Enable Notification");

        }else{
            if (!PermisionClass.hasPermision(LoginActivity.this , PermisionClass.permisions)){
                ActivityCompat.requestPermissions(LoginActivity.this, PermisionClass.permisions, 0);

            }
        }
    }
}
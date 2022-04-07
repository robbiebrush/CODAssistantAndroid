package com.example.codassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.codassistant.Database.CreateUpdateFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceManager;

import com.example.codassistant.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavigationView navigationView;
    NavController navController;
    //Font flag
    public static int font = 0;

    //Stat filter flag
    public static int filter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets language
        /*
        if (sharedPreferences.getString("lang", "eng").equals("eng")) {
            setAppLocale("en");
            Log.d("help","made it eng");
            recreate();
        } else if (sharedPreferences.getString("lang" ,"eng").equals("esp")) {
            setAppLocale("es");
            Log.d("help","made it esp");
            recreate();
        }
        */

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_matches, R.id.nav_stats, R.id.nav_rosters)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_add_24);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDestination currentFragment = navController.getCurrentDestination();
                if (currentFragment.getId() == R.id.nav_matches) {
                    Bundle extra = new Bundle();
                    extra.putInt(CreateUpdateFragment.ACTION_TYPE,
                            CreateUpdateFragment.CREATE);
                    navController.navigate(R.id.nav_create_update, extra);
                }
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination,
                                             @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nav_matches) {
                    binding.appBarMain.fab.show();
                } else {
                    binding.appBarMain.fab.hide();
                }
            }
        });
    }

    private void setAppLocale(String code) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        config.setLocale(locale);
        res.updateConfiguration(config, dm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                navController.navigate(R.id.nav_settings);
                break;
            case R.id.credits:
                navController.navigate(R.id.nav_credits);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("lang")) {
            if (sharedPreferences.getString("lang", "eng").equals("eng")) {
                setAppLocale("en");
                Log.d("help", "made it eng");
            } else if (sharedPreferences.getString("lang", "eng").equals("esp")) {
                setAppLocale("es");
                Log.d("help", "made it esp");
            }
        }

        if (key.equals("font")) {
            if (sharedPreferences.getString("font", "default").equals("default")) {
                font = 0;
            } else if (sharedPreferences.getString("font", "default").equals("large")) {
                font = 1;
            }
        }
        if (key.equals("filter")) {
            if (sharedPreferences.getString("filter", "overall").equals("overall")) {
                filter = 0;
            } else if (sharedPreferences.getString("filter", "overall").equals("hp")) {
                filter = 1;
                Log.d("help","made it hp filter");
            } else if (sharedPreferences.getString("filter", "overall").equals("snd")) {
                filter = 2;
            } else if (sharedPreferences.getString("filter", "overall").equals("ctl")) {
                filter = 3;
            }
        }
    }

    @Override
    protected void onStart() {
        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        pref.registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
        pref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
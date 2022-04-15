package com.example.codassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.CalendarContract;
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
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.example.codassistant.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sharedPreferences.getString("lang", "en").equals("en")) {
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration config = res.getConfiguration();
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            config.setLocale(locale);
            res.updateConfiguration(config, dm);
        } else if (sharedPreferences.getString("lang" ,"en").equals("es")) {
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration config = res.getConfiguration();
            Locale locale = new Locale("es");
            Locale.setDefault(locale);
            config.setLocale(locale);
            res.updateConfiguration(config, dm);
        }

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
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDestination currentFragment = navController.getCurrentDestination();
                if (currentFragment.getId() == R.id.nav_matches) {
                    Bundle extra = new Bundle();
                    extra.putInt(CreateUpdateFragment.ACTION_TYPE,
                            CreateUpdateFragment.CREATE);
                    navController.navigate(R.id.nav_create_update, extra);
                } else if (currentFragment.getId() == R.id.nav_home) {
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, HomeFragment.titleStr)
                            .putExtra(CalendarContract.Events.EVENT_LOCATION, getApplicationContext().getResources().getString(R.string.tbd))
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, HomeFragment.start)
                            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, HomeFragment.end);
                    if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                        if (HomeFragment.start == 0 || HomeFragment.end == 0 || HomeFragment.titleStr.equals("")) {
                            Snackbar.make(view, "Select an event", Snackbar.LENGTH_SHORT).show();
                        } else {
                            startActivity(intent);
                        }
                    } else{
                        Snackbar.make(view, "No app installed", Snackbar.LENGTH_SHORT).show();
                    }
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
                    binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_add_24);
                    binding.appBarMain.fab.show();
                } else if (destination.getId() == R.id.nav_home){
                    binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_event_24);
                    binding.appBarMain.fab.show();
                } else {
                    binding.appBarMain.fab.hide();
                }
            }
        });
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
}
package com.ostrov.navigationdrawerexample1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer);

        // ActionBarDrawerToggle enables integration between drawer functionality and app bar framework.
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        // Adds the specified listener to the list of listeners that will be notified of drawer events.
        mDrawerLayout.addDrawerListener(mToggle);

        // Enabling action bar app icon and behaving it as toggle button.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nv);

        // Set a listener that will be notified when a menu item is selected.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });

        // Set default fragment
        selectItemDrawer(navigationView.getMenu().getItem(0));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Synchronize the indicator with the state of the linked DrawerLayout
        // after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    /** Call whenever navigation menu item is selected */
    private void selectItemDrawer(MenuItem item) {
        // Create selected Fragment
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.events:
                fragment = new EventsFragment();
                break;
            case R.id.search:
                fragment = new SearchFragment();
                break;
            case R.id.settings:
                fragment = new SettingsFragment();
                break;
            default:
                fragment = new DashboardFragment();
                break;
        }

        // FragmentManager - interface for interacting with Fragment objects inside of an Activity.
        // getSupportFragmentManager - return the FragmentManager for interacting with fragments associated with this activity.
        FragmentManager manager = getSupportFragmentManager();

        // beginTransaction - start a series of edit operations on the Fragments associated with this FragmentManager.
        manager.beginTransaction().replace(R.id.fl_content, fragment).commit();

        // Set the checked state.
        item.setChecked(true);

        // Change the title associated with this activity.
        setTitle(item.getTitle());

        // Close all currently open drawer views by animating them out of view.
        mDrawerLayout.closeDrawers();
    }

    // When navigation drawer icon is touched, onOptionsItemSelected callback method is called.
    // In this method, you need to call ActionBarDrawerToggle’s onOptionsItemSelected method.
    // This will handle drawer integration and returns true if selected item is drawer icon.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}

package app.managementapp.college.com.collegemanagement.management;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.adapters.MenuGridAdapter;

public class ManagementHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        // setting the required menus
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MenuGridAdapter mAdapter = new MenuGridAdapter(this, getMenuData(this));
        recyclerView.setAdapter(mAdapter);
    }


    public static List<app.managementapp.college.com.collegemanagement.model.MenuItem> getMenuData(Context ctx)  {
        List<app.managementapp.college.com.collegemanagement.model.MenuItem> items = new ArrayList<>();
        String s_name[] = ctx.getResources().getStringArray(R.array.menu_name);
        String s_date[] = ctx.getResources().getStringArray(R.array.groups_date);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.menu_photos);

        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(0, s_date[0], s_name[0], "", drw_arr.getResourceId(0, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(1, s_date[1], s_name[1], "", drw_arr.getResourceId(1, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(2, s_date[2], s_name[2], "", drw_arr.getResourceId(2, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(3, s_date[3], s_name[3], "", drw_arr.getResourceId(3, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(3, s_date[3], s_name[4], "", drw_arr.getResourceId(4, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(3, s_date[3], s_name[5], "", drw_arr.getResourceId(5, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(3, s_date[3], s_name[6], "", drw_arr.getResourceId(6, -1)));
        items.add(new app.managementapp.college.com.collegemanagement.model.MenuItem(3, s_date[3], s_name[7], "", drw_arr.getResourceId(7, -1)));

        return items;
    }

    public void clicked(String clickOn){
        Log.d("yyy", "clicked: " + clickOn);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.management_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

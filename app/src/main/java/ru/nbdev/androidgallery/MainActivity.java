package ru.nbdev.androidgallery;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private TextView imageDescription;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
        toolbarInit();
        fabInit();
        drawerInit();

        showFruits();
    }

    private void viewInit() {
        drawer = findViewById(R.id.drawer_layout);
        imageView = findViewById(R.id.image);
        imageDescription = findViewById(R.id.image_description);
    }

    private void toolbarInit() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void fabInit() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AppBarLayout appBarLayout = findViewById(R.id.app_bar);
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                    int fabTotalHeight = fab.getHeight() + ((CoordinatorLayout.LayoutParams) fab.getLayoutParams()).bottomMargin;
                    int appBarHeight = appBarLayout.getTotalScrollRange();
                    float fabMoveScale;
                    if (fabTotalHeight > appBarHeight) {
                        fabMoveScale = (float) fabTotalHeight / appBarHeight;
                    } else {
                        fabMoveScale = (float) appBarHeight / fabTotalHeight;
                    }
                    fab.setTranslationY(Math.abs(verticalOffset * fabMoveScale));
                }
            });
        } else {
            ((CoordinatorLayout.LayoutParams) fab.getLayoutParams()).setBehavior(new FabHideDownOnScroll());
        }
    }

    private void drawerInit() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_fruits:
                showFruits();
                break;

            case R.id.nav_vegetables:
                showVegetables();
                break;

            case R.id.nav_nature:
                showNature();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFruits() {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.fruits));
        imageDescription.setText(getResources().getText(R.string.fruits_text));
    }

    private void showVegetables() {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.vegetables));
        imageDescription.setText(getResources().getText(R.string.vegetables_text));
    }

    private void showNature() {
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.nature));
        imageDescription.setText(getResources().getText(R.string.nature_text));
    }
}

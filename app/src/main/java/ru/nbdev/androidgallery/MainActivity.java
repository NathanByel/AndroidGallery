package ru.nbdev.androidgallery;

import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import ru.nbdev.androidgallery.recycler.ItemDecorationAlbumColumns;
import ru.nbdev.androidgallery.recycler.RecyclerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInit();
        toolbarInit();
        fabInit();
        drawerInit();
        recyclerInit();

        showFruits();
    }

    private void viewInit() {
        drawer = findViewById(R.id.drawer_layout);
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

    private void recyclerInit() {
        final int GRID_SIZE = 2;
        final int GRID_SPACING = 50;

        recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, GRID_SIZE);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(getResources(), null);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new ItemDecorationAlbumColumns(GRID_SPACING, GRID_SIZE));
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
        List<ImageItem> imagesList = new ArrayList<>();
        imagesList.add(new ImageItem(R.drawable.fruits1,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits2,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits3,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits4,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits5,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits1,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits2,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits3,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits4,"text"));
        imagesList.add(new ImageItem(R.drawable.fruits5,"text"));
        adapter.swapData(imagesList);
    }

    private void showVegetables() {
        List<ImageItem> imagesList = new ArrayList<>();
        imagesList.add(new ImageItem(R.drawable.vegetables1,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables2,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables3,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables4,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables5,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables1,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables2,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables3,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables4,"text"));
        imagesList.add(new ImageItem(R.drawable.vegetables5,"text"));
        adapter.swapData(imagesList);
    }

    private void showNature() {
        Intent intent = new Intent(MainActivity.this, NatureActivity.class);
        startActivity(intent);
    }
}

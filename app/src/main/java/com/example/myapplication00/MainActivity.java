package com.example.myapplication00;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.myapplication00.Adapter.CustomExpandableListAdapter;
import com.example.myapplication00.Adapter.ShortSummary;
import com.example.myapplication00.Adapter.TitleListAdapter;
import com.example.myapplication00.helper.Title;
import com.example.myapplication00.helper.FragmentNavigationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    private ArrayList<Title> mTitleList;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String, List<String>> lstChild;
    private FragmentNavigationManager navigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init View
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = (ExpandableListView) findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getmInstance(this);

        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header , null , false);
        expandableListView.addHeaderView(listHeaderView);

        genDrawerData();
        genOptionsData();

        addDrawersItem();
        setupDrawer();

        if(savedInstanceState == null)
            selectFirstItemAsDefault();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("La aplicación");
    }



    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
        if(navigationManager != null)
        {
            String firstItem = lstChild.get(lstTitle.get(0)).get(0);
            navigationManager.showFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);

        }
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void addDrawersItem() {
        adapter = new CustomExpandableListAdapter(this , lstTitle , lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener((groupPosition) ->
            {
                //set title for Toolbar when open
                getSupportActionBar().setTitle(lstTitle.get(groupPosition).toString());
            }
        );

        expandableListView.setOnGroupCollapseListener(groupPosition -> {
            getSupportActionBar().setTitle("La aplicación");
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Change fragment when click on item
                String selectedItem = ((List) (lstChild.get(lstTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);
                if(items[groupPosition].equals(lstTitle.get(groupPosition))) {
                    navigationManager.showFragment(selectedItem);
                }
                else
                    throw new IllegalArgumentException("Not supported fragment");
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }


    private void genDrawerData() {
        List<String> title = Arrays.asList("English Renaissance" , "Romanticism" , "20th century");
        List<String> childItem1 = Arrays.asList("William Shakespeare" , "Edmund Spenser" , "John Donne");
        List<String> childItem2 = Arrays.asList("Robert Burns" , "Samuel Taylor Coleridge" , "Sir Walter Scott");
        List<String> childItem3 = Arrays.asList("George Orwell" , "Rudyard Kipling" , "H. G. Wells");

        lstChild = new LinkedHashMap<>();
        lstChild.put(title.get(0) , childItem1);
        lstChild.put(title.get(1) , childItem2);
        lstChild.put(title.get(2) , childItem3);

        lstTitle = new ArrayList<>(lstChild.keySet());
    }

    private void genOptionsData() {
        ArrayList<Title> titles1 = new ArrayList<>();
        ArrayList<Title> titles2 = new ArrayList<>();
        ArrayList<Title> titles3 = new ArrayList<>();
        ArrayList<Title> titles4 = new ArrayList<>();
        ArrayList<Title> titles5 = new ArrayList<>();
        ArrayList<Title> titles6 = new ArrayList<>();
        ArrayList<Title> titles7 = new ArrayList<>();
        ArrayList<Title> titles8 = new ArrayList<>();
        ArrayList<Title> titles9 = new ArrayList<>();


        //insert elements into ArrayList, then into the NavigationManager
        titles1.add(new Title("Romeo and Juliet", R.drawable.image_romeo_and_juliet));
        titles1.add(new Title("Hamlet", R.drawable.image_hamlet));
        navigationManager.setContent(lstChild.get("English Renaissance").get(0), titles1);

        //repeat the process

        titles2.add(new Title("The Faerie Queene", R.drawable.image_the_faerie_queene));
        navigationManager.setContent(lstChild.get("English Renaissance").get(1), titles2);

        titles3.add(new Title("Biathanatos", R.drawable.image_biathanatos));
        navigationManager.setContent(lstChild.get("English Renaissance").get(2), titles3);

        titles4.add(new Title("Auld Lang Syne", R.drawable.image_auld_and_syne));
        navigationManager.setContent(lstChild.get("Romanticism").get(0), titles4);

        titles5.add(new Title("The Rime of the Ancient Mariner", R.drawable.image_the_rime_of_the_ancient_mariner));
        navigationManager.setContent(lstChild.get("Romanticism").get(1), titles5);

        titles6.add(new Title("The Lady of the Lake", R.drawable.image_the_lady_of_the_lake));
        navigationManager.setContent(lstChild.get("Romanticism").get(2), titles6);

        titles7.add(new Title("Nineteen Eighty-Four", R.drawable.image_nineteen_eighty_four));
        navigationManager.setContent(lstChild.get("20th century").get(0), titles7);

        titles8.add(new Title("The Jungle Book", R.drawable.image_the_jungle_book));
        navigationManager.setContent(lstChild.get("20th century").get(1), titles8);

        titles9.add(new Title("The Time Machine", R.drawable.image_the_time_machine));
        navigationManager.setContent(lstChild.get("20th century").get(2), titles9);
    }

    private void initItems() {
        items = new String[]{"English Renaissance" , "Romanticism" , "20th century"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
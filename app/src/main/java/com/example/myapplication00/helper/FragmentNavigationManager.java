package com.example.myapplication00.helper;

import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication00.Adapter.ShortSummary;
import com.example.myapplication00.Adapter.TitleListAdapter;
import com.example.myapplication00.Interface.NavigationManager;
import com.example.myapplication00.MainActivity;
import com.example.myapplication00.R;
import com.example.myapplication00.fragments.FragmentContent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class FragmentNavigationManager implements NavigationManager {
    private static FragmentNavigationManager mInstance;

    private Map<String , ArrayList<Title>> listContentByKey = new LinkedHashMap<>();;
    private FragmentManager mFragmentManager;
    private MainActivity mainActivity;

    private RecyclerView mRecyclerView;
    private TitleListAdapter mTitleAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static  FragmentNavigationManager getmInstance(MainActivity mainActivity)
    {
        if(mInstance == null){
            mInstance = new FragmentNavigationManager();
        }
        mInstance.configure(mainActivity);

        return mInstance;
    }


    public void setContent(String keyTitle , ArrayList<Title> content)
    {
        listContentByKey.put(keyTitle, content);
    }


    private void configure(MainActivity mainActivity){
        //the original is like this, but I suspect it should be : this.mainActivity = ...
        this.mainActivity = mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }


    @Override
    public void showFragment(String author) {

        ArrayList<Title> titles = listContentByKey.get(author);

        buildRecyclerView(titles,author);


    }

    private void buildRecyclerView(ArrayList<Title> titles, String author) {
        mRecyclerView = (RecyclerView) mainActivity.findViewById(R.id.container_layout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mainActivity);
        mTitleAdapter = new TitleListAdapter(titles);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemClickListener(new TitleListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startNewActivity(author,position, titles.get(position).getmTitle());
                //changeItem(position, "Clicked");
            }
        });
    }

    private void startNewActivity(String author, int position, String title) {
        final String Message = "MESSAGE";
        Toast toast = Toast.makeText(mainActivity, author, Toast.LENGTH_SHORT);

        Intent intent = new Intent(mainActivity, ShortSummary.class);
        String mes = null;
        switch(author){
            case "William Shakespeare":

                switch(position){
                    case 0:
                        mes = mainActivity.getString(R.string.summary_Romeo_and_Juliet);
                        break;
                    case 1:
                        mes = mainActivity.getString(R.string.summary_Hamlet);
                        break;
                }
                break;
            case "Edmund Spenser":
                toast.show();
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_The_Faerie_Queene);
                        break;
                }
                break;
            case "John Donne":
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_Biathanathos);
                        break;
                }
                break;
            case "Robert Burns":
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_Auld_Lang_Syne);
                        break;
                }
                break;
            case "Samuel Taylor Coleridge" :
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_The_Rime_of_the_Ancient_Mariner);
                        break;
                }
                break;
            case "Sir Walter Scott":
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_The_Lady_of_the_Lake);
                        break;
                }
                break;
            case "George Orwell" :
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_Nineteen_Eighty_Four);
                        break;
                }
                break;
            case "Rudyard Kipling" :
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_The_Jungle_Book);
                        break;
                }
                break;
            case "H. G. Wells":
                switch(position) {
                    case 0:
                        mes = mainActivity.getString(R.string.summary_The_Time_Machine);
                        break;
                }
                break;
        }

        intent.putExtra(Message,mes);
        intent.putExtra("TITLE", title);
        mainActivity.startActivity(intent);
        mTitleAdapter.notifyDataSetChanged();
    }


}

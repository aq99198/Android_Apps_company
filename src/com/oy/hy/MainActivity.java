package com.oy.hy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    
    private ListView transaction_ListView = null;
    private ListView concept_ListView = null;
    private ListView home_ListView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        
        mTitle = getTitle();
        
    	transaction_ListView= (ListView) findViewById(R.id.transaction_listView);
    	concept_ListView = (ListView) findViewById(R.id.concept_listView);
    	home_ListView = (ListView) findViewById(R.id.listView1);
        
    	// Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }
    
    private List<Map<String, Object>> displaything(int num) {
    	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		switch(num) {
			case 1:
				 map.put("title", getString(R.string.homepage_thing1));
				 map.put("info", getString(R.string.homepage_thing2));
				 map.put("img", R.drawable.ic_launcher);
				 list.add(map);
				break;
			case 2:
				 map.put("title", getString(R.string.introduce));
				 map.put("info", getString(R.string.introduce_thing));
				 map.put("img", R.drawable.pic_company);
				 list.add(map);
				break;
			case 3:
				 map.put("title", getString(R.string.ouritem1));
				 list.add(map);	
				 map = new HashMap<String, Object>();
				 map.put("title", getString(R.string.ouritem2));
				 list.add(map);
				 map = new HashMap<String, Object>();
				 map.put("title", getString(R.string.ouritem3));
				 list.add(map);	
				 map = new HashMap<String, Object>();
				 map.put("title", getString(R.string.ouritem4));
				 list.add(map);
				break;
			case 4:
				 map.put("title", getString(R.string.worklist));
				 list.add(map);	
				 map = new HashMap<String, Object>();
				 map.put("title", getString(R.string.lifelist));
				 list.add(map);	
				break;
			default:
				break;
		}
		return list;
	 }
    public void onSectionAttached(int number) {
    	SimpleAdapter adapter=null;
        home_ListView.setVisibility(View.INVISIBLE);
        transaction_ListView.setVisibility(View.INVISIBLE);
        concept_ListView.setVisibility(View.INVISIBLE);
        switch (number) {
            case 1:
            	adapter = new SimpleAdapter(this,displaything(1),R.layout.company_home,
        		        new String[]{"title","info","img"},
        		        new int[]{R.id.home_t1,R.id.home_t2,R.id.home_img});
            	home_ListView.setAdapter(adapter);
                mTitle = getString(R.string.homepage);
                home_ListView.setVisibility(View.VISIBLE);
                break;
            case 2:
            	adapter = new SimpleAdapter(this,displaything(2),R.layout.company_introduce,
        		        new String[]{"title","info","img"},
        		        new int[]{R.id.introduce_t1,R.id.introduce_t2,R.id.introduce_img});
            	home_ListView.setAdapter(adapter);
                mTitle = getString(R.string.introduce);
                home_ListView.setVisibility(View.VISIBLE);
                break;
            case 3:
            	adapter = new SimpleAdapter(this,displaything(3),R.layout.company_concept,
        		        new String[]{"title"},
        		        new int[]{R.id.concept_tt});
            	concept_ListView.setAdapter(adapter);
        		concept_ListView.setOnItemClickListener(new Concept_ListClickListener());
        		mTitle = getString(R.string.concept);
        		concept_ListView.setVisibility(View.VISIBLE);
                break;
            case 4:
            	adapter = new SimpleAdapter(this,displaything(4),R.layout.company_transaction,
        		        new String[]{"title"},
        		        new int[]{R.id.transaction_t1});
            	transaction_ListView.setAdapter(adapter);
            	
            	transaction_ListView.setOnItemClickListener(new Transaction_ListClickListener());
            	mTitle = getString(R.string.transaction);
            	transaction_ListView.setVisibility(View.VISIBLE);
                break;
        }
    }

    class Transaction_ListClickListener implements OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0:
					
					break;
				case 1:
					Intent intent = new Intent();
			        //intent.putExtra("name","LeiPei");    
			        intent.setClass(MainActivity.this, LifeplanActivity.class);
			        MainActivity.this.startActivity(intent);
					break;
				default:
					break;
				}
	    }
    }
    
    class Concept_ListClickListener implements OnItemClickListener{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
					Intent intent = new Intent();
			        /* 指定intent要启动的类 */
			        Bundle bundle = new Bundle();
			        bundle.putInt("item_num",position);
			        intent.putExtras(bundle);
			        intent.setClass(MainActivity.this, ItemShowActivity.class);
			        /* 启动一个新的Activity */
			        MainActivity.this.startActivity(intent);
			}
    	}

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}

package movies.flag.pt.moviesapp.screens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import movies.flag.pt.moviesapp.R;
import movies.flag.pt.moviesapp.fragments.BaseFragment;
import movies.flag.pt.moviesapp.fragments.HomeFragment;
import movies.flag.pt.moviesapp.fragments.MoviesFragment;
import movies.flag.pt.moviesapp.fragments.SearchFragment;
import movies.flag.pt.moviesapp.fragments.SeriesFragment;


public class MainActivityScreen extends Screen {

    private static final int FRAGMENT_CONTAINER_ID = R.id.main_fragment_container;

    private DrawerLayout drawerLayout;
    private TextView homeOption;
    private TextView moviesOption;
    private TextView seriesOption;
    private EditText searchOption;
    private ImageButton searchArrow;
    private TextView toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_fragment);

        findViews();
        addListeners();
    }

    private void findViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_activity_fragment);
        homeOption = (TextView) findViewById(R.id.main_home_txt);
        moviesOption = (TextView) findViewById(R.id.main_movies_txt);
        seriesOption = (TextView) findViewById(R.id.main_series_txt);
        searchOption = (EditText) findViewById(R.id.main_search_txt);
        searchArrow = (ImageButton) findViewById(R.id.main_search_arrow);
        toolbar = (TextView) findViewById(R.id.main_menu_toolbar);
    }

    private void addListeners() {

        homeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(HomeFragment.newInstance());
                closeMenu();
            }
        });

        moviesOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(MoviesFragment.newInstance());
                closeMenu();
            }
        });

        seriesOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(SeriesFragment.newInstance());
                closeMenu();
            }
        });

        searchArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = searchOption.getText().toString();
                addFragment(SearchFragment.newInstance(text));
                closeMenu();
                hideKeyboard();

            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    private void closeMenu(){
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void openMenu(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchOption.getWindowToken(), 0);
    }

    public void addFragment(BaseFragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(FRAGMENT_CONTAINER_ID, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}

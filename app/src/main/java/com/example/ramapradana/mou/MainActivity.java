package com.example.ramapradana.mou;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramapradana.mou.FavoriteFragment;
import com.example.ramapradana.mou.PopularFragment;
import com.example.ramapradana.mou.R;
import com.example.ramapradana.mou.TopRatedFragment;

import java.nio.file.attribute.FileOwnerAttributeView;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    setTitle("Popular");
                    PopularFragment popularFragment = new PopularFragment();
                    FragmentTransaction fragmentTransaction_pop = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_pop.replace(R.id.frame_fragment, popularFragment, "Popular");
                    fragmentTransaction_pop.commit();
                    return true;
                case R.id.navigation_dashboard:
                    setTitle("Top Rated");
                    TopRatedFragment topRatedFragment= new TopRatedFragment();
                    FragmentTransaction fragmentTransaction_top = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_top.replace(R.id.frame_fragment, topRatedFragment, "Top Rated");
                    fragmentTransaction_top.commit();
                    return true;
                case R.id.navigation_notifications:
                    setTitle("My Favorite");
                    FavoriteFragment favoriteFragment= new FavoriteFragment();
                    FragmentTransaction fragmentTransaction_fav = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_fav.replace(R.id.frame_fragment, favoriteFragment, "Top Rated");
                    fragmentTransaction_fav.commit();
                    return true;
                case  R.id.navigation_about:
                    setTitle("About");
                    AboutFragment aboutFragment= new AboutFragment();
                    FragmentTransaction fragmentTransaction_about = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_about.replace(R.id.frame_fragment, aboutFragment, "Top Rated");
                    fragmentTransaction_about.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Popular");
        PopularFragment fragment = new PopularFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment, fragment, "Popular");
        fragmentTransaction.commit();
    }

}

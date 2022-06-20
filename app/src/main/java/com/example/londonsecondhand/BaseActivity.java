package com.example.londonsecondhand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.londonsecondhand.Adapter.FragmentAdapter;
import com.example.londonsecondhand.Fragment.AccountFragment;
import com.example.londonsecondhand.Fragment.HomeFragment;
import com.example.londonsecondhand.Fragment.PublishFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private BottomNavigationView bntView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //不显示标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        bntView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(6);
        List<Fragment> allFragments = new ArrayList<>();
        allFragments.add(new HomeFragment());
        allFragments.add(new PublishFragment());
        allFragments.add(new AccountFragment());

        FragmentAdapter adapter = new FragmentAdapter(allFragments,getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // 点击不同底部按钮切换到不同页面
        bntView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuId = item.getItemId();
                switch (menuId) {
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.fragment_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.fragment_publish:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.fragment_user:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        //跳转到不同页面之后，切换底部按钮颜色
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                bntView.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
}
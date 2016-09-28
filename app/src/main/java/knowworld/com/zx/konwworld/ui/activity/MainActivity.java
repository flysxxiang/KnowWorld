package knowworld.com.zx.konwworld.ui.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import knowworld.com.zx.konwworld.AppManager;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.base.BaseActivity;
import knowworld.com.zx.konwworld.ui.fragment.NewsFragment;
import knowworld.com.zx.konwworld.ui.fragment.WeiXinFragment;
import knowworld.com.zx.konwworld.ui.fragment.ZhiHuFragment;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    private NewsFragment newsFragment = null;
    private ZhiHuFragment zhiHuFragment = null;
    private WeiXinFragment weiXinFragment = null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.main_navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle(R.string.app_name_cn);//设置主标题
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(mToolbar);

        newsFragment = new NewsFragment();
        FragmentTransaction  mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.add(R.id.main_content, newsFragment).commit();

        // NavigationView菜单项的点击事件
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected (MenuItem menuItem) {
                        FragmentTransaction  mTransaction = getSupportFragmentManager().beginTransaction();
                        hideFragments(mTransaction);
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_news:
                                if (newsFragment == null) {
                                    newsFragment = new NewsFragment();
                                    mTransaction.add(R.id.main_content, newsFragment);
                                } else {
                                    mTransaction.show(newsFragment);
                                }
                                // fragmentManager.beginTransaction().replace(R.id.main_content, newsFragment).commit();
                                mToolbar.setTitle(R.string.navigation_news);

                                break;
                            case R.id.navigation_zhihu:
                                if (zhiHuFragment == null) {
                                    zhiHuFragment = new ZhiHuFragment();
                                    mTransaction.add(R.id.main_content, zhiHuFragment);
                                } else {
                                    mTransaction.show(zhiHuFragment);
                                }
                                // fragmentManager.beginTransaction().replace(R.id.main_content, zhiHuFragment).commit();
                                mToolbar.setTitle(R.string.navigation_zhihu);

                                break;
                            case R.id.navigation_weixin:
                                if (weiXinFragment == null) {
                                    weiXinFragment = new WeiXinFragment();
                                    mTransaction.add(R.id.main_content, weiXinFragment);
                                } else {
                                    mTransaction.show(weiXinFragment);
                                }
                                // fragmentManager.beginTransaction().replace(R.id.main_content, weiXinFragment).commit();
                                mToolbar.setTitle(R.string.navigation_weixin);

                                break;
                        }

                        mTransaction.commit();
                        // 设置菜单被选
                        menuItem.setChecked(true);
                        closeDrawer();

                        return true;
                    }
                }

        );

        // ActionBarDrawerToggle：监听打开关闭Drawer，ToolBar图片有动画效果
        // 构造函数：抽屉activity，DrawerLayout，抽屉开关的图片指示器，一个描述抽屉打开的string(用户友好)，一个描述抽屉关闭的string
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_menu, R.string.app_name, R.string.app_name) {

            // 当抽屉完全关闭时调用
            public void onDrawerClosed (View view) {
                super.onDrawerClosed(view);
            }

            // 当抽屉完全打开时调用
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  // 导航菜单
                openDrawer();
                return true;
            case R.id.action_settings:

                break;
            case R.id.action_about:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void openDrawer () {
        if (mDrawerLayout == null)
            return;
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    protected void closeDrawer () {
        if (mDrawerLayout == null)
            return;
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    protected ActionBar getActionBarToolbar () {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return getSupportActionBar();
    }


    private void rep (Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof NewsFragment) {
                    newsFragment = (NewsFragment) fragment;
                } else if (fragment instanceof ZhiHuFragment) {
                    zhiHuFragment = (ZhiHuFragment) fragment;
                } else if (fragment instanceof WeiXinFragment) {
                    weiXinFragment = (WeiXinFragment) fragment;
                }
            }
        }

//        getFragmentManager().beginTransaction().show(zhiHuFragment).hide().commit();
    }


    public boolean onKeyDown (int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            showExitDialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private void showExitDialog () {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialogInterface, int i) {
                AppManager.getAppManager().AppExit(MainActivity.this);
            }
        });
        builder.show();
    }


    private void hideFragments (FragmentTransaction transaction) {
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (zhiHuFragment != null) {
            transaction.hide(zhiHuFragment);
        }
        if (weiXinFragment != null) {
            transaction.hide(weiXinFragment);
        }
    }
}

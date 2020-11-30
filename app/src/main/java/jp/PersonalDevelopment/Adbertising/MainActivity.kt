package jp.PersonalDevelopment.Adbertising

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity : AppCompatActivity(){
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            Log.d("MainActivity", "NavigationView")

            when (item.itemId) {
                R.id.navigation_Home -> {
                    Log.d("MainActivity", "Home")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Search -> {
                    Log.d("MainActivity", "Search")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Coupon -> {
                    Log.d("MainActivity", "Coupon")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CouponFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Subscribe -> {
                    Log.d("MainActivity", "Subscibe")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SubscribeFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
         false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setEnabled(true);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
    }
}

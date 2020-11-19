package jp.PersonalDevelopment.Adbertising

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.PersonalDevelopment.Adbertising.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_Home -> {
                    val homeFragment = HomeFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment)
                    fragmentTransaction.commit()
                }
                R.id.navigation_Search -> {
                    val searchFragment = SearchFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.fragment_container, searchFragment)
                    fragmentTransaction.commit()
                }
                R.id.navigation_Coupon -> {
                    val couponFragment = CouponFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.fragment_container, couponFragment)
                    supportFragmentManager.popBackStack()
                    fragmentTransaction.commit()
                }
                R.id.navigation_Subscribe -> {
                    val subscribeFragment = SubscribeFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.replace(R.id.fragment_container, subscribeFragment)
                    fragmentTransaction.commit()
                }
            }
            false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, homeFragment)
        fragmentTransaction.commit()
    }
}
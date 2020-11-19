package jp.PersonalDevelopment.Adbertising

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.PersonalDevelopment.Adbertising.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, LoginActivity::class.java )
                    startActivity(intent)
                }
                R.id.navigation_dashboard -> {
                    val firstFragment = FirstFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.replace(R.id.fragment_container, firstFragment)
                    fragmentTransaction?.commit()
                }
                R.id.navigation_notifications -> {
                    val secondFragment = SecondFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.replace(R.id.fragment_container, secondFragment)
                    fragmentTransaction?.commit()
                }
                R.id.navigation_subscribe -> {
                    val subscribeFragment = SubscribeFragment()
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.replace(R.id.fragment_container, subscribeFragment)
                    fragmentTransaction?.commit()
                }
            }
            false
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, firstFragment)
        fragmentTransaction.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
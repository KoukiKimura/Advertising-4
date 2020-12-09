package jp.PersonalDevelopment.Adbertising

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.ListAdapter
import jp.PersonalDevelopment.Adbertising.AdbListAdapter


class MainActivity : AppCompatActivity(), View.OnClickListener {
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
                R.id.navigation_Point -> {
                    Log.d("MainActivity", "Subscibe")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PointFragment())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
         false
    }


    // --- ここまで追加する ---

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageFrame : ImageView = findViewById(R.id.account_icon)
        imageFrame.setOnClickListener(this)


        // BottomNavigationView処理
        navigation.setEnabled(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // HomeFragment の表示
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()


        // ログイン処理
        val user = FirebaseAuth.getInstance().currentUser       // ログイン済みユーザを取得する
        if (user == null) {                                     // ログインしていなければログイン画面に遷移させる
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onClick(v: View?){
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }
}

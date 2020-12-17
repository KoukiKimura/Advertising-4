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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import jp.PersonalDevelopment.Adbertising.AdbListAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var activeFragment: Fragment =HomeFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_Home -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,HomeFragment())
                            .addToBackStack(null)
                            .commit()
                    Log.d("onNavigation",activeFragment.toString())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Search -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,SearchFragment())
                            .addToBackStack(null)
                            .commit()
                    Log.d("onNavigation",activeFragment.toString())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Coupon -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,CouponFragment())
                            .addToBackStack(null)
                            .commit()
                    Log.d("onNavigation",activeFragment.toString())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_Point -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container,PointFragment())
                            .addToBackStack(null)
                            .commit()
                    Log.d("onNavigation",activeFragment.toString())
                    return@OnNavigationItemSelectedListener true
                }
            }
         false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomNavigationView処理
        navigation.setEnabled(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // HomeFragment の表示
        activeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,HomeFragment())
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
        intent.putExtra("genre", 0)
        startActivity(intent)
    }
}

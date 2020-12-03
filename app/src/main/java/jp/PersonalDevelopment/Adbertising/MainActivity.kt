package jp.PersonalDevelopment.Adbertising

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


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

    // --- ここから ---
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mListView: ListView
    private lateinit var mQuestionArrayList: ArrayList<list>
    private lateinit var mAdapter: AdbListAdapter

    private var mGenreRef: DatabaseReference? = null

    private val mEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            val map = dataSnapshot.value as Map<String, String>
            val title = map["title"] ?: ""
            val body = map["body"] ?: ""
            val name = map["name"] ?: ""
            val uid = map["uid"] ?: ""
            val imageString = map["image"] ?: ""
            val bytes =
                    if (imageString.isNotEmpty()) {
                        Base64.decode(imageString, Base64.DEFAULT)
                    } else {
                        byteArrayOf()
                    }


            val question = list(title, desp ,  uid, dataSnapshot.key)
            mQuestionArrayList.add(question)
            mAdapter.notifyDataSetChanged()
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            val map = dataSnapshot.value as Map<String, String>

            // 変更があったQuestionを探す
            for (question in mQuestionArrayList) {
                if (dataSnapshot.key.equals(question.questionUid)) {
                    // このアプリで変更がある可能性があるのは回答(Answer)のみ
                    question.answers.clear()
                    val answerMap = map["answers"] as Map<String, String>?
                    if (answerMap != null) {
                        for (key in answerMap.keys) {
                            val temp = answerMap[key] as Map<String, String>
                            val answerBody = temp["body"] ?: ""
                            val answerName = temp["name"] ?: ""
                            val answerUid = temp["uid"] ?: ""
                            val answer = Answer(answerBody, answerName, answerUid, key)
                            question.answers.add(answer)
                        }
                    }

                    mAdapter.notifyDataSetChanged()
                }
            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {

        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {

        }

        override fun onCancelled(p0: DatabaseError) {

        }
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

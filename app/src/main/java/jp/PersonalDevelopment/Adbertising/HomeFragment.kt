package jp.PersonalDevelopment.Adbertising

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.gms.common.config.GservicesValue.value
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.*
import jp.PersonalDevelopment.Adbertising.Adbertising.ContentsPATH
import jp.PersonalDevelopment.Adbertising.R.id.home_image_fragment

class HomeFragment:Fragment(){

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mListView: ListView
    private lateinit var mHomeImageScrollView: ListView
    private lateinit var mListArrayHomeListSystem: ArrayList<HomeListSystem>
    private lateinit var mHomeImageScrollListSystem: ArrayList<HomeImageListSystem>
    private lateinit var mAdapter: AdbListAdapter
    private lateinit var mHomeImageAdapter: HomeImageAdapter
    private var listener: OnHomeFragmentListener? = null

    private lateinit var mBottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var mImageFrame: FrameLayout
    private lateinit var mImageView: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mAdapter = AdbListAdapter(context)
        mHomeImageAdapter = HomeImageAdapter(context)
    }

    override fun onCreate(savedinstanceState: Bundle?){
        super.onCreate(savedinstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View?{
        // Firebase
        mDatabaseReference = FirebaseDatabase.getInstance().reference
        val view = inflater.inflate(R.layout.fragment_home, container,false)
        val imageFragment : ImageView = view.findViewById(R.id.account_icon)

        // ListView の準備
        mListView = view.findViewById(R.id.home_list_view)
        mListArrayHomeListSystem = ArrayList<HomeListSystem>()
        mAdapter.notifyDataSetChanged()
        mListView.adapter = mAdapter

        // ImageViewの準備
        mHomeImageScrollView = view.findViewById(R.id.home_image_scroll_view)
        mHomeImageScrollListSystem = ArrayList<HomeImageListSystem>()
        mHomeImageAdapter.notifyDataSetChanged()
        mHomeImageScrollView.adapter = mHomeImageAdapter

        // BottomSheet
        val bottomSheet : LinearLayout = view.findViewById(R.id.home_bottom_sheet)
        mBottomSheet = BottomSheetBehavior.from(bottomSheet)
        mBottomSheet.setPeekHeight(0)

        mImageFrame = view.findViewById(R.id.home_image_fragment)
        mImageFrame.visibility = View.INVISIBLE


        mImageView = view.findViewById(R.id.home_image_view)

        mListView.setOnItemClickListener { parent, view, position, id -> onListClicked(view) }
        imageFragment.setOnClickListener { view -> onIconClicked(view) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val map = mutableListOf("aaa","bbb","ccc")
      //  mListArrayList.clear()
        val mListRef = mDatabaseReference.child(ContentsPATH).child("0")
        mListRef.addChildEventListener(mEventListener)

        mAdapter.setAdbArrayList(mListArrayHomeListSystem)
        mAdapter.notifyDataSetChanged()
        mListView.adapter

        mHomeImageAdapter.setHomeImageArrayList(mHomeImageScrollListSystem)
        mHomeImageAdapter.notifyDataSetChanged()
        mHomeImageScrollView.adapter
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private val mEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
            val map = dataSnapshot.value as Map<String, String>
            val title = map["title"] ?: ""
            val desp = map["desp"] ?: ""
            val uid = map["uid"] ?: ""
            val imageString = map["image"] ?: ""
            val bytes =
                    if (imageString.isNotEmpty()) {
                        Base64.decode(imageString, Base64.DEFAULT)
                    } else {
                        byteArrayOf()
                    }
            val list = HomeListSystem(title,desp,uid,bytes)
            mListArrayHomeListSystem.add(list)
            mAdapter.notifyDataSetChanged()
        }
        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            val map = dataSnapshot.value as Map<String, String>

            // 変更があったListを探す
            for (list in mListArrayHomeListSystem) {
                if (dataSnapshot.key.equals(list.uid)) {
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

    private fun onListClicked(view:View){
        val dataSnapshot = mDatabaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    // TODO: handle the post
                    val map = dataSnapshot.value as Map<String, String>
                    val title = map["title"] ?: ""
                    val desp = map["desp"] ?: ""
                    val uid = map["uid"] ?: ""
                    val imageString = map["image"] ?: ""
                    val bytes =
                            if (imageString.isNotEmpty()) {
                                Base64.decode(imageString, Base64.DEFAULT)
                            } else {
                                byteArrayOf()
                            }
                    val list = HomeImageListSystem(title, bytes)
                    mHomeImageScrollListSystem.add(list)
                    mHomeImageAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        mImageFrame.visibility = View.VISIBLE
//                if(mBottomSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
//                    // 全画面化
//                    Log.d("BottomSheet","BottomSheet Expanded")
//                    mBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
//                } else {
//                    // 縮小化（peekHeightの高さ）
//                    Log.d("BottomSheet","BottomSheet Coolapsed")
//                    mBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
//                }
    }
    private fun onIconClicked(view:View){
        val intent = Intent(context,TestActivity::class.java)
        intent.putExtra("genre",0)
        activity!!.startActivity(intent)
    }

    interface OnHomeFragmentListener{
        fun onHomeFragmentFinish()
    }
    companion object {
        @JvmStatic
        fun newInstance(param: String) =
                HomeFragment().apply {
                }
    }
}
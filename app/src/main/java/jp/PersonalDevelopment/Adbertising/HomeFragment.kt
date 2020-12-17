package jp.PersonalDevelopment.Adbertising

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.*
import jp.PersonalDevelopment.Adbertising.Adbertising.ContentsPATH

class HomeFragment:Fragment(){

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mListView: ListView
    private lateinit var mListArrayList: ArrayList<list>
    private lateinit var mAdapter: AdbListAdapter
    private var listener: OnHomeFragmentListener? = null

    private lateinit var mBottomSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var mImageView: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mAdapter = AdbListAdapter(context)
    }

    override fun onCreate(savedinstanceState: Bundle?){
        super.onCreate(savedinstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View?{
        // Firebase
        mDatabaseReference = FirebaseDatabase.getInstance().reference
        var view = inflater.inflate(R.layout.fragment_home, container,false)
        val imageFrame : ImageView = view.findViewById(R.id.account_icon)

        // ListView の準備
        mListView = view.findViewById(R.id.home_list_view)
        mAdapter.notifyDataSetChanged()
        mListArrayList = ArrayList<list>()
        mListView.adapter = mAdapter

        // BottomSheet
        var bottomSheet : LinearLayout = view.findViewById(R.id.home_bottom_sheet)
        mBottomSheet= BottomSheetBehavior.from(bottomSheet)
        mImageView = view.findViewById(R.id.home_image_view)

        mListView.setOnItemClickListener { parent, view, position, id -> onListClicked(view) }
        imageFrame.setOnClickListener { view -> onIconClicked(view) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment","onViewCreated")

        val map = mutableListOf("aaa","bbb","ccc")
      //  mListArrayList.clear()
        var mListRef = mDatabaseReference.child(ContentsPATH).child("0")
        mListRef!!.addChildEventListener(mEventListener)
        mAdapter.setAdbArrayList(mListArrayList)
        mAdapter.notifyDataSetChanged()
        mListView.adapter = mAdapter

        Log.d("Fragmenton", mListView.count.toString())
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
            val list = list(title,desp,uid,bytes)
            mListArrayList.add(list)
            mAdapter.notifyDataSetChanged()
            Log.d("onChildAdded", mListArrayList.size.toString())
        }
        override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            val map = dataSnapshot.value as Map<String, String>

            // 変更があったListを探す
            for (list in mListArrayList) {
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
        Log.d("dirLook",view.toString())

                if(mBottomSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
                    // 全画面化
                    Log.d("BottomSheet","BottomSheet Expanded")
                    mBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    // 縮小化（peekHeightの高さ）
                    Log.d("BottomSheet","BottomSheet Coolapsed")
                    mBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                }
    }
    private fun onIconClicked(view:View){
        val intent = Intent(context,TestActivity::class.java)
        intent.putExtra("genre",0)
        activity!!.startActivity(intent)
        Log.d("onIconClickd","activity!!")
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
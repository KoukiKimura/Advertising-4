package jp.PersonalDevelopment.Adbertising

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.google.firebase.database.core.Context

private const val ARG_PARAM = "param"

class HomeFragment:Fragment(){

    // Firebase 連携
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mListView: ListView
    private lateinit var mListArrayList: ArrayList<list>
    private lateinit var mAdapter: AdbListAdapter

    private var mDataRef: DatabaseReference? = null

    private var param: String? = null
    private var listener: OnHomeFragmentListener? = null


    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        mAdapter = AdbListAdapter(context)
        Log.d("fragment","onAttach")
    }

    override fun onCreate(savedinstanceState: Bundle?){
        super.onCreate(savedinstanceState)
        arguments?.let{
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View?{
        // Firebase
        mDatabaseReference = FirebaseDatabase.getInstance().reference

        var view = inflater.inflate(R.layout.fragment_home, container,false)

        // ListView の準備
        mListView = view.findViewById(R.id.home_list_view)


        mAdapter.notifyDataSetChanged()
        mAdapter.setAdbArrayList(mListArrayList)
        mListView.adapter = mAdapter

        Log.d("Fragment","onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment","onViewCreated")

        mListArrayList = ArrayList<list>()

        mAdapter.setAdbArrayList(mListArrayList)
        mListView.adapter = mAdapter
        Log.d("Fragment", mListView.count.toString())
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
            val list = list(title, desp ,  uid,bytes)
            mListArrayList.add(list)
            mAdapter.notifyDataSetChanged()
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

    interface OnHomeFragmentListener{
        fun onHomeFragmentFinish()
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM, param)
                    }
                }

    }

}


package jp.PersonalDevelopment.Adbertising

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.fragment_point.*


class PointFragment : Fragment() {
    companion object {
        private const val TAG = "SubscribeFragment"
        fun createInstance(textStr:String?) :PointFragment{
            return PointFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_point, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = ArrayList<Map<String, String>>()
        for (i in 0..10){
            val map = HashMap<String, String>()
            map.put("main","メイン $i")
            map.put("sub","サブ $i")
            list.add(map)
        }

        val adapter = SimpleAdapter(
                getContext(),
                list,
                android.R.layout.simple_list_item_2,
                arrayOf("main","sub"),
                intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        point_list_view.adapter = adapter

        point_list_view.setOnItemClickListener {parent, view, position, id ->
            Log.d("UI_PARTS","クリック $position")
        }
    }
}
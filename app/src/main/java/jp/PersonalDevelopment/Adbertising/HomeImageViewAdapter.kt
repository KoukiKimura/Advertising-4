package jp.PersonalDevelopment.Adbertising

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class HomeImageViewAdapter(context: Context) : BaseAdapter() {
    private var mLayoutInflater: LayoutInflater
    private var mAdbArrayList = ArrayList<list>()

    init {
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int{
        return mAdbArrayList.size
    }
    override fun getItem(position: Int): Any {
        return mAdbArrayList[position]
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getView(position:Int, convertView:View?, parent: ViewGroup?): View {
        var convertView = convertView

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_home_view,parent, false)
        }
        val bytes = mAdbArrayList[position].imageBytes
        if(bytes.isNotEmpty()){
            val image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).copy(Bitmap.Config.ARGB_8888,true)
            val imageView = convertView.findViewById<View>(R.id.home_image_view) as ImageView
            imageView.setImageBitmap(image)
        }
        return convertView
    }

    fun setAdbArrayList(adbArrayList: ArrayList<list>){
        mAdbArrayList = adbArrayList
    }
}
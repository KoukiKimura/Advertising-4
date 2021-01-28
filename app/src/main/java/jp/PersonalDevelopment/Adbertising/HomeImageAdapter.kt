package jp.PersonalDevelopment.Adbertising


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class HomeImageAdapter(context: Context) : BaseAdapter() {
    private var mLayoutInflater: LayoutInflater
    private var mHomeImageArrayList = ArrayList<HomeImageListSystem>()



    init {
        mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int{
        return mHomeImageArrayList.size
    }
    override fun getItem(position: Int): Any {
        return mHomeImageArrayList[position]
    }
    override fun getItemId(position: Int): Long{
        return position.toLong()
    }
    override fun getView(position:Int, convertView:View?, parent: ViewGroup?): View {
        var convertView = convertView

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_home_view,parent, false)
        }

        val bytes = mHomeImageArrayList[position].imageBytes
        if(bytes.isNotEmpty()){
            val image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size).copy(Bitmap.Config.ARGB_8888,true)
            val imageView = convertView!!.findViewById<View>(R.id.home_image_view) as ImageView
            imageView.setImageBitmap(image)
        }
        return convertView!!
    }

    fun setHomeImageArrayList(homeArrayHomeImageListSystem: ArrayList<HomeImageListSystem>){
        mHomeImageArrayList = homeArrayHomeImageListSystem
    }
}
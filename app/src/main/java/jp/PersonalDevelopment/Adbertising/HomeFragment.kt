package jp.PersonalDevelopment.Adbertising

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class HomeFragment:Fragment(){
    companion object {
        private const val TAG = "HomeFragment"
        fun createInstance(textStr: String?) : HomeFragment{
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):View?{
        inflater.inflate(R.layout.fragment_home, container, false)

        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

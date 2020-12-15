package jp.PersonalDevelopment.Adbertising

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class CouponFragment : Fragment() {
    private lateinit var mBottomSheet: BottomSheetBehavior<LinearLayout>

    companion object {
        private const val TAG = "CouponFragment"
        fun createInstance(textStr:String?) : CouponFragment{
            return CouponFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_coupon, container, false)
        var bottomSheet : LinearLayout = view.findViewById<LinearLayout>(R.id.bottom_sheet_layout)
        mBottomSheet = BottomSheetBehavior.from(bottomSheet)
        var button = view.findViewById<Button>(R.id.coupon_button)
        button.setOnClickListener{onButtomClicked(it)}

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

    }

    private fun onButtomClicked(view:View){
        when(view.id){
            R.id.coupon_button ->
                if(mBottomSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
                    // 全画面化
                    mBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    // 縮小化（peekHeightの高さ）
                    mBottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                }
        }
    }
}
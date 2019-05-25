package android.example.workouttrackaer



import android.content.Intent
import android.R
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter: FragmentPagerAdapter {

    private val mFragmentList:MutableList<Fragment> = mutableListOf()
    private val mFragmentTitleList:MutableList<String> = mutableListOf()

    constructor(manager: FragmentManager):super(manager)

    fun addFragment(fragment:Fragment,title:String)
    {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
    override fun getItem(position: Int): Fragment {

        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    fun replaceFragment(fragment:Fragment, title:String, position:Int)
    {
        mFragmentList.removeAt(position)
        mFragmentTitleList.removeAt(position)
        mFragmentList.add(position, fragment)
        mFragmentTitleList.add(position, title)
    }


}
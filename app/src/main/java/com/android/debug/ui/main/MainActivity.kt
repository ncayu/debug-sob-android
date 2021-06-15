package com.android.debug.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.debug.BR
import com.android.debug.R
import com.android.debug.core.base.BaseActivity
import com.android.debug.databinding.ActivityMain2Binding
import com.android.debug.model.bean.TabEntity
import com.android.debug.ui.fragment.*
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import java.util.*

class MainActivity : BaseActivity<ActivityMain2Binding, MainViewModel>() {

    private val mTabEntities = ArrayList<CustomTabEntity>()
    private val mFragments = ArrayList<Fragment>()
    private val mTitles = arrayOf("特惠", "沸点", "发现", "小册", "我的")

    private val mIconUnselectIds = intArrayOf(
            R.mipmap.tab_home_normal, R.mipmap.tab_activity,
            R.mipmap.tab_find_normal, R.mipmap.tab_xiaoce_normal, R.mipmap.tab_profile_normal)
    private val mIconSelectIds = intArrayOf(
            R.mipmap.tab_home, R.mipmap.tab_activity_press,
            R.mipmap.tab_find, R.mipmap.tab_xiaoce, R.mipmap.tab_profile)


    override fun getLayoutId() = R.layout.activity_main2

    override fun getVariableId() = BR.mainVm

    override fun initView() {

    }

    override fun initData(savedInstanceState: Bundle?) {
        initTab()
    }

    private fun initTab() {
        //全部fragment丢进去
        mFragments.clear()
        mFragments.add(HomeFragment())
        mFragments.add(TabFragment())
        mFragments.add(FindFragment())
        mFragments.add(XiaoceFragment())
        mFragments.add(ProfileFragment())


        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        vb.viewPage.adapter = MyPagerAdapter(supportFragmentManager, mFragments, mTitles)
        //设置view page的缓存为tab的size
        vb.viewPage.offscreenPageLimit = mTitles.size
        vb.tlMain.setTabData(mTabEntities)
        vb.tlMain.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换tab
                vb.viewPage.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    class MyPagerAdapter(fm: FragmentManager?, fragmentList: ArrayList<Fragment>, arr: Array<String>) : FragmentPagerAdapter(fm!!) {
        private var f: ArrayList<Fragment> = fragmentList
        private var arrTitle: Array<String> = arr


        override fun getCount(): Int {
            return f.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return arrTitle[position]
        }

        override fun getItem(position: Int): Fragment {
            return f[position]
        }
    }

    override fun startObserve() {
    }

}
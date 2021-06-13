package com.android.debug.ui.loop

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debug.BR
import com.android.debug.R
import com.android.debug.core.base.BaseActivity
import com.android.debug.databinding.ActivityJpBlogBinding
import com.android.debug.test.adapter.LoopAdapter
import com.android.debug.ui.loop_databinding.LoopDatabindingActivity
import com.android.lib.common.utils.navActivity
import com.android.lib.common.utils.singleClick
import com.umeng.analytics.MobclickAgent

class JpBlogActivity : BaseActivity<ActivityJpBlogBinding, JpBlogViewModel>() {

    private val mLoopAdapter by lazy { LoopAdapter() }

    override fun getLayoutId() = R.layout.activity_jp_blog

    override fun initView() {
        vb.btnOpenDb.singleClick {
            navActivity<LoopDatabindingActivity>()
        }
        //初始化rv
        vb.rvLoop.layoutManager = LinearLayoutManager(this)
        vb.rvLoop.adapter = mLoopAdapter

//        mLoopAdapter.setOnItemLongClickListener { adapter, view, position ->
//            for (i in mLoopAdapter.data.indices) {
//                mLoopAdapter.data[i].bgColor = "#ffffff"
//            }
//            mLoopAdapter.data[position].bgColor = "#FF00ff"
//            mLoopAdapter.notifyDataSetChanged()
//            return@setOnItemLongClickListener false
//        }
    }

    override fun getVariableId(): Int {
        return BR.mainVm
    }

    override fun startObserve() {
        //等待结果
        viewModel.apply {
            liveDataLoops.observe(this@JpBlogActivity, {
                //得到结果
                mLoopAdapter.setList(it)
            })
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        //请求数据
        viewModel.httpGetLoop()
    }
    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}
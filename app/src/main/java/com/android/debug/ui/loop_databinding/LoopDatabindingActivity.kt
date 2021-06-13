package com.android.debug.ui.loop_databinding

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debug.BR
import com.android.debug.R
import com.android.debug.core.base.BaseActivity
import com.android.debug.databinding.ActivityLoopDatabindingBinding
import com.android.debug.ui.loop.JpBlogViewModel
import com.umeng.analytics.MobclickAgent

class LoopDatabindingActivity : BaseActivity<ActivityLoopDatabindingBinding, JpBlogViewModel>() {

    private val loopAdapter: DbLoopAdapter by lazy {
        DbLoopAdapter().apply {
            vb.rvLoop.layoutManager = LinearLayoutManager(this@LoopDatabindingActivity)
            vb.rvLoop.adapter = this
        }
    }

    override fun getLayoutId() = R.layout.activity_loop_databinding
    override fun getVariableId() = BR.loopVm

    override fun startObserve() {
        viewModel.apply {
            liveDataLoops.observe(this@LoopDatabindingActivity, {
                loopAdapter.setList(it)
            })
        }
    }

    override fun initView() {

    }

    override fun initData(savedInstanceState: Bundle?) {
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
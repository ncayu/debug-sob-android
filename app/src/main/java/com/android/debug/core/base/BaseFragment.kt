package com.android.debug.core.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


abstract class BaseFragment(redID: Int) : Fragment(redID), CoroutineScope by MainScope() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }



    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        //协程取消
        cancel()
    }
}
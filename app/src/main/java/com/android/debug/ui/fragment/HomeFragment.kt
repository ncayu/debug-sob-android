package com.android.debug.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.debug.R
import com.android.debug.core.base.BaseFragment
import com.android.debug.databinding.LayoutHomeBinding
import com.android.debug.ui.fragment.vm.HomeViewModel
import com.hi.dhl.binding.viewbind

class HomeFragment : BaseFragment(R.layout.layout_home) {

    private val vb: LayoutHomeBinding by viewbind()

    //测试代码
    //加载viewmode,这样就可以实例化vm了。与act不同，act绑定一个vm用户databinding
    //fragment可以和act共享vm或者，实例化多个vm。（act也可以实例化多个vm）
    //目前fragment就先用这个方式,下面的vm换成你自己的噢
    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //把根view返回
        return vb.root
    }

    override fun initView() {
        vb.tv.text = "text"

    }

    override fun initData() {
        vm.getRecommend(1)
    }
}
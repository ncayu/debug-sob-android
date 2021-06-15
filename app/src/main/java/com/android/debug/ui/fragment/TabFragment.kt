package com.android.debug.ui.fragment

import com.android.debug.R
import com.android.debug.core.base.BaseFragment
import com.android.debug.databinding.LayoutTabBinding
import com.hi.dhl.binding.databind

class TabFragment : BaseFragment(R.layout.layout_tab) {

    private val vb: LayoutTabBinding by databind()

    override fun initView() {
        vb.btnTtt.text = "32sss4"
    }

    override fun initData() {

    }
}
package com.android.debug.ui.fragment

import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debug.R
import com.android.debug.core.base.BaseFragment
import com.android.debug.databinding.LayoutTabBinding
import com.android.debug.ui.adapter.MomentAdapter
import com.android.debug.ui.fragment.vm.MomentViewModel
import com.hi.dhl.binding.databind
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

class MomentFragment : BaseFragment(R.layout.layout_tab) {

    private val vb: LayoutTabBinding by databind()
    private val vm: MomentViewModel by viewModels()
    private val momentAdapter = MomentAdapter()

    override fun initView() {
        vb.rvMoment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = momentAdapter
            addItemDecoration(
                    HorizontalDividerItemDecoration.Builder(context)
                            .color(Color.parseColor("#F6F6F6"))
                            .sizeResId(R.dimen.sw_6dp)
                            .build()
            )
        }
    }

    override fun initData() {
        vm.getMoment(1)
        vm.momentLiveData.observe(viewLifecycleOwner) {
            //数据给适配器
            momentAdapter.setNewInstance(it.list)
        }
    }
}
package com.android.debug.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.debug.R
import com.android.debug.core.base.BaseFragment
import com.android.debug.databinding.LayoutHomeBinding
import com.android.debug.ui.adapter.HomeRecommendAdapter
import com.android.debug.ui.fragment.vm.HomeViewModel
import com.hi.dhl.binding.viewbind
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration


class HomeFragment : BaseFragment(R.layout.layout_home) {

    private val recommendAdapter = HomeRecommendAdapter()

    private val vb: LayoutHomeBinding by viewbind()

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return vb.root
    }

    override fun initView() {
        vb.rvRecommend.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendAdapter
            addItemDecoration(
                    HorizontalDividerItemDecoration.Builder(context)
                            .color(Color.parseColor("#F6F6F6"))
                            .sizeResId(R.dimen.sw_4dp)
                            .build())
        }
    }

    override fun initData() {
        vm.getRecommend(1)
        vm.articleList.observe(viewLifecycleOwner) {
            recommendAdapter.setNewInstance(it.list)
        }
    }
}
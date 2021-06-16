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
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration


class HomeFragment : BaseFragment(R.layout.layout_home), OnRefreshListener, OnLoadMoreListener {

    private val recommendAdapter = HomeRecommendAdapter()

    private val vb: LayoutHomeBinding by viewbind()

    private val vm: HomeViewModel by viewModels()

    private var mCurrentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }

    override fun initView() {
        vb.rvRecommend.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recommendAdapter
            addItemDecoration(
                HorizontalDividerItemDecoration.Builder(context)
                    .color(Color.parseColor("#F6F6F6"))
                    .sizeResId(R.dimen.sw_6dp)
                    .build()
            )
        }
    }

    override fun initData() {
        vm.getRecommend(1)
        //文章数据返回
        vm.articleList.observe(viewLifecycleOwner) {
            mCurrentPage = it.currentPage

            if (it.currentPage == 1) {
                vb.slHomeLayout.finishRefresh()
                recommendAdapter.setNewInstance(it.list)
            } else {
                if (it.isHasNext) {
                    vb.slHomeLayout.finishLoadMore()
                } else {
                    vb.slHomeLayout.finishRefreshWithNoMoreData()
                }
                recommendAdapter.addData(it.list)
            }
        }
        //下拉，上拉监听
        vb.slHomeLayout.setOnRefreshListener(this)
        vb.slHomeLayout.setOnLoadMoreListener(this)

        vm.requestState.observe(viewLifecycleOwner) {
            if (it) {
                vb.slHomeLayout.finishRefresh()
            } else {
                vb.slHomeLayout.finishLoadMore(false)
            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mCurrentPage = 1
        vm.getRecommend(1)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        vm.getRecommend(mCurrentPage + 1)
    }
}
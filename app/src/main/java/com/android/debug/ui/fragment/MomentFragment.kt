package com.android.debug.ui.fragment

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ch.ielse.view.imagewatcher.ImageWatcher
import com.android.debug.R
import com.android.debug.core.base.BaseFragment
import com.android.debug.databinding.LayoutTabBinding
import com.android.debug.ui.adapter.MomentAdapter
import com.android.debug.ui.fragment.vm.MomentViewModel
import com.android.debug.ui.main.MainActivity
import com.android.debug.ui.moment.MomentDetailActivity
import com.android.debug.utils.GlideSimpleTarget
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.hi.dhl.binding.databind
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import zlc.season.bracer.start

class MomentFragment : BaseFragment(R.layout.layout_tab), ImageWatcher.OnPictureLongPressListener,
        ImageWatcher.Loader, OnItemClickListener {

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
        // vb.imageWatcher.apply {
        //     setTranslucentStatus(Utils.calcStatusBarHeight(context))
        //     setErrorImageRes(R.mipmap.error_picture)
        //     setOnPictureLongPressListener(this@MomentFragment)
        //     setLoader(this@MomentFragment)
        // }
        momentAdapter.setImageWatcher((context as MainActivity).getImageWatcher())
        momentAdapter.setOnItemClickListener(this)
    }

    override fun initData() {
        vm.getMoment(1)
        vm.momentLiveData.observe(viewLifecycleOwner) {
            //数据给适配器
            momentAdapter.setNewInstance(it.list)
        }
    }

    override fun onPictureLongPress(v: ImageView?, url: String?, pos: Int) {
    }

    override fun load(context: Context?, url: String?, lc: ImageWatcher.LoadCallback?) {
        context?.apply {
            Glide.with(this).asBitmap().load(url).into(GlideSimpleTarget(lc))
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        context?.let {
            MomentDetailActivity().apply {
                mMomentId = momentAdapter.data[position].id
            }.start(it)
        }
    }
}
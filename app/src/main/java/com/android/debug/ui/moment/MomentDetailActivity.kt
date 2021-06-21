package com.android.debug.ui.moment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import ch.ielse.view.imagewatcher.ImageWatcher
import com.allen.library.CircleImageView
import com.android.debug.R
import com.android.debug.core.base.BaseActivity
import com.android.debug.databinding.ActivityMomentDetailBinding
import com.android.debug.model.bean.SobMomentDetail
import com.android.debug.ui.adapter.MomentDetailAdapter
import com.android.debug.utils.GlideSimpleTarget
import com.android.debug.utils.ImageHelper
import com.android.lib.common.utils.AppDateUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.debug.widget.nine.NineGridView
import com.debug.widget.nine.NineImageAdapter
import com.debug.widget.nine.Utils
import com.gyf.immersionbar.ktx.immersionBar
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import zlc.season.bracer.mutableParams

class MomentDetailActivity : BaseActivity<ActivityMomentDetailBinding, MomentDetailViewModel>(),
        ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader, OnRefreshListener,
        OnLoadMoreListener {

    var mMomentId by mutableParams<String>()
    private val mRequestOptions: RequestOptions = RequestOptions().centerCrop()
    private val mDrawableTransitionOptions: DrawableTransitionOptions = DrawableTransitionOptions.withCrossFade()
    private val momentDetailAdapter = MomentDetailAdapter()
    private var mCurrentPage = 1
    override fun getLayoutId() = R.layout.activity_moment_detail

    override fun initView() {
        immersionBar {
            titleBar(R.id.toolbar)
        }

        vb.rvMomentDetail.apply {
            layoutManager = LinearLayoutManager(this@MomentDetailActivity)
            adapter = momentDetailAdapter
            addItemDecoration(
                    HorizontalDividerItemDecoration.Builder(context)
                            .color(getColor(R.color.color_f2f2f2))
                            .size(2)
                            .build()
            )
        }
        momentDetailAdapter.apply {
            //摸鱼详情布局
            addHeaderView(getHeaderView())
        }
        vb.imageWatcher.apply {
            setTranslucentStatus(Utils.calcStatusBarHeight(context))
            setErrorImageRes(R.mipmap.error_picture)
            setOnPictureLongPressListener(this@MomentDetailActivity)
            setLoader(this@MomentDetailActivity)
        }

        //下拉，上拉监听
        vb.refreshLayout.setOnRefreshListener(this)
        vb.refreshLayout.setOnLoadMoreListener(this)
    }

    override fun startObserve() {
        viewModel.detailLiveData.observe(this) {
            //显示头部详情数据
            momentDetailAdapter.headerLayout?.apply {
                vb.refreshLayout.finishRefresh()
                setMomentContent(it)
            }
        }
        viewModel.commentListData.observe(this) {
            it?.apply {
                mCurrentPage = it.currentPage
                if (this.currentPage == 1) {
                    vb.refreshLayout.finishRefresh()
                    momentDetailAdapter.setNewInstance(it.list ?: mutableListOf())
                    if (!it.isHasNext) {
                        vb.refreshLayout.finishRefreshWithNoMoreData()
                    }
                } else {
                    if (it.isHasNext) {
                        vb.refreshLayout.finishLoadMore()
                    } else {
                        vb.refreshLayout.finishRefreshWithNoMoreData()
                    }
                    momentDetailAdapter.addData(it.list ?: mutableListOf())
                }
            }
        }
        viewModel.requestState.observe(this) {
            if (!it) {
                vb.refreshLayout.finishLoadMore(false)
            }
        }

    }

    private fun LinearLayout.setMomentContent(it: SobMomentDetail) {
        momentDetailAdapter.setOwnerId(it.userId)
        val detail = this.getChildAt(0)
        //头像
        val avatar = detail.findViewById<CircleImageView>(R.id.iv_moment_avatar)
        ImageHelper.load(avatar, it.avatar)
        //名字
        detail.findViewById<TextView>(R.id.tv_moment_name).text = it.nickname
        //公司
        val company = "${it.position}@${it.company}"
        detail.findViewById<TextView>(R.id.tv_moment_company)
                .text = company
        //摸鱼内容
        detail.findViewById<TextView>(R.id.tv_moment_content).text = it.content
        //多图片
        detail.findViewById<NineGridView>(R.id.ng_moment)
                .apply {
                    setAdapter(NineImageAdapter(context, mRequestOptions, mDrawableTransitionOptions, it.images))
                    //大图片查看
                    setOnImageClickListener { _, view ->
                        vb.imageWatcher.show(view as ImageView, imageViews, it.images)
                    }
                }
        //摸鱼标签
        detail.findViewById<TextView>(R.id.tv_moment_topicName).apply {
            text = it.topicName ?: "随笔"
            visibility = View.VISIBLE
        }
        //摸鱼时间
        detail.findViewById<TextView>(R.id.tv_day)
                .text = AppDateUtils.getExactDate(AppDateUtils.parseLong4(it.createTime))
    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel.getMomentDetailById(mMomentId)
    }

    private fun getHeaderView(): View {
        val header: View = layoutInflater.inflate(R.layout.item_moment_content_layout, vb.rvMomentDetail, false)

        return header
    }

    override fun getVariableId() = 0

    override fun onPictureLongPress(v: ImageView?, url: String?, pos: Int) {
    }

    override fun load(context: Context?, url: String?, lc: ImageWatcher.LoadCallback?) {
        context?.apply {
            Glide.with(this).asBitmap().load(url).into(GlideSimpleTarget(lc))
        }
    }

    override fun onBackPressed() {
        if (!vb.imageWatcher.handleBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.getMomentDetailById(mMomentId)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.loadMoreComment(mMomentId, mCurrentPage)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}
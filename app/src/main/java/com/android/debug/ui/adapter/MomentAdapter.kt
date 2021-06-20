package com.android.debug.ui.adapter

import android.graphics.Color
import android.widget.ImageView
import ch.ielse.view.imagewatcher.ImageWatcher
import com.allen.library.CircleImageView
import com.android.debug.R
import com.android.debug.model.bean.SobMoment.MomentBean
import com.android.debug.utils.ImageHelper
import com.android.lib.common.utils.AppDateUtils
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.debug.widget.nine.NineGridView
import com.debug.widget.nine.NineImageAdapter

class MomentAdapter : BaseQuickAdapter<MomentBean, BaseViewHolder>(R.layout.item_moment_layout) {
    private val mRequestOptions: RequestOptions = RequestOptions().centerCrop()
    private val mDrawableTransitionOptions: DrawableTransitionOptions = DrawableTransitionOptions.withCrossFade()
    private var mImageWatcher: ImageWatcher? = null


    fun setImageWatcher(imageWatcher: ImageWatcher) {
        this.mImageWatcher = imageWatcher
    }


    override fun convert(baseViewHolder: BaseViewHolder, item: MomentBean) {
        //显示头像
        baseViewHolder.setVisible(R.id.divider_line, false)

        //头像
        val ivAvatar = baseViewHolder.getView(R.id.iv_moment_avatar) as CircleImageView
        if (item.isVip) {
            ivAvatar.borderColor = Color.parseColor("#FB88C4")
            ivAvatar.borderWidth = context.resources.getDimension(R.dimen.sw_2dp).toInt()
        } else {
            ivAvatar.borderColor = Color.parseColor("#000000")
            ivAvatar.borderWidth = 0
        }
        ImageHelper.load(baseViewHolder.getView(R.id.iv_moment_avatar), item.avatar, R.mipmap.ic_launcher)

        //名字
        baseViewHolder.setText(R.id.tv_moment_name, item.nickname)
        //公司，岗位，时间
        baseViewHolder.setText(R.id.tv_moment_company, "${item.position}@${item.company}")
        //内容
        baseViewHolder.setText(R.id.tv_moment_content, item.content)
        //图片
        baseViewHolder.getView<NineGridView>(R.id.ng_moment)
                .apply {
                    setAdapter(NineImageAdapter(context, mRequestOptions, mDrawableTransitionOptions, item.images))
                    //大图片查看
                    setOnImageClickListener { _, view ->
                        mImageWatcher?.show(view as ImageView, imageViews, item.images)
                    }
                }
        //标签
        baseViewHolder.setText(R.id.tv_moment_topicName, item.topicName ?: "随笔")
        // baseViewHolder.setVisible(R.id.tv_moment_topicName, item.topicName?.isNotEmpty() ?: false)
        //时间
        baseViewHolder.setText(R.id.tv_day, AppDateUtils.getExactDate(AppDateUtils.parseLong4(item.createTime)))
        //评论
        baseViewHolder.setText(R.id.tv_moment_comment, item.commentCount.toString())
        //点赞
        baseViewHolder.setText(R.id.tv_moment_like, item.thumbUpCount.toString())
        //分享
    }
}
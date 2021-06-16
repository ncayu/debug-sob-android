package com.android.debug.ui.adapter

import android.graphics.Color
import android.text.TextUtils
import com.allen.library.CircleImageView
import com.android.debug.R
import com.android.debug.model.bean.SobMoment.MomentBean
import com.android.debug.utils.ImageHelper
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.debug.widget.nine.NineGridView
import com.debug.widget.nine.NineImageAdapter

class MomentAdapter : BaseQuickAdapter<MomentBean, BaseViewHolder>(R.layout.item_moment_layout) {
    private val mRequestOptions: RequestOptions = RequestOptions().centerCrop()
    private val mDrawableTransitionOptions: DrawableTransitionOptions = DrawableTransitionOptions.withCrossFade()


    override fun convert(baseViewHolder: BaseViewHolder, item: MomentBean) {
        //显示头像
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
        // if (item.images == null || item.images.isEmpty()) {
        //     baseViewHolder.setVisible(R.id.ng_moment, false)
        // } else {
        //     baseViewHolder.setVisible(R.id.ng_moment, true)
        //     baseViewHolder.getView<NineGridView>(R.id.ng_moment)
        //             .setAdapter(NineImageAdapter(context, mRequestOptions, mDrawableTransitionOptions, item.images))
        // }
        baseViewHolder.getView<NineGridView>(R.id.ng_moment)
                .setAdapter(NineImageAdapter(context, mRequestOptions, mDrawableTransitionOptions, item.images))
        //标签
        baseViewHolder.setText(R.id.tv_moment_topicName, item.topicName)
        baseViewHolder.setVisible(R.id.tv_moment_topicName, !TextUtils.isEmpty(item.topicName))
        //评论
        baseViewHolder.setText(R.id.tv_moment_comment, item.commentCount.toString())
        //点赞
        baseViewHolder.setText(R.id.tv_moment_like, item.thumbUpCount.toString())
        //分享

    }
}
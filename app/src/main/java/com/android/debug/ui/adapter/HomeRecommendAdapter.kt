package com.android.debug.ui.adapter

import android.graphics.Color
import com.allen.library.CircleImageView
import com.android.debug.R
import com.android.debug.model.bean.HomeRecommend
import com.android.debug.utils.ImageHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class HomeRecommendAdapter :
    BaseQuickAdapter<HomeRecommend.RecommendArticle, BaseViewHolder>(R.layout.item_home_recommend_style1) {
    override fun convert(holder: BaseViewHolder, item: HomeRecommend.RecommendArticle) {
        //标题
        holder.setText(R.id.tv_title, item.title)
        //头像
        val ivAvatar = holder.getView(R.id.iv_avatar) as CircleImageView
        if (item.isVip) {
            ivAvatar.borderColor = Color.parseColor("#FB88C4")
            ivAvatar.borderWidth = context.resources.getDimension(R.dimen.sw_2dp).toInt()
        } else {
            ivAvatar.borderColor = Color.parseColor("#000000")
            ivAvatar.borderWidth = 0
        }
        ImageHelper.load(holder.getView(R.id.iv_avatar), item.avatar, R.mipmap.ic_launcher)
        //名字
        holder.setText(R.id.tv_name, item.nickName)
        //封面,没有占位图，临时先用icon
        if (item.covers != null && !item.covers.isEmpty()) {
            ImageHelper.load(holder.getView(R.id.iv_cover), item.covers[0], R.mipmap.ic_launcher)
        } else {
            holder.setBackgroundResource(R.id.iv_cover, R.mipmap.ic_launcher)
        }
        //阅读与收藏
        val str = "%s阅读 - %s收藏"
        holder.setText(
            R.id.tv_views,
            String.format(str, item.viewCount.toString(), item.thumbUp.toString())
        )
    }
}
package com.android.debug.ui.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.allen.library.CircleImageView
import com.android.debug.R
import com.android.debug.model.bean.SobMomentComment
import com.android.debug.utils.ImageHelper
import com.android.lib.common.utils.AppDateUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class MomentDetailAdapter : BaseQuickAdapter<SobMomentComment.ListMomentBean, BaseViewHolder>(R.layout.item_moment_comment_layout) {

    override fun convert(holder: BaseViewHolder, item: SobMomentComment.ListMomentBean) {
        //显示头像
        //头像
        val ivAvatar = holder.getView(R.id.iv_moment_avatar) as CircleImageView
        if (item.isVip) {
            ivAvatar.borderColor = Color.parseColor("#FB88C4")
            ivAvatar.borderWidth = context.resources.getDimension(R.dimen.sw_2dp).toInt()
        } else {
            ivAvatar.borderColor = Color.parseColor("#ffffff")
            ivAvatar.borderWidth = 0
        }
        ImageHelper.load(holder.getView(R.id.iv_moment_avatar), item.avatar, R.mipmap.ic_launcher)

        //名字
        holder.setText(R.id.tv_moment_name, item.nickname)
        //公司，岗位，时间
        holder.setText(R.id.tv_moment_company, "${item.position}@${item.company}")
        //内容
        holder.setText(R.id.tv_moment_content, item.content)
        //时间
        holder.setText(R.id.tv_day, AppDateUtils.getExactDate(AppDateUtils.parseLong4(item.createTime)))
        val subLayout = holder.getView<LinearLayout>(R.id.ll_sub_comments)
        subLayout.visibility = View.GONE
        if (item.subComments != null && item.subComments.isNotEmpty()) {
            item.subComments.forEach {
                val tv = TextView(context)
                tv.setPadding(0, 10, 0, 10)
                tv.setTextColor(context.getColor(R.color.color_black))
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimensionPixelSize(R.dimen.sw_13sp).toFloat())
                val comment = "${it.nickname} : ${it.content}"
                val spanString = SpannableString(comment)
                val span = ForegroundColorSpan(context.getColor(R.color.color_115E91))
                spanString.setSpan(span, 0, it.nickname.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                tv.text = spanString
                subLayout.addView(tv)
            }
            subLayout.visibility = View.VISIBLE
        } else {
            subLayout.removeAllViews()
        }

    }
}
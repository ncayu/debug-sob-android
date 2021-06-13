package com.android.debug.ui.loop_databinding

import com.android.debug.R
import com.android.debug.databinding.ItemDbLoopBinding
import com.android.debug.model.bean.SobLoop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author: dr
 * @date: 2021/2/1
 * @description data binding 方式做列表
 */
class DbLoopAdapter : BaseQuickAdapter<SobLoop, BaseDataBindingHolder<ItemDbLoopBinding>>(R.layout.item_db_loop) {
    override fun convert(holder: BaseDataBindingHolder<ItemDbLoopBinding>, item: SobLoop) {
        holder.dataBinding?.apply {
            loop = item
            executePendingBindings()
        }
    }
}
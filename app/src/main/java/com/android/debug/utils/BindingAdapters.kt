package com.android.debug.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("visibleUnless")
fun bindVisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("goneUnless")
fun bindGoneUnless(view: View, gone: Boolean) {
    view.visibility = if (gone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

// @BindingAdapter("htmlText")
// fun bindHtmlText(view:TextView,html:String){
//     view.text = if (fromN()) Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(html)
// }

// @BindingAdapter("articleStar")
// fun bindArticleStar(view:ImageView,collect:Boolean){
//     view.setImageResource(if (collect) R.drawable.timeline_like_pressed else R.drawable.timeline_like_normal)
// }

@BindingAdapter(
        "imageUrl",
        "imagePlaceholder",
        requireAll = false
)
fun bindImage(
        imageView: ImageView,
        imageUrl: String?,
        placeholder: Int? = null,
) {
    if (imageUrl == null) return

    if (placeholder != null) {
        ImageHelper.load(imageView, imageUrl, placeholder, placeholder)
    } else {
        ImageHelper.load(imageView, imageUrl)
    }
}

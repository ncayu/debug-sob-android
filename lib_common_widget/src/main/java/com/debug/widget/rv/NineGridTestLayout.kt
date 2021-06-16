package com.debug.widget.rv

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.android.lib.common.ext.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.debug.widget.RadiusImageView

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/12
 */
class NineGridTestLayout : NineGridLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    override fun displayOneImage(
        imageView: RadiusImageView,
        url: String,
        parentWidth: Int
    ): Boolean {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap> {
                @SuppressLint("CheckResult")
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    bitmap: Bitmap,
                    model: Any,
                    target: Target<Bitmap>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    val w = bitmap.width
                    val h = bitmap.height
                    Log.i(">>>", "test w:$w")
                    Log.i(">>>", "test h:$h")
                    val newW: Int
                    val newH: Int
                    if (h > w * MAX_W_H_RATIO) { //h:w = 5:3
                        newW = parentWidth / 2
                        newH = newW * 5 / 3
                    } else if (h < w) { //h:w = 2:3
                        newW = parentWidth * 2 / 3
                        newH = newW * 2 / 3
                    } else { //newH:h = newW :w
                        newW = parentWidth / 2
                        newH = h * newW / w
                    }
                    setOneImageLayoutParams(imageView, newW, newH)
                    return false
                }
            })

        // ImageLoaderUtil.displayImage(mContext, imageView, url, ImageLoaderUtil.getPhotoImageOption(), new ImageLoadingListener() {
        //     @Override
        //     public void onLoadingStarted(String imageUri, View view) {
        //
        //     }
        //
        //     @Override
        //     public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        //
        //     }
        //
        //     @Override
        //     public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
        //         int w = bitmap.getWidth();
        //         int h = bitmap.getHeight();
        //
        //         int newW;
        //         int newH;
        //         if (h > w * MAX_W_H_RATIO) {//h:w = 5:3
        //             newW = parentWidth / 2;
        //             newH = newW * 5 / 3;
        //         } else if (h < w) {//h:w = 2:3
        //             newW = parentWidth * 2 / 3;
        //             newH = newW * 2 / 3;
        //         } else {//newH:h = newW :w
        //             newW = parentWidth / 2;
        //             newH = h * newW / w;
        //         }
        //         setOneImageLayoutParams(imageView, newW, newH);
        //     }
        //
        //     @Override
        //     public void onLoadingCancelled(String imageUri, View view) {
        //
        //     }
        // });
        return false
    }

    override fun displayImage(imageView: RadiusImageView, url: String) {
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.cornerRadius = 6.dp
        imageView.borderWidth = 1
        imageView.borderColor = Color.parseColor("#ECECEC")
        Glide.with(imageView.context).load(url).into(imageView)
        // ImageLoaderUtil.getImageLoader(mContext).displayImage(url, imageView, ImageLoaderUtil.getPhotoImageOption());
    }

    override fun onClickImage(i: Int, url: String, urlList: List<String>) {
        //Toast.makeText(mContext, "点击了图片$url", Toast.LENGTH_SHORT).show()
    }

    companion object {
        protected const val MAX_W_H_RATIO = 3
    }
}
package com.android.debug.model.api


import com.android.debug.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val Api: BlogService by lazy {
    Retrofit.Builder()
            .baseUrl(BuildConfig.UNION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build().create(BlogService::class.java)
}

val UnionApi: UnionService by lazy {
    Retrofit.Builder()
            .baseUrl(BuildConfig.UNION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build().create(UnionService::class.java)
}

fun getOkHttpClient(): OkHttpClient {
    val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .readTimeout(60, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(60, TimeUnit.SECONDS) //设置写的超时时间
            .connectTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        builder.addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
    }
    val client = builder.build()
    return client
}

val cookieJar by lazy {
    CookiesManager()
}

//class ReceivedCookiesInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalResponse = chain.proceed(chain.request())
//        if (originalResponse.headers("set-cookie").isEmpty()) {
//            val cookieBuffer = StringBuffer()
//            val cookieInfo = originalResponse.headers("set-cookie")
//            LL.d("cookie= $cookieInfo")
//        }
//        return originalResponse
//    }
//
//}


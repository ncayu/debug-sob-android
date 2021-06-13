package com.android.debug.core.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var vb: VDB
    lateinit var viewModel: VM

    //临时用的dialog
    private var loadingDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = getVm()
        startObserve()
        initView()
        initData(savedInstanceState)
        innerObserve()
    }

    private fun innerObserve() {
        viewModel.loadingEvent.observe(this) {
            if (it) {
                showLoading()
            } else {
                dismissLoading()
            }
        }
        viewModel.pageNavigationEvent.observe(this,{
            navigateTo(it)
        })
        viewModel.destroyYourselfAndNavigation.observe(this,{
            navigateAndDestroy(it)
        })
    }

    abstract fun startObserve()

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun getVariableId(): Int

    private fun getVm(): VM {
        val viewModelClass: Class<BaseViewModel>
        val type = javaClass.genericSuperclass
        viewModelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<BaseViewModel>
            //获取第1个注解即VM的注解类型
        } else {
            //使用父类的类型
            BaseViewModel::class.java
        }
        val viewModel = ViewModelProvider(this).get(viewModelClass) as VM
        if (getVariableId() > 0) {
            vb.setVariable(getVariableId(), viewModel)
        }
        return viewModel;
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = ProgressDialog(this).apply {
                setMessage("加载中。。。")
            }
        }
        loadingDialog?.isShowing.takeIf {
            loadingDialog?.show()
            true
        }

    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    fun navigateTo(page: Any) {
        startActivity(Intent(this, page as Class<*>))
    }

    fun navigateAndDestroy(page: Any) {
        startActivity(Intent(this, page as Class<*>))
        finish()
    }
}
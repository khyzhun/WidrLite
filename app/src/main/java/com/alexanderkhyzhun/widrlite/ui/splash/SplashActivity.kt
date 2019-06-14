package com.alexanderkhyzhun.widrlite.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.MainActivity
import com.alexanderkhyzhun.widrlite.ui.auth.AuthActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class SplashActivity : BaseActivity(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        CoroutineScope(Dispatchers.Main).launch {
            delay(1500L / 2)
            presenter.handleUserAuthStatus()
        }
    }

    override fun onRedirectToMainPage() {
        startActivity(MainActivity.getIntent(this))
    }

    override fun onRedirectToLoginPage() {
        startActivity(AuthActivity.getIntent(this))
    }

    override fun showLoader() {
        /* code implementation */
    }

    override fun hideLoader() {
        /* code implementation */
    }

    override fun renderMessage(text: String) {
        showSnack(text)
    }

    override fun renderError(throwable: Throwable) {
        showSnack(throwable.message)
    }

    companion object {
        const val TAG = "SplashActivity"
        fun getIntent(context: Context?) = Intent(context, SplashActivity::class.java)
    }

}
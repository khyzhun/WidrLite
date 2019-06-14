package com.alexanderkhyzhun.widrlite.ui.profile

import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat.checkSelfPermission
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseActivity
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * @author Alexander Khyzhun
 * Created on 14 June, 2019
 */
@SuppressLint("CheckResult")
class ProfileFragment : BaseFragment(R.layout.fragment_profile), ProfileView {

    interface Callback {
        fun takePhoto()
        fun importPhoto()
    }

    val schedulers: Schedulers by inject()
    val glideManager: RequestManager by inject()

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    private var callback: Callback? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callback) {
            callback = context
        } else {
            throw RuntimeException("$context must implement Callback")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_profile_proposal_layout.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickProposal() }

        fragment_profile_iv_user_image.clicks()
            .debounce(BaseActivity.CLICK_DEBOUNCE, TimeUnit.MILLISECONDS)
            .compose(bindUntilDestroy())
            .observeOn(schedulers.mainThread())
            .subscribe { presenter.onClickImport() }
    }


    override fun renderName(userName: String) {
        fragment_profile_tv_name.text = userName
    }

    override fun renderImage(image: Bitmap) {
        glideManager
            .load(image)
            .apply(RequestOptions().circleCrop())
            .into(fragment_profile_iv_user_image)
    }

    override fun renderImage(image: String) {
        glideManager
            .load(image)
            .apply(RequestOptions().circleCrop())
            .into(fragment_profile_iv_user_image)
    }

    override fun onClickedTakePhoto() {

    }

    override fun onClickedImport() {
        callback?.importPhoto()
    }

    override fun onClickedSave() {

    }

    override fun onClickedProposal() {

    }

    override fun savePhotoFromCamera(photo: Bitmap) {
        presenter.savePhoto(photo)
    }

    override fun savePhotoFromStorage(photo: Bitmap) {
        presenter.savePhoto(photo)
    }

    override fun renderError(throwable: Throwable) {

    }

    override fun renderMessage(text: String) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    companion object {
        const val TAG = "ProfileFragment"
        const val PAGER_POSITION = 3



        fun newInstance() = ProfileFragment()
    }

}
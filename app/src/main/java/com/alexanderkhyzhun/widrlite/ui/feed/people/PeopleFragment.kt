package com.alexanderkhyzhun.widrlite.ui.feed.people

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.alexanderkhyzhun.widrlite.R
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.ui.mvp.BaseFragment
import com.alexanderkhyzhun.widrlite.utils.getEmojiByUnicode
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_people.*
import org.koin.android.ext.android.inject

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
class PeopleFragment : BaseFragment(R.layout.fragment_people), PeopleView {


    val schedulers: Schedulers by inject()

    @InjectPresenter
    lateinit var presenter: PeoplePresenter

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_people_tv_no_data.text = "No data ${getEmojiByUnicode(0x1f937)}"
    }

    override fun renderView() {

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
        const val TAG = "PeopleFragment"
        const val LABEL = "Trouver on profile"
        const val PAGER_POSITION = 3
        fun newInstance() = PeopleFragment()
    }
}
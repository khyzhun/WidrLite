package com.alexanderkhyzhun.widrlite.ui.chat

import com.alexanderkhyzhun.widrlite.data.models.ChatItem
import com.alexanderkhyzhun.widrlite.ui.mvp.ErrorView
import com.alexanderkhyzhun.widrlite.ui.mvp.LoadingView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Alexander Khyzhun
 * Created on 15 June, 2019
 */
interface ChatView : MvpView, ErrorView, LoadingView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderView(chat: ChatItem)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun renderCallSendIcon(text: CharSequence)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedCall()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedSend(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearEditText()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onClickedContact()

}
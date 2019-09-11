package org.mozilla.focus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.rocket.download.SingleLiveEvent
import org.mozilla.rocket.shopping.search.domain.GetShoppingSitesUseCase
import org.mozilla.rocket.shopping.search.ui.adapter.ShoppingSiteItem

class ShoppingSearchPromptViewModel(
    private val getShoppingSiteUseCase: GetShoppingSitesUseCase
) : ViewModel() {
    val isUrlShoppingSite = MutableLiveData<Boolean>()
    private val shoppingSiteList: LiveData<List<ShoppingSiteItem>> = getShoppingSiteUseCase.invoke()

    val openShoppingSearch = SingleLiveEvent<Unit>()

    fun checkIsShoppingSite(url: String) {
        isUrlShoppingSite.value = shoppingSiteList.value?.any { site -> site.displayUrl.contentEquals(url) }
    }
}
package dev.cardoso.quotesmvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteResponse
import dev.cardoso.quotesmvvm.domain.usecase.ListAllQuotesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(private val getAllQuotesUseCase: ListAllQuotesUseCase): ViewModel(){

    private val quoteResponseMutableStateFlow = MutableStateFlow(
        QuoteResponse(false, message = "",data= listOf())
    )

    val quoteResponse: StateFlow<QuoteResponse> = quoteResponseMutableStateFlow

    fun getQuotesList(token:String){
        viewModelScope.launch {
            val quoteResponse = getAllQuotesUseCase.getQuotesListUseCase(token).first()
            quoteResponse.let {
                if (quoteResponse != null){
                    quoteResponseMutableStateFlow.value=quoteResponse
                    //Log.e("",quoteResponse.data.toString())
                }
            }
        }
    }

}
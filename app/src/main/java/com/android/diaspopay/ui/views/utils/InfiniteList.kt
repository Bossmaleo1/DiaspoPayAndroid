package com.android.diaspopay.ui.views.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import com.android.diaspopay.data.model.data.Transfer
import com.android.diaspopay.presentation.viewModel.transfer.TransferViewModel
import com.android.diaspopay.ui.views.bottomnavigationviews.HistoryView
import com.android.diaspopay.ui.views.shimmer.TransferShimmer

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfiniteListTransferRemote(
    listState: LazyListState,
    listItems: List<Transfer>,
    paddingValues: PaddingValues,
    transferViewModel: TransferViewModel,
    sender: String,
    token: String
) {

    LazyColumn(
        contentPadding = paddingValues,
        state = listState
    ) {

        items(listItems) { transfer ->
            HistoryView(transfer)
        }

        items(count = 1) {
            TransferShimmer()
        }
    }

    listState.OnBottomReached(buffer = 2) {
        transferViewModel.getTransfer(
            sender = sender,
            transferViewModel.currentPage.value + 1,
            pagination = true,
           token)
    }

}

@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore : () -> Unit
){
    // Buffer must be positive.
    // Or our list will never reach the bottom.
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {
            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                // list is empty
                // return false here if loadMore should not be invoked if the list is empty
                return@derivedStateOf true
            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}
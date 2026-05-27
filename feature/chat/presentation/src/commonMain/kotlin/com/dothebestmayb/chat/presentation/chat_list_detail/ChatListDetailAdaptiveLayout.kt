@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.dothebestmayb.chat.presentation.chat_list_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.dothebestmayb.chat.presentation.create_chat.CreateChatRoot
import com.dothebestmayb.core.designsystem.theme.extended
import com.dothebestmayb.core.presentation.util.DialogSheetScopedViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatListDetailAdaptiveLayout(
    chatListDetailViewModel: ChatListDetailViewModel = koinViewModel()
) {
    val sharedState by chatListDetailViewModel.state.collectAsStateWithLifecycle()

    val scaffoldDirective = createNoSpacingPaneScaffoldDirective()
    val scaffoldNavigator = rememberListDetailPaneScaffoldNavigator(
        scaffoldDirective = scaffoldDirective,
    )
    val scope = rememberCoroutineScope()

    val navEventState = rememberNavigationEventState(NavigationEventInfo.None)

    /**
     * Navigation Event / BackHandler 계열은 대체로 enabled 된 handler들 중
     * Composition에서 나중에 구성된 handler가 먼저 back을 처리한다.
     * 앱 전체 handler, NavHost/화면 전환 handler, list-detail 내부 handler,
     * dialog 닫기 handler처럼 여러 handler가 함께 있을 때는 순서가 곧 우선순위가 된다.
     * handler를 if로 넣었다 뺐다 하면 recomposition 시점마다 사라졌다가 다시 등록될 수 있다.
     * 그러면 등록 순서와 우선순위가 흔들리므로 NavigationBackHandler의 위치는 고정한다.
     * 현재 처리 가능 여부만 isBackEnabled로 바꾸고, false이면 바깥 handler가 back을 처리하게 둔다.
     */
    NavigationBackHandler(
        state = navEventState,
        isBackEnabled = scaffoldNavigator.canNavigateBack(),
        onBackCompleted = {
            scope.launch {
                scaffoldNavigator.navigateBack()
            }
        }
    )

    ListDetailPaneScaffold(
        directive = scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.extended.surfaceLower),
        listPane = {
            AnimatedPane {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(100) { chatIndex ->
                        Text(
                            text = "Chat $chatIndex",
                            modifier = Modifier
                                .clickable {
                                    chatListDetailViewModel.onAction(
                                        ChatListDetailAction.OnChatClick(
                                            chatIndex.toString()
                                        )
                                    )
                                    scope.launch {
                                        scaffoldNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                                    }
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        },
        detailPane = {
            AnimatedPane {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    sharedState.selectedChatId?.let {
                        Text(
                            text = it,
                        )
                    }
                }
            }
        }
    )

    DialogSheetScopedViewModel(
        visible = sharedState.dialogState is DialogState.CreateChat
    ) {
        CreateChatRoot(
            onDismiss = {
                chatListDetailViewModel.onAction(ChatListDetailAction.OnDismissCurrentDialog)
            }
        )
    }
}

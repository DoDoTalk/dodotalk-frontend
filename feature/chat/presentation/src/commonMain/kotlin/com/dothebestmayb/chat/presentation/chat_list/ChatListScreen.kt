package com.dothebestmayb.chat.presentation.chat_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dothebestmayb.chat.presentation.chat_list.components.ChatListHeader
import com.dothebestmayb.chat.presentation.chat_list.components.ChatListItemUi
import com.dothebestmayb.chat.presentation.chat_list.components.EmptyChatSection
import com.dothebestmayb.chat.presentation.model.ChatUi
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkHorizontalDivider
import com.dothebestmayb.core.designsystem.components.buttons.DoDoTalkFloatingActionButton
import com.dothebestmayb.core.designsystem.components.dialogs.DestructiveConfirmationDialog
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import dodotalk.feature.chat.presentation.generated.resources.Res
import dodotalk.feature.chat.presentation.generated.resources.cancel
import dodotalk.feature.chat.presentation.generated.resources.create_chat
import dodotalk.feature.chat.presentation.generated.resources.do_you_want_logout
import dodotalk.feature.chat.presentation.generated.resources.do_you_want_logout_desc
import dodotalk.feature.chat.presentation.generated.resources.logout
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatListRoot(
    onChatClick: (ChatUi) -> Unit,
    onConfirmLogoutClick: () -> Unit,
    onCreateChatClick: () -> Unit,
    onProfileSettingsClick: () -> Unit,
    viewModel: ChatListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ChatListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ChatListAction.OnChatClick -> onChatClick(action.chat)
                ChatListAction.OnConfirmLogout -> onConfirmLogoutClick()
                ChatListAction.OnCreateChatClick -> onCreateChatClick()
                ChatListAction.OnProfileSettingsClick -> onProfileSettingsClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        snackbarHostState = snackbarHostState,
    )
}

@Composable
fun ChatListScreen(
    state: ChatListState,
    onAction: (ChatListAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.extended.surfaceLower,
        contentWindowInsets = WindowInsets.safeDrawing,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            DoDoTalkFloatingActionButton(
                onClick = {
                    onAction(ChatListAction.OnCreateChatClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(Res.string.create_chat),
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ChatListHeader(
                localParticipant = state.localParticipant,
                isUserMenuOpen = state.isUserMenuOpen,
                onUserAvatarClick = {
                    onAction(ChatListAction.OnUserAvatarClick)
                },
                onLogoutClick = {
                    onAction(ChatListAction.OnLogoutClick)
                },
                onDismissMenu = {
                    onAction(ChatListAction.OnDismissUserMenu)
                },
                onProfileSettingsClick = {
                    onAction(ChatListAction.OnProfileSettingsClick)
                }
            )
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                state.chats.isEmpty() -> {
                    EmptyChatSection(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                            )
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(
                            items = state.chats,
                            key = { it.id },
                        ) { chatUi ->
                            ChatListItemUi(
                                chat = chatUi,
                                isSelected = chatUi.id == state.selectedChatId,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onAction(ChatListAction.OnChatClick(chatUi))
                                    }
                            )
                            DoDoTalkHorizontalDivider()
                        }
                    }
                }
            }
        }
    }

    if (state.showLogoutConfirmation) {
        DestructiveConfirmationDialog(
            title = stringResource(Res.string.do_you_want_logout),
            description = stringResource(Res.string.do_you_want_logout_desc),
            confirmButtonText = stringResource(Res.string.logout),
            cancelButtonText = stringResource(Res.string.cancel),
            onDismiss = {
                onAction(ChatListAction.OnDismissLogoutDialog)
            },
            onCancelClick = {
                onAction(ChatListAction.OnDismissLogoutDialog)
            },
            onConfirmClick = {
                onAction(ChatListAction.OnConfirmLogout)
            },
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DoDoTalkTheme {
        ChatListScreen(
            state = ChatListState(),
            onAction = {},
            snackbarHostState = remember { SnackbarHostState() }
        )
    }
}

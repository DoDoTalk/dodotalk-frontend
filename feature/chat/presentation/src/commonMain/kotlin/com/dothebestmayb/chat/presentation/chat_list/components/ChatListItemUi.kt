package com.dothebestmayb.chat.presentation.chat_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dothebestmayb.chat.domain.models.Chat
import com.dothebestmayb.chat.domain.models.ChatMessage
import com.dothebestmayb.chat.presentation.mappers.toUi
import com.dothebestmayb.chat.presentation.model.ChatUi
import com.dothebestmayb.core.designsystem.components.avatar.ChatParticipantUi
import com.dothebestmayb.core.designsystem.components.avatar.DoDoTalkStackedAvatars
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import com.dothebestmayb.core.designsystem.theme.titleXSmall
import dodotalk.feature.chat.presentation.generated.resources.Res
import dodotalk.feature.chat.presentation.generated.resources.group_chat
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Instant

@Composable
fun ChatListItemUi(
    chat: ChatUi,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val isGroupChat = chat.otherParticipants.size > 1

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min) // Content 중 가장 짧은 길이의 height를 max height로 설정
            .background(
                color = if (isSelected) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.extended.surfaceLower
                }
            )
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                DoDoTalkStackedAvatars(
                    avatars = chat.otherParticipants,
                )
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = if (isGroupChat) {
                                stringResource(Res.string.group_chat)
                            } else {
                                chat.otherParticipants.first().username
                            },
                            style = MaterialTheme.typography.titleXSmall,
                            color = MaterialTheme.colorScheme.extended.textPrimary,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                        if (chat.lastActivityAt != null) {
                            Text(
                                text = chat.lastActivityAt.formatChatListTime(),
                                style = MaterialTheme.typography.titleXSmall,
                            )
                        }
                    }
                    if (isGroupChat) {
                        val formattedUsernames = remember(chat.otherParticipants, chat.localParticipant) {
                            (listOf(chat.localParticipant) + chat.otherParticipants).joinToString {
                                it.username
                            }
                        }
                        Text(
                            text = formattedUsernames,
                            color = MaterialTheme.colorScheme.extended.textPlaceholder,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            if (chat.lastMessage != null) {
                val previewMessage = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.extended.textSecondary,
                        )
                    ) {
                        append(chat.lastMessageSenderUsername + " : ")
                    }
                    append(chat.lastMessage.content)
                }
                Text(
                    text = previewMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.extended.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        // 선택된 채팅인지 여부를 나타내는 UI
        Box(
            modifier = Modifier
                .alpha(if (isSelected) 1f else 0f) // 보이는지 여부와 상관 없이 채팅방 UI가 동일한 size를 가지도록 하기 위해 visibility 대신 alpha로 설정
                .background(MaterialTheme.colorScheme.primary)
                .width(4.dp)
                .fillMaxHeight() // IntrinsicSize.Min 때문에 위 Column의 높이와 같아짐
        )
    }
}

private fun Instant.formatChatListTime(
    now: Instant = Clock.System.now(),
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String {
    val datetime = toLocalDateTime(timeZone)
    val nowDateTime = now.toLocalDateTime(timeZone)

    return when {
        datetime.date == nowDateTime.date -> {
            "${datetime.hour.twoDigits()}:${datetime.minute.twoDigits()}"
        }

        else -> {
            "${datetime.year}.${datetime.month.number.twoDigits()}.${datetime.month.number.twoDigits()}"
        }
    }
}

private fun Int.twoDigits(): String = toString().padStart(2, '0')

@Preview
@Composable
private fun ChatListItemPreview() {
    DoDoTalkTheme {
        ChatListItemUi(
            isSelected = true,
            modifier = Modifier
                .fillMaxWidth(),
            chat = ChatUi(
                id = "1",
                localParticipant = ChatParticipantUi(
                    id = "1",
                    username = "김민수",
                    initials = "민수",
                ),
                otherParticipants = listOf(
                    ChatParticipantUi(
                        id = "2",
                        username = "MyLongUserNameAsThis",
                        initials = "ML",
                    ),
                    ChatParticipantUi(
                        id = "3",
                        username = "김치찌개삼겹살된장찌개",
                        initials = "김찌",
                    ),
                ),
                lastMessage = ChatMessage(
                    id = "1",
                    chatId = "1",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi...",
                    createdAt = Clock.System.now(),
                    senderId = "1",
                ),
                lastMessageSenderUsername = "김민수",
                lastActivityAt = Clock.System.now().minus(2.days)
            ),
        )
    }
}

@Preview
@Composable
private fun ChatListItemPreview2() {
    DoDoTalkTheme {
        ChatListItemUi(
            isSelected = true,
            modifier = Modifier
                .fillMaxWidth(),
            chat = ChatUi(
                id = "1",
                localParticipant = ChatParticipantUi(
                    id = "1",
                    username = "김민수",
                    initials = "민수",
                ),
                otherParticipants = listOf(
                    ChatParticipantUi(
                        id = "2",
                        username = "MyLongUserNameAsThis",
                        initials = "ML",
                    ),
                ),
                lastMessage = ChatMessage(
                    id = "1",
                    chatId = "1",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi...",
                    createdAt = Clock.System.now(),
                    senderId = "1",
                ),
                lastMessageSenderUsername = "김민수",
                lastActivityAt = Clock.System.now().minus(3.hours)
            ),
        )
    }
}

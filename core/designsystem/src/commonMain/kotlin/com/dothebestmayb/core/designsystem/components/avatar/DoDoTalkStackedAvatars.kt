package com.dothebestmayb.core.designsystem.components.avatar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme

@Composable
fun DoDoTalkStackedAvatars(
    avatars: List<AvatarUi>,
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.SMALL,
    maxVisible: Int = 2,
    overlapPercentage: Float = 0.4f,
) {
    val overlapOffset = -(size.dp * overlapPercentage)

    val visibleAvatars = avatars.take(maxVisible)
    val remainingCount = (avatars.size - maxVisible).coerceAtLeast(0)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(overlapOffset),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        visibleAvatars.forEach { avatarUi ->
            DoDoTalkAvatarPhoto(
                displayText = avatarUi.initials,
                size = size,
                imageUrl = avatarUi.imageUrl,
            )
        }

        if (remainingCount > 0) {
            DoDoTalkAvatarPhoto(
                displayText = "$remainingCount+",
                size = size,
                textColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
private fun DoDoTalkStackedAvatarsPreview() {
    DoDoTalkTheme {
        DoDoTalkStackedAvatars(
            avatars = listOf(
                AvatarUi(
                    id = "1",
                    username = "DoDoTalk",
                    initials = "DT",
                ),
                AvatarUi(
                    id = "2",
                    username = "MinSu",
                    initials = "MS",
                ),
                AvatarUi(
                    id = "3",
                    username = "JohnDo",
                    initials = "JD",
                ),
                AvatarUi(
                    id = "4",
                    username = "SuDo",
                    initials = "SD",
                ),
            )
        )
    }
}

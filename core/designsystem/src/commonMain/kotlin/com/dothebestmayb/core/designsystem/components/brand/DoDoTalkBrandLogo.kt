package com.dothebestmayb.core.designsystem.components.brand

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dodotalk.core.designsystem.generated.resources.Res
import dodotalk.core.designsystem.generated.resources.logo_dodotalk
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DoDoTalkBrandLogo(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = vectorResource(Res.drawable.logo_dodotalk),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

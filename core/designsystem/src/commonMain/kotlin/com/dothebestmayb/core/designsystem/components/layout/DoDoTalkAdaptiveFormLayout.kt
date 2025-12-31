package com.dothebestmayb.core.designsystem.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dothebestmayb.core.designsystem.components.brand.DoDoTalkBrandLogo
import com.dothebestmayb.core.designsystem.theme.DoDoTalkTheme
import com.dothebestmayb.core.designsystem.theme.extended
import com.dothebestmayb.core.presentation.util.DeviceConfiguration
import com.dothebestmayb.core.presentation.util.clearFocusOnTap
import com.dothebestmayb.core.presentation.util.currentDeviceConfiguration

@Composable
fun DoDoTalkAdaptiveFormLayout(
    headerText: String,
    logo: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    formContent: @Composable ColumnScope.() -> Unit,
) {
    val configuration = currentDeviceConfiguration()
    val headerColor = if (configuration == DeviceConfiguration.MOBILE_LANDSCAPE) {
        MaterialTheme.colorScheme.onBackground
    } else {
        MaterialTheme.colorScheme.extended.textPrimary
    }

    when (configuration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            DoDoTalkSurface(
                modifier = modifier
                    .clearFocusOnTap()
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .consumeWindowInsets(WindowInsets.displayCutout), // cutout: 펀치홀 같은 거
                header = {
                    Spacer(modifier = Modifier.height(32.dp))
                    logo()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                AuthHeaderSection(
                    headerText = headerText,
                    headerColor = headerColor,
                    errorText = errorText,
                )
                Spacer(modifier = Modifier.height(24.dp))
                formContent()
            }
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
                    .fillMaxSize()
                    .clearFocusOnTap()
                    .background(MaterialTheme.colorScheme.background)
                    .consumeWindowInsets(WindowInsets.displayCutout)
                    // 내비게이션 바가 제스처 대신 버튼으로 되어 있는 경우 겹치지 않도록 padding 추가
                    .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues())
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    logo()
                    AuthHeaderSection(
                        headerText = headerText,
                        headerColor = headerColor,
                        errorText = errorText,
                        headerTextAlignment = TextAlign.Start,
                    )
                }
                DoDoTalkSurface(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    formContent()
                    // gesture navigation과 content가 겹치지 않도록 하단에 공간 추가
                    Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                }
            }
        }

        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .clearFocusOnTap()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                logo()
                Column(
                    modifier = Modifier
                        .widthIn(max = 480.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AuthHeaderSection(
                        headerText = headerText,
                        headerColor = headerColor,
                        errorText = errorText
                    )
                    formContent()
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.AuthHeaderSection(
    headerText: String,
    headerColor: Color,
    errorText: String? = null,
    headerTextAlignment: TextAlign = TextAlign.Center
) {
    Text(
        text = headerText,
        style = MaterialTheme.typography.titleLarge,
        color = headerColor,
        textAlign = headerTextAlignment,
        modifier = Modifier.fillMaxWidth()
    )
    AnimatedVisibility(
        visible = errorText != null,
    ) {
        if (errorText != null) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = headerTextAlignment,
            )
        }
    }
}

@Preview(
    name = "phone",
    device = Devices.PHONE
)
@Preview(
    name = "phone_landscape",
    widthDp = 1000,
    heightDp = 400,
)
@Preview(
    name = "tablet",
    device = Devices.TABLET
)
@Preview(
    name = "desktop",
    device = Devices.DESKTOP
)
@Composable
private fun DoDoTalkAdaptiveFormLightLayoutPreview() {
    DoDoTalkTheme {
        DoDoTalkAdaptiveFormLayout(
            headerText = "Welcome DoDoTalk!",
            errorText = "Login failed!",
            logo = {
                DoDoTalkBrandLogo()
            },
            formContent = {
                Text(
                    text = "Sample form title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}

@Preview(
    name = "phone",
    device = Devices.PHONE
)
@Preview(
    name = "phone_landscape",
    widthDp = 1000,
    heightDp = 400,
)
@Preview(
    name = "tablet",
    device = Devices.TABLET
)
@Preview(
    name = "desktop",
    device = Devices.DESKTOP
)
@Composable
private fun DoDoTalkAdaptiveFormDarkLayoutPreview() {
    DoDoTalkTheme(darkTheme = true) {
        DoDoTalkAdaptiveFormLayout(
            headerText = "Welcome DoDoTalk!",
            errorText = "Login failed!",
            logo = {
                DoDoTalkBrandLogo()
            },
            formContent = {
                Text(
                    text = "Sample form title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Sample form title 2",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}


package org.dtcm.work.productdetail.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getPlatformContext(): Any = LocalContext.current
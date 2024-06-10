package org.dtcm.work.productdetail.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable



actual fun openInMaps(context: Any, address: String) {
    val androidContext = context as Context
    val intentUri = Uri.parse("geo:0,0?q=${Uri.encode(address)}")
    val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    androidContext.startActivity(mapIntent)
}
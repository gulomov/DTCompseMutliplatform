package org.dtcm.work.productdetail.helper

import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.UIKit.UIApplication
import platform.Foundation.*

actual fun openInMaps(context: Any, address: String) {
    val encodedAddress = address.encodeURL() ?: address
    val url = NSURL(string = "http://maps.apple.com/?q=$encodedAddress")
    if (UIApplication.sharedApplication.canOpenURL(url)) {
        UIApplication.sharedApplication.openURL(url)
    } else {
        println("Can't open in any application")
    }
}


fun String.encodeURL(): String? {
    val allowedCharacterSet = NSCharacterSet.URLQueryAllowedCharacterSet
    return (this as NSString).stringByAddingPercentEncodingWithAllowedCharacters(allowedCharacterSet)
}
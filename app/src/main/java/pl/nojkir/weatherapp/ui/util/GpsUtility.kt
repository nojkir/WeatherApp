package pl.nojkir.weatherapp.ui.util

import android.content.Context
import pub.devrel.easypermissions.EasyPermissions
import java.util.jar.Manifest

object GpsUtility {

    fun hasLocationPermissions(context: Context) =
        EasyPermissions.hasPermissions(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        )


}
package pl.nojkir.weatherapp.ui.util

sealed class Resource<T>(
    val data: T? = null,
    val messge: String? = null
) {
    class Success <T>(data: T) : Resource<T>(data)
    class Error <T>(messge: String, data: T? = null) :  Resource<T>(data,messge)
    class Loading <T>: Resource<T>()
}
package ru.andrewkir.vktestcase.common

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun <activity : Activity> Activity.startActivityClearBackStack(activityClass: Class<activity>) {
    Intent(this, activityClass).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Fragment.openLink(link: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    this.startActivity(browserIntent)
}

//fun Fragment.handleApiError(
//    error: ApiResponse.OnErrorResponse,
//    retry: (() -> Unit)? = null
//) {
//    if (error.isNetworkFailure) {
//        requireView().createRetrySnackbar(
//            "Check connection", retry
//        )
//        return
//    }
//
//    if (error.body == null && error.code == null) {
//        requireView().createRetrySnackbar("Retry")
//    }
//
//    val parsedError = try {
//        if (error.body == null) ""
//        else {
//            val jsonObj = JSONObject(error.body.string())
//            jsonObj.getString("error")
//        }
//    } catch (ex: JSONException) {
//        ""
//    }
//
//    if (error.code == 401) {
//        if (this is LoginFragment) {
//            if (parsedError.isNotEmpty()) requireView().createRetrySnackbar(parsedError)
//            else requireView().createRetrySnackbar("Попробуйте ещё раз")
//        } else {
//            Toast.makeText(
//                requireContext(),
//                "Необходимо войти снова",
//                Toast.LENGTH_SHORT
//            ).show()
//            (this as BaseFragment<*, *, *>).userLogout()
//        }
//        return
//    }
//
//    if (parsedError.isNotEmpty()) requireView().createRetrySnackbar(parsedError)
//    else requireView().createRetrySnackbar("Ошибка на стороне сервера")
//}

fun View.createRetrySnackbar(msg: String, retry: (() -> Unit)? = null) {
    val snack = Snackbar.make(this, msg, Snackbar.LENGTH_LONG)
    retry?.let {
        snack.setAction("Повторить") {
            it()
        }
    }
    snack.show()
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
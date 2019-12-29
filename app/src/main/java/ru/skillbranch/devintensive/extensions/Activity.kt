package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.utils.Utils.KEYBOARD_VISIBLE_THRESHOLD_DP
import ru.skillbranch.devintensive.utils.Utils.dip

fun Activity.hideKeyboard(){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(getActivityRoot().windowToken, 0)
}



fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()

    val activityRoot = getActivityRoot()
    val visibleThreshold = dip(KEYBOARD_VISIBLE_THRESHOLD_DP)

    activityRoot.getWindowVisibleDisplayFrame(r)

    val heightDiff = activityRoot.rootView.height - r.height()

    return heightDiff > visibleThreshold;
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpened()//this.isKeyboardOpen()
}

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}
fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}
fun Activity.isKeyboardOpened(): Boolean {
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = Math.round(this.convertDpToPx(50F))
    return heightDiff > marginOfError
}

fun Activity.getActivityRoot(): View {
    return (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0);
}


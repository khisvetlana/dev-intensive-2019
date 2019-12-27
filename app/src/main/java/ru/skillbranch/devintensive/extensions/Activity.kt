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

fun Activity.hideKeyboard(parent: View?){
    parent?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
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
    return !this.isKeyboardOpen()
}

fun Activity.getActivityRoot(): View {
    return (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0);
}


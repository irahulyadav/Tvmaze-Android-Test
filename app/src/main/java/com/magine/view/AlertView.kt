package com.magine.view

import android.app.AlertDialog
import android.content.Context
import android.text.Html
import android.view.ContextThemeWrapper
import com.magine.R


/**
 * Created by Indpro on 12/4/2014.
 */
open class AlertView {

    companion object {

        @JvmOverloads
        fun showConfirmDialog(
            context: Context,
            title: String = context.getString(R.string.app_name),
            message: String,
            button: String = "OK",
            action: () -> Unit
        ): AlertDialog {
            val ctw = ContextThemeWrapper(context, R.style.AlertDialogCustom)
            val builder = AlertDialog.Builder(ctw)
            builder.setNeutralButton(button) { dialog, whichButton ->
                action()
            }
            val alert = builder.create()
            alert.setMessage(Html.fromHtml(message))
            alert.setTitle(title)
            alert.setCancelable(false)
            alert.show()
            return alert
        }

    }
}

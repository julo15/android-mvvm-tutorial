package io.github.julo15.mvvmtutorial.imageupload

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import io.github.julo15.mvvmtutorial.databinding.DialogPickImageBinding

/**
 * Opens a dialog to enter a url.
 */
fun showPickerDialog(context: Context, onComplete: (uri: Uri?) -> Unit) {
    AlertDialog.Builder(context).apply {
        val dialogBinding = DialogPickImageBinding.inflate(LayoutInflater.from(context))
        setView(dialogBinding.root)
        setPositiveButton("Add") { dialog, which ->
            val uri = try {
                Uri.parse(dialogBinding.urlEditText.text.toString())
            } catch (e: Exception) {
                null
            }
            onComplete(uri)
        }
        setNegativeButton("Cancel") { dialog, which ->
            onComplete(null)
        }
    }.show()
}

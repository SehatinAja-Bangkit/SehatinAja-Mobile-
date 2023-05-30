package com.example.sehatinaja.ui.camera

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.sehatinaja.R

/**
 * Shows OK/Cancel confirmation dialog about camera permission.
 */
class ConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(activity)
            .setMessage(R.string.request_permission)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                requireParentFragment().requestPermissions(arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                requireParentFragment().activity?.finish()
            }
            .create()
}
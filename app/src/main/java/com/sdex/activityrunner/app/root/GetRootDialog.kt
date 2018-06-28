package com.sdex.activityrunner.app.root

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.sdex.activityrunner.R
import com.sdex.activityrunner.premium.PurchaseActivity

class GetRootDialog : DialogFragment() {

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return AlertDialog.Builder(activity!!)
      .setTitle(R.string.pro_version_dialog_title)
      .setMessage(R.string.pro_version_unlock_root_integration)
      .setNeutralButton(R.string.root_check_compatibility)
      { _, _ -> CheckRootActivity.start(activity!!) }
      .setPositiveButton(R.string.pro_version_get)
      { _, _ -> PurchaseActivity.start(activity!!) }
      .create()
  }

  companion object {

    const val TAG = "GetRootDialog"

    fun newInstance(): GetRootDialog {
      return GetRootDialog()
    }
  }
}
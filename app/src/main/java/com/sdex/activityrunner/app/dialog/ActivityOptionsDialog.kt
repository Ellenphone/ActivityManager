package com.sdex.activityrunner.app.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sdex.activityrunner.R
import com.sdex.activityrunner.app.ActivityLauncher
import com.sdex.activityrunner.app.ActivityModel
import com.sdex.activityrunner.shortcut.AddShortcutDialogActivity
import kotlinx.android.synthetic.main.dialog_activity_menu.*

class ActivityOptionsDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_activity_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = requireArguments().getSerializable(ARG_MODEL) as ActivityModel

        val launcher = ActivityLauncher(requireActivity())

        activity_name.text = model.name
        action_activity_add_shortcut.setOnClickListener {
            AddShortcutDialogActivity.start(requireContext(), model)
            dismiss()
        }
//        action_activity_launch_with_params.visibility =
//            if (model.exported) View.VISIBLE else View.GONE
//        action_activity_launch_with_params.setOnClickListener {
//            launcher.launchActivityWithParams(model)
//            dismiss()
//        }
        action_activity_launch_with_root.setOnClickListener {
            launcher.launchActivityWithRoot(model)
            dismiss()
        }
    }

    companion object {

        const val TAG = "ActivityMenuDialog"

        private const val ARG_MODEL = "arg_model"

        fun newInstance(model: ActivityModel): ActivityOptionsDialog {
            val dialog = ActivityOptionsDialog()
            dialog.arguments = Bundle(1).apply {
                putSerializable(ARG_MODEL, model)
            }
            return dialog
        }
    }
}
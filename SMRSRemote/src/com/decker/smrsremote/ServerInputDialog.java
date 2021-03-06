package com.decker.smrsremote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

@SuppressLint("InflateParams")
public class ServerInputDialog extends DialogFragment {

	public NoticeDialogListener listener;

	public interface NoticeDialogListener {
		public void onDialogPositiveClick(StarMadeServer serverObject);

		public void onDialogNegativeClick(DialogFragment dialog);
	}

	NoticeDialogListener mListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			listener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Input Server Info");
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View DialogView = inflater.inflate(
				R.layout.server_dialog_fragment, null);
		/*
		 * Inflate and set the layout for the dialog // Pass null as the parent
		 * view because its going in the dialog layout
		 */
		builder.setView(DialogView).setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						EditText newServerName = (EditText) DialogView
								.findViewById(R.id.ServerName);
						EditText newServerIP = (EditText) DialogView
								.findViewById(R.id.ServerIP);
						StarMadeServer newServer = new StarMadeServer(
								newServerName.getText().toString(), newServerIP
										.getText().toString());
						listener.onDialogPositiveClick(newServer);

					}
				});
		return builder.create();
	}

}

package com.huskybus;

import java.util.ArrayList;
import java.util.List;

import com.huskybus.R;
import com.huskybus.datastructures.BusRoute;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MultiSpinner extends Spinner implements OnMultiChoiceClickListener, OnCancelListener {

	private ArrayList<BusRoute> busRoutes;
	private boolean[] selected;
	private String defaultText;
	private MultiSpinnerListener listener;

	public MultiSpinner(Context context) {
		super(context);
	}

	public MultiSpinner(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
	}

	public MultiSpinner(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		if (isChecked)
			selected[which] = true;
		else
			selected[which] = false;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		// refresh text on spinner
		StringBuffer spinnerBuffer = new StringBuffer();
		boolean someUnselected = false;
		for (int i = 0; i < busRoutes.size(); i++) {
			if (selected[i] == true) {
				spinnerBuffer.append(busRoutes.get(i).getDescription());
				spinnerBuffer.append(", ");
			} else {
				someUnselected = true;
			}
		}
		String spinnerText;
		if (someUnselected) {
			spinnerText = spinnerBuffer.toString();
			if (spinnerText.length() > 2)
				spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
		} else {
			spinnerText = defaultText;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
				android.R.layout.simple_spinner_item,
				new String[] { spinnerText });
		setAdapter(adapter);
		listener.onItemsSelected(selected);
	}

	@Override
	public boolean performClick() {
		if(selected.length != 0){
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			CharSequence[] cs = new CharSequence[busRoutes.size()];
			for(int i = 0; i < busRoutes.size(); i++){
				cs[i] = busRoutes.get(i).getDescription();
			}
			builder.setMultiChoiceItems(
					cs, selected, this);
			builder.setPositiveButton(R.string.ok,
					new DialogInterface.OnClickListener() {
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			builder.setOnCancelListener(this);
			builder.show();
		}
		return true;
	}

	public void setItems(ArrayList<BusRoute> items, String allText,
			MultiSpinnerListener listener) {
		this.busRoutes = items;
		this.defaultText = allText;
		this.listener = listener;
		// all deselected by default
		selected = new boolean[items.size()];
		for (int i = 0; i < selected.length; i++) {
			selected[i] = false;
		}
		// all text on the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
				android.R.layout.simple_spinner_item, new String[] { allText });
		setAdapter(adapter);
	}

	public interface MultiSpinnerListener {
		public void onItemsSelected(boolean[] selected);
	}
}
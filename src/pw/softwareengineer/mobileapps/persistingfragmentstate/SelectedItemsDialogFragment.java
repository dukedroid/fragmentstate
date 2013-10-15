package pw.softwareengineer.mobileapps.persistingfragmentstate;




import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SelectedItemsDialogFragment extends DialogFragment {
	public static final String TAG_SELECTED_ITEMS_DIALOG = "selectedItemsDialog";
	public static final String KEY_SELECTED_ITEMS = "selected_items";
	private CharSequence[] items;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		items = getArguments().getCharSequenceArray(KEY_SELECTED_ITEMS);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_selected_items).setItems(items, null)
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   ((SelectionListFragment)getTargetFragment()).onDialogDismissedByUser();
                   }
               });
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

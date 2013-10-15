package pw.softwareengineer.mobileapps.persistingfragmentstate;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class SelectionListFragment extends ListFragment implements
		OnItemClickListener {

	private ListView mListView;
	private List<CharSequence> selectedItems = new ArrayList<CharSequence>();
	private ActionBarActivity parentActivity;

	private boolean dialogFragmentShown = false;

	@Override
	public void onAttach(Activity activity) {
		Log.i(SelectionListFragment.class.getSimpleName(), "onAttach()");
		super.onAttach(activity);
		parentActivity = (ActionBarActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setListAdapter(ArrayAdapter.createFromResource(parentActivity,
				R.array.phonetic_alphabets, R.layout.list_item));
		Log.i(SelectionListFragment.class.getSimpleName(), "onCreate()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(SelectionListFragment.class.getSimpleName(), "onCreateView()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// If a dialog fragment was being shown at the time the configuration change
		// occurred, that dialog should
		// be re-shown after the configuration change:
		if (dialogFragmentShown) {
			showSelectedItems();
		}

		mListView = getListView();
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mListView.setOnItemClickListener(this);

		Log.i(SelectionListFragment.class.getSimpleName(),
				"onActivityCreated()");
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_selection_list, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_selections:
			showSelectedItems();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showSelectedItems() {
		if (selectedItems.size() < 1) {
			Toast.makeText(parentActivity, "No item selected",
					Toast.LENGTH_SHORT).show();
			return;
		}
		dialogFragmentShown = true;
		CharSequence[] selectedItemsArray = new CharSequence[0];
		selectedItemsArray = selectedItems.toArray(selectedItemsArray);
		SelectedItemsDialogFragment dialogFragment = new SelectedItemsDialogFragment();
		Bundle args = new Bundle();
		args.putCharSequenceArray(SelectedItemsDialogFragment.KEY_SELECTED_ITEMS, selectedItemsArray);
		dialogFragment.setArguments(args);
		dialogFragment.setRetainInstance(true);
		dialogFragment.setTargetFragment(this, 0);
		dialogFragment.show(parentActivity.getSupportFragmentManager(),
				SelectedItemsDialogFragment.TAG_SELECTED_ITEMS_DIALOG);
	}

	public void onDialogDismissedByUser() {
		dialogFragmentShown = false;
	}

	// ListView callback:
	@Override
	public void onItemClick(AdapterView<?> arg0, View childView,
			int adapterPosition, long id) {
		CharSequence selectedItem = ((TextView) childView).getText();
		if (mListView.getCheckedItemPositions().get(adapterPosition)) {
			selectedItems.add(selectedItem);
		} else {
			selectedItems.remove(selectedItem);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i(SelectionListFragment.class.getSimpleName(), "onStart()");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(SelectionListFragment.class.getSimpleName(), "onResume()");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(SelectionListFragment.class.getSimpleName(), "onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i(SelectionListFragment.class.getSimpleName(), "onStop()");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(SelectionListFragment.class.getSimpleName(), "onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(SelectionListFragment.class.getSimpleName(), "onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i(SelectionListFragment.class.getSimpleName(), "onDetach()");
	}

}

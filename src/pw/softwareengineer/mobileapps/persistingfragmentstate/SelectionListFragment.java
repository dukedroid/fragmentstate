package pw.softwareengineer.mobileapps.persistingfragmentstate;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
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
		super.onAttach(activity);
		parentActivity = (ActionBarActivity) activity;
		
		System.out.println("onAttach()");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setListAdapter(ArrayAdapter.createFromResource(parentActivity,
				R.array.phonetic_alphabets,
				R.layout.list_item));
		
		System.out.println("onCreate()");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("onCreateView()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
			if(dialogFragmentShown) {
				showSelectedItems();
			}
		
		mListView = getListView();
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mListView.setOnItemClickListener(this);
		
		System.out.println("onActivityCreated()");

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
		dialogFragment.setItems(selectedItemsArray);
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
		System.out.println("onStart()");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("onResume()");
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("onStop()");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy()");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		System.out.println("onDetach()");
	}

}

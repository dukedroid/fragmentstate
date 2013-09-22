package pw.softwareengineer.mobileapps.persistingfragmentstate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	Fragment selectionListFragment;
	private static final String fragmentName = "selection_list_fragment";// Key used to store selectionListFragment
		// instance in the bundle during configuration changes.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) {
			selectionListFragment = fragmentManager.getFragment(
					savedInstanceState, fragmentName);		
		}

		if (selectionListFragment == null) {

			selectionListFragment = new SelectionListFragment();
			selectionListFragment.setRetainInstance(true);// To retain objects
															// inside
															// selectionListFragment
															// like
			// 'selectedItems' ArrayList etc. across configuration changes.
		}

		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, selectionListFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {// Save the
																// deletion list
																// fragment
																// along with
		// with its checkbox states and checkedItemIds:
		getSupportFragmentManager().putFragment(savedInstanceState,
				fragmentName, selectionListFragment);
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}

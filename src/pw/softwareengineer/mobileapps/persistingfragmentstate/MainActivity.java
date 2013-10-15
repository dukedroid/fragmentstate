package pw.softwareengineer.mobileapps.persistingfragmentstate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	Fragment selectionListFragment;
	private static final String fragmentName = "selection_list_fragment";// Key
																			// used
																			// to
																			// store
																			// the
																			// reference
																			// to
																			// the
																			// selectionListFragment

	// instance in the bundle during a configuration change.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) {
			// #3:
			selectionListFragment = fragmentManager.getFragment(
					savedInstanceState, fragmentName);
		}

		if (selectionListFragment == null) {

			selectionListFragment = new SelectionListFragment();
			// #1:
			selectionListFragment.setRetainInstance(true);// To retain the
															// current instance
															// of
			// 'selectionListFragment' across Activity re-creation.
		}
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction
				.replace(android.R.id.content, selectionListFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {// Save the
																// reference to
																// the
		// current instance of 'selectionListFragment' to the bundle.
		// #2:
		getSupportFragmentManager().putFragment(savedInstanceState,
				fragmentName, selectionListFragment);
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}

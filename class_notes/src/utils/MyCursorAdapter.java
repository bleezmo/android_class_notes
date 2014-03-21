package utils;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * This will be our list adapter for our list view
 * displays items in the list based on the results obtained
 * from the cursor.
 * @author josh
 *
 */
public class MyCursorAdapter extends CursorAdapter{
	/**
	 * we use this constructor so that android doesn't auto-query
	 * which will make thing to slow, and cause the app to be unresponsive
	 * 
	 * @param context
	 * @param c
	 * @param flags
	 */
	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}

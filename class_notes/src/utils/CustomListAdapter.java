package utils;

import com.example.class_notes.ItemDescriptionActivity;
import com.example.class_notes.ItemModel;
import com.example.class_notes.R;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Custom list adapter which overrides getView to return the view
 * represented in the custom xml list item layout
 * @author josh
 *
 */
public class CustomListAdapter extends BaseAdapter{

	private ItemModel[] items;
	private Activity ctx;

	public CustomListAdapter(Activity ctx, ItemModel[] items){
		this.items = items;
		this.ctx = ctx;
	}
	@Override
	public int getCount() {
		return items.length;
	}

	@Override
	public Object getItem(int position) {
		return items[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView != null){
			//if convertView is not null, we can reuse this view
			view = convertView;
		}else{
			//if convertView is null, inflate a new view and return it
			view = ctx.getLayoutInflater().inflate(R.layout.custom_list_item, null);
		}
		final ItemModel item = items[position];
		//populate the text view with the item title
		TextView tv = (TextView) view.findViewById(R.id.item_title);
		tv.setText(item.title);
		//each button must have it's own listener, to start an
		//an activity with individual options passed in
		Button btn = (Button) view.findViewById(R.id.item_btn);
		btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ctx,ItemDescriptionActivity.class);
				intent.putExtra("description", item.descr);
				ctx.startActivity(intent);
			}
		});
		return view;
	}

}

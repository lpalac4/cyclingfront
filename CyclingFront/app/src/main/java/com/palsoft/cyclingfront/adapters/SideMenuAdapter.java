package com.palsoft.cyclingfront.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.palsoft.cyclingfront.R;
import com.palsoft.cyclingfront.models.SideMenuItem;

import java.util.List;

public class SideMenuAdapter extends ArrayAdapter<SideMenuItem> {

	List<SideMenuItem> mMenuItems;
	
	public SideMenuAdapter(Context context, int resource,
			List<SideMenuItem> objects) {
		super(context, resource, objects);
		mMenuItems = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MenuHolder holder = null;

		if(convertView == null || convertView.getTag() == null)
		{
			convertView = View.inflate(getContext(), R.layout.adapter_sidemenu_item, null);
			holder = new MenuHolder();
			holder.mTitle =  (TextView) convertView.findViewById(R.id.title);
			holder.mIcon = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(holder);
		}
		else
		{
			holder = (MenuHolder) convertView.getTag();
		}

		holder.mTitle.setText(mMenuItems.get(position).getTitle());
		holder.mIcon.setBackgroundResource(mMenuItems.get(position).getImageId());

		return convertView;
	}

	public class MenuHolder
	{
		public TextView mTitle;
		public ImageView mIcon;
	}

}

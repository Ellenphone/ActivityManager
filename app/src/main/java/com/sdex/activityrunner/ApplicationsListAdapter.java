package com.sdex.activityrunner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.sdex.activityrunner.db.ActivityModel;
import com.sdex.activityrunner.db.ApplicationModel;
import com.sdex.activityrunner.db.ItemModel;
import com.sdex.activityrunner.util.GlideApp;
import java.util.ArrayList;
import java.util.List;

public class ApplicationsListAdapter extends BaseExpandableListAdapter {

  private List<ItemModel> items;
  private Context context;
  private RequestManager glide;

  public ApplicationsListAdapter(Context context) {
    this.context = context;
    this.items = new ArrayList<>();
    this.glide = GlideApp.with(context);
  }

  @Override
  public Object getChild(int groupPosition, int childPosition) {
    return this.items.get(groupPosition).getActivityModels().get(childPosition);
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
    View convertView, ViewGroup parent) {
    ActivityModel activityModel = (ActivityModel) getChild(groupPosition, childPosition);
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_application, parent, false);

    TextView text1 = view.findViewById(android.R.id.text1);
    text1.setText(activityModel.getName());

    TextView text2 = view.findViewById(android.R.id.text2);
    text2.setText(activityModel.getComponentName().getShortClassName());

    ImageView icon = view.findViewById(android.R.id.icon);
    glide.load(activityModel.getIconPath())
      .apply(new RequestOptions()
        .fitCenter())
      .into(icon);

    return view;
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return this.items.get(groupPosition).getActivityModels().size();
  }

  @Override
  public Object getGroup(int groupPosition) {
    return this.items.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return this.items.size();
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
    ViewGroup parent) {
    ItemModel itemModel = (ItemModel) getGroup(groupPosition);
    final ApplicationModel applicationModel = itemModel.getApplicationModel();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_activity, parent, false);

    TextView text = view.findViewById(android.R.id.text1);
    text.setText(applicationModel.getName());

    TextView text2 = view.findViewById(android.R.id.text2);
    text2.setText(applicationModel.getPackageName());

    ImageView icon = view.findViewById(android.R.id.icon);
    glide.load(applicationModel.getIconPath())
      .apply(new RequestOptions()
        .fitCenter())
      .into(icon);

    return view;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }

  public void addItems(List<ItemModel> itemModels) {
    this.items.clear();
    this.items.addAll(itemModels);
    notifyDataSetChanged();
  }
}
package com.example.winners_app.adapter;

import com.example.winners_app.R;
import com.example.winners_app.ListViewItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_TXT = 0 ;
    private static final int ITEM_VIEW_TYPE_IMG = 1 ;
    private static final int ITEM_VIEW_TYPE_BTN = 2 ;
    private static final int ITEM_VIEW_TYPE_MAX = 3 ;

    // 아이템 데이터 리스트.
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX ;
    }

    // position 위치의 아이템 타입 리턴.
    @Override
    public int getItemViewType(int position) {
        return listViewItemList.get(position).getType() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        int viewType = getItemViewType(position) ;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem listViewItem = listViewItemList.get(position);

            switch (viewType) {
                case ITEM_VIEW_TYPE_TXT:
                    convertView = inflater.inflate(R.layout.listview_notice,
                            parent, false);
                    TextView notice = (TextView) convertView.findViewById(R.id.notice) ;

                    notice.setText(listViewItem.getName());
                    break;
                case ITEM_VIEW_TYPE_IMG:
                    convertView = inflater.inflate(R.layout.listview_album,
                            parent, false);

                    ImageView image1 = (ImageView) convertView.findViewById(R.id.image1) ;
                    ImageView image2 = (ImageView) convertView.findViewById(R.id.image2) ;
                    ImageView image3 = (ImageView) convertView.findViewById(R.id.image3) ;

                    image1.setImageDrawable(listViewItem.getImage1());
                    image2.setImageDrawable(listViewItem.getImage2());
                    image3.setImageDrawable(listViewItem.getImage3());
                    break;
                case ITEM_VIEW_TYPE_BTN:
                    convertView = inflater.inflate(R.layout.listview_button,
                            parent, false);

                    Button button = (Button) convertView.findViewById(R.id.button) ;

                    button.setText(listViewItem.getName());
                    break;
            }
        }
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 첫 번째 아이템 추가를 위한 함수.
    public void addItem(String name) {
        ListViewItem item = new ListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_TXT) ;
        item.setName(name);

        listViewItemList.add(item) ;
    }

    // 두 번째 아이템 추가를 위한 함수.
    public void addItem(Drawable image1, Drawable image2, Drawable image3) {
        ListViewItem item = new ListViewItem() ;

        item.setType(ITEM_VIEW_TYPE_IMG) ;
        item.setImage1(image1);
        item.setImage2(image2);
        item.setImage3(image3);

        listViewItemList.add(item);
    }

    // 세 번째 아이템 추가를 위한 함수.
           public void addItem(String button, int num) {
            ListViewItem item = new ListViewItem() ;

            item.setType(ITEM_VIEW_TYPE_BTN) ;
            item.setName(button);

        listViewItemList.add(item);
    }
}
package gtsarandum.syncc;

/**
 * Created by Nicolas on 27.06.2014.
 */

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeSet;

class ContactAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<String> mData = new ArrayList<String>();
    private ArrayList<String> contactids=new ArrayList<String>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    private Context context;

    public ContactAdapter(Context context) {
        this.context=context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    //with id
    public void addItem(final String item, String id){
        mData.add(item);
        contactids.add(id);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    //with id=null
    public void addSectionHeaderItem(final String item, String id){
        //id will be null so images are displayed with the correct contact
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        contactids.add(id);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();


            switch (rowType) {

                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.snippet_item1, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.text);
                    holder.imageView=(ImageView)convertView.findViewById(R.id.contact_image);
                    if (contactids.get(position)!=null){
                        Uri photo=getPhotoUriFromID(contactids.get(position));
                        if (photo!=null){
                            Drawable drawable=getDrawableFromUri(photo);
                            holder.imageView.setImageDrawable(drawable);
                        }
                    }
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.snippet_item2, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(mData.get(position));

        return convertView;
    }

    private Drawable getDrawableFromUri(Uri uri){
        Drawable drawable;
        try {
            InputStream inputStream=context.getContentResolver().openInputStream(uri);
            drawable=Drawable.createFromStream(inputStream,uri.toString());
        } catch(FileNotFoundException e){
            drawable=context.getResources().getDrawable(R.drawable.ic_launcher);
        }
        return drawable;
    }

    private Uri getPhotoUriFromID(String id) {
        try {
            Cursor cur =context.getApplicationContext().getContentResolver()
                    .query(ContactsContract.Data.CONTENT_URI,
                            null,
                            ContactsContract.Data.CONTACT_ID
                                    + "="
                                    + id
                                    + " AND "
                                    + ContactsContract.Data.MIMETYPE
                                    + "='"
                                    + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
                                    + "'", null, null
                    );
            if (cur != null) {
                if (!cur.moveToFirst()) {
                    return null; // no photo
                }
            } else {
                return null; // error in cursor process
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Uri person = ContentUris.withAppendedId(
                ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
        return Uri.withAppendedPath(person,
                ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}
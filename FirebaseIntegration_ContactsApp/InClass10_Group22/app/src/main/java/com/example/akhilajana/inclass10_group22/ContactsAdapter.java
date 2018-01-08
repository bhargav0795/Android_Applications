package com.example.akhilajana.inclass10_group22;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by vijju on 11/7/2016.
 */

public class ContactsAdapter extends ArrayAdapter<Contacts> {

    List<Contacts> contactsList;
    int mResource;
    Context mContext;

    public ContactsAdapter(Context context, int resource, List<Contacts> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.contactsList = objects;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
            holder=new ViewHolder();
            holder.eImage = (ImageView)convertView.findViewById(R.id.ContactAvatar) ;
            holder.eName= (TextView) convertView.findViewById(R.id.viewContactName);
            holder.eEmail= (TextView) convertView.findViewById(R.id.viewContactEmail);
            holder.ePhone= (TextView) convertView.findViewById(R.id.viewConatactPhone);
            holder.eDept= (TextView) convertView.findViewById(R.id.viewConatactDept);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Contacts contacts=contactsList.get(position);
        ImageView rowImage = holder.eImage;
        TextView rowName = holder.eName;
        TextView rowAmount = holder.eEmail;
        TextView rowPhone = holder.ePhone;
        TextView rowDept = holder.eDept;

        String imagePath = contacts.getImage();

        if(imagePath.equals("R.drawable.avatar_f_1"))
        {
            rowImage.setImageResource(R.drawable.avatar_f_1);

        }
        else if(imagePath.equals("R.drawable.avatar_f_2"))
        {
            rowImage.setImageResource(R.drawable.avatar_f_2);

        }
        else if(imagePath.equals("R.drawable.avatar_f_3"))
        {
            rowImage.setImageResource(R.drawable.avatar_f_3);

        }
        else if(imagePath.equals("R.drawable.avatar_m_1"))
        {
            rowImage.setImageResource(R.drawable.avatar_m_1);

        }
        else if(imagePath.equals("R.drawable.avatar_m_2"))
        {
            rowImage.setImageResource(R.drawable.avatar_m_2);

        }
        else if(imagePath.equals("R.drawable.avatar_m_3"))
        {
            rowImage.setImageResource(R.drawable.avatar_m_3);

        }

        rowName.setText(contacts.getName().toString());
        rowAmount.setText(contacts.getEmail().toString());
        rowPhone.setText(contacts.getPhone().toString());
        rowDept.setText(contacts.getDepartment().toString());

        return convertView;
    }
    static class ViewHolder{
        TextView eName;
        TextView eEmail;
        TextView ePhone;
        TextView eDept;
        ImageView eImage;

    }
}

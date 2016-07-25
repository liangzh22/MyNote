package com.example.administrator.mynote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.mynote.Model.Note;
import com.example.administrator.mynote.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private int resourceId;

    public NoteAdapter(Context context,  int resourceId, List<Note> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.content=(TextView)view.findViewById(R.id.noteitem);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.content.setText(note.getContent());
        return view;
    }
    class ViewHolder{
        TextView content;
    }
}

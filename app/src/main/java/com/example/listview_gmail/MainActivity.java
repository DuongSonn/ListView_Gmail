package com.example.listview_gmail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity  {

    String[] senders = {"Alliance","Bratt","Chris","Devil","EE","F","Global","Home"};
    String[] contents = {"Content 1","Content 2","Content 3","Content 4","Content 5","Content 6","Content 7","Content 8"};
    String[] headers = {"Header 1","Header 2","Header 3","Header 4","Header 5","Header 6","Header 7","Header 8"};
    String[] times= {"12:11 PM","12:00 AM","11:11 PM","10:30 PM","08:11 AM","09:10 PM","09:11 AM","12:11 PM"};
    String[] characters;
    Integer[] likes = {R.mipmap.start,R.mipmap.start,R.mipmap.start,R.mipmap.start,R.mipmap.start,R.mipmap.start,R.mipmap.start,R.mipmap.start};
//    Integer like = R.mipmap.start;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characters = new String[senders.length];
        for (int i = 0; i < characters.length ; i++) {
            characters[i] = senders[i].substring(0,1);
        }

        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter (this, R.layout.custom_row_icon_label, senders, contents, headers, times, characters, likes);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ImageView like = v.findViewById(R.id.like);
        if (likes[position] == R.mipmap.start) {
            likes[position] = R.mipmap.end;
            like.setImageResource(likes[position]);
        } else {
            likes[position] = R.mipmap.start;
            like.setImageResource(likes[position]);
        }
    }
}

class  CustomIconLabelAdapter extends ArrayAdapter<String> {
    Context context;
    String[] senders;
    String[] headers;
    String[] contents;
    String[] times;
    String[] characters;
    Integer[] likes;
    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] senders, String[] contents, String[] headers, String[] times, String[] characters, Integer[] likes) {
        super(context, R.layout.custom_row_icon_label, senders);
        this.context = context;
        this.senders = senders;
        this.contents = contents;
        this.headers = headers;
        this.times = times;
        this.characters = characters;
        this.likes = likes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_row_icon_label, null);

        TextView header, content, time, sender, icon;
        ImageView like = row.findViewById(R.id.like);

        header = row.findViewById(R.id.description);
        sender = row.findViewById(R.id.sender);
        content = row.findViewById(R.id.content);
        icon = row.findViewById(R.id.icon);
        time = row.findViewById(R.id.time);

        header.setText(headers[position]);
        sender.setText(senders[position]);
        content.setText(contents[position]);
        time.setText(times[position]);
        icon.setText(characters[position]);
        like.setImageResource(likes[position]);

        return (row);
    }
}

package com.example.ivandimitrov.instagramtask.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ivandimitrov.instagramtask.R;
import com.example.ivandimitrov.instagramtask.retrofit.comments.CommentsData;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 2/2/2017.
 */

public class CommentListAdapter extends ArrayAdapter<CommentsData> {

    private final Activity context;
    private ArrayList<CommentsData> mCommentsList = new ArrayList<>();

    public CommentListAdapter(Activity context, ArrayList<CommentsData> commentsList) {
        super(context, 0, commentsList);
        this.context = context;
        this.mCommentsList = commentsList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.comment_element, null, true);
            viewHolder.userName = (TextView) view.findViewById(R.id.user_name);
            viewHolder.userComment = (TextView) view.findViewById(R.id.user_comment);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.userName.setText(mCommentsList.get(position).getFrom().getFullName());
        viewHolder.userComment.setText(mCommentsList.get(position).getText());
        return view;
    }

    static class ViewHolder {
        TextView userName;
        TextView userComment;
    }
}
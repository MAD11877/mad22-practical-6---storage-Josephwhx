package sg.edu.np.mad.practical3;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder> {
    ArrayList<User> data;
    ProfileListener listener;
    public ProfileAdapter(ArrayList<User> userList, ProfileListener listener){
        this.data = userList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        ProfileViewHolder viewHolder;
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout, parent, false);
        viewHolder = new ProfileViewHolder(item);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ProfileViewHolder holder, @SuppressLint("RecyclerView") int position){
        User user = data.get(position);
        String lastNumber = user.getName().substring(user.getName().length() - 1);
        holder.name.setText(user.getName());
        holder.desc.setText(user.getDescription());
        if(lastNumber.equals("7")){

            holder.showIcon.setVisibility(View.VISIBLE);
        }
        else{
            holder.showIcon.setVisibility(View.GONE);
        }
        holder.profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onIconClick(data.get(position));
            }
        });
    }
    @Override
    public int getItemCount(){
        return data.size();
    }
}

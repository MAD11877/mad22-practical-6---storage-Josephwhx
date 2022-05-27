package sg.edu.np.mad.practical3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProfileViewHolder extends RecyclerView.ViewHolder {
    TextView name, desc;
    ImageView profileIcon, showIcon;
    public ProfileViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.profileName);
        desc = itemView.findViewById(R.id.profileDescription);
        profileIcon = itemView.findViewById(R.id.iconImage);
        showIcon = itemView.findViewById(R.id.imageView2);
    }
}

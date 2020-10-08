package viewHolderSk;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject2020gym.R;
import com.example.madproject2020gym.memberClickListnerSK;

public class memberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMemberName, txtMemberEmail, txtMemberPhone;
    public memberClickListnerSK listner;

    public memberViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMemberName = (TextView) itemView.findViewById(R.id.member_Name);
        txtMemberEmail = (TextView) itemView.findViewById(R.id.member_Email);
        txtMemberPhone = (TextView) itemView.findViewById(R.id.member_Phone);



    }

   public void setMemberClickListner(memberClickListnerSK listner){
        this.listner = listner;
   }


    @Override
    public void onClick(View view) {
        listner.onclick(view, getAdapterPosition(),false);

    }
}

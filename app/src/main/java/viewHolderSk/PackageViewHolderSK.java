package viewHolderSk;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject2020gym.R;
import com.example.madproject2020gym.pkgClickListnerSK;

public class PackageViewHolderSK extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtPackageName, txtPackageDescription;
    public pkgClickListnerSK listner;




    public PackageViewHolderSK(@NonNull View itemView) {
        super(itemView);

        txtPackageName = (TextView) itemView.findViewById(R.id.package_name);
        txtPackageDescription = (TextView) itemView.findViewById(R.id.package_description);
    }

    public void setpkgClickLictnerSK(pkgClickListnerSK listner){
        this.listner = listner;
    }
    @Override
    public void onClick(View view) {
        listner.onclick(view, getAdapterPosition(), false);

    }
}

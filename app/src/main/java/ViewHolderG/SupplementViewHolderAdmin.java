package ViewHolderG;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject2020gym.R;

import InterfaceG.ItemClickListnerAdmin;

public class SupplementViewHolderAdmin extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtSupplement, txtSupplementDescription,txtSupplementPrice;
    public ImageView imageView;
    public ItemClickListnerAdmin listner;

    public SupplementViewHolderAdmin( View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.supplement_image);
        txtSupplement = (TextView) itemView.findViewById(R.id.supplement_admin);
        txtSupplementDescription = (TextView) itemView.findViewById(R.id.description_admin);
        txtSupplementPrice = (TextView) itemView.findViewById(R.id.supplement_price);
    }

    public void setItemClickListnerAdmin(ItemClickListnerAdmin listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}

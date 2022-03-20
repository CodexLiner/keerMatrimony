package keer.matrimony.Adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import keer.matrimony.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.models.data;

public class ProfileListAdapters extends RecyclerView.Adapter<ProfileListAdapters.holder> {
    List<data> list;

    public ProfileListAdapters(List<data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_layout , parent , false);
        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        if (list.get(position).getFirst_name()!=null){
            String name = list.get(position).getFirst_name();
            if (list.get(position).getLast_name()!=null){
                name+= " "+list.get(position).getLast_name();
                holder.Name.setText(name);
            }else {
                holder.Name.setText(name);
            }
        }
        if (list.get(position).getCity()!=null){
            holder.job.setText(list.get(position).getCity());
        }
        if (list.get(position).getCity()!=null){
            holder.city.setText(list.get(position).getCity());
            if (list.get(position).getSubcaste()!=null){
                String castCity = "Keer, " +list.get(position).getSubcaste()+" | "+list.get(position).getCity();
                holder.city.setText(castCity);
            }
        }
        if (list.get(position).getDob()!=null){
            String[] ages = list.get(position).getDob().split("-");
            int age = Integer.parseInt(ages[0]);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDate curDate = LocalDate.now();
                LocalDate dob = LocalDate.parse(list.get(position).getDob());
                int s = Period.between(dob , curDate).getYears();
                holder.age.setText(String.valueOf(s));
            }
        }
//        Picasso.with(holder.ProfileImage.getContext()).load(Uri.parse((String) list.get(position))).into(holder.ProfileImage);
       if (list.get(position).getProfile()!=null){
           Picasso.with(holder.ProfileImage.getContext()).load(Uri.parse((String) CONSTANTS.BASEURLPROFILE +  list.get(position).getProfile())).into(new Target() {
               @Override
               public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                   holder.ProfileImage.setBackground(new BitmapDrawable(bitmap));
               }

               @Override
               public void onBitmapFailed(Drawable errorDrawable) {

               }

               @Override
               public void onPrepareLoad(Drawable placeHolderDrawable) {
                   holder.ProfileImage.setBackground(placeHolderDrawable);
               }
           });
       }

//       buttons
        holder.InterestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.ProfileImage.getContext(), "Interest Sent", Toast.LENGTH_SHORT).show();
            }
        });
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.ProfileImage.getContext(), "Added to favourite", Toast.LENGTH_SHORT).show();
            }
        });
        holder.ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                activity.getFragmentManager().beginTransaction().replace( R.id.navigation_home, new DashboardFragment()).commit();
                Navigation.findNavController(v).navigate(R.id.action_navigation_dashboard_to_navigationDetails2);
                CONSTANTS.DATAs = list.get(position);
//                ((FragmentActivity)v.getContext()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.nav_host_fragment_activity_home, new ProfileDetails( list.get(position))).addToBackStack("dashboard")
//                        .commit();
              /*  ((HomeActivity) v.getContext()).getFragmentManager().beginTransaction()
                        .replace( R.id.navigation_home, new DashboardFragment())
                        .commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {
        Button callButton , InterestButton;
        ImageButton favButton;
        ImageView ProfileImage;
        TextView ignore , allPhotos , Name , job , age , city;
        public holder(@NonNull View itemView) {
            super(itemView);

            callButton = itemView.findViewById(R.id.callButton);
            ProfileImage = itemView.findViewById(R.id.ProfileImage);
            InterestButton = itemView.findViewById(R.id.intrestButton);
            favButton = itemView.findViewById(R.id.favButton);
            ignore = itemView.findViewById(R.id.ignore);
            allPhotos = itemView.findViewById(R.id.allPhotos);
            Name = itemView.findViewById(R.id.sName);
            job = itemView.findViewById(R.id.sJob);
            age = itemView.findViewById(R.id.sAge);
            city = itemView.findViewById(R.id.sCity);
        }
    }
}

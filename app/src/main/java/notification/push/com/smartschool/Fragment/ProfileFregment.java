package notification.push.com.smartschool.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import notification.push.com.smartschool.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFregment extends Fragment {


    public ProfileFregment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_fregment, container, false);
    }

}

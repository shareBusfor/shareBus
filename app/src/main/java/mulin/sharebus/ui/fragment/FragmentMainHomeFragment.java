package mulin.sharebus.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import mulin.sharebus.R;

/**
 * Created by mulin on 2018/4/11.
 */

public class FragmentMainHomeFragment extends SupportFragment {


    public static FragmentMainHomeFragment newInstance() {

        Bundle args = new Bundle();

        FragmentMainHomeFragment fragment = new FragmentMainHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_sms, container, false);


//        initData();
        return view;
    }

}

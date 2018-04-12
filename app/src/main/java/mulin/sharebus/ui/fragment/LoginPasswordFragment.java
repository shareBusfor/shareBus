package mulin.sharebus.ui.fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by mulin on 2018/4/9.
 */

public class LoginPasswordFragment extends SupportFragment {


    EditText phoneEdit;
    Button nextStep;
    LinearLayout llMain;

    public static LoginPasswordFragment newInstance() {

        Bundle args = new Bundle();

        LoginPasswordFragment fragment = new LoginPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }


}

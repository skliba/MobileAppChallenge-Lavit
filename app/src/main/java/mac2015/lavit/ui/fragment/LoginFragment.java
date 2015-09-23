package mac2015.lavit.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import mac2015.lavit.R;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.LoginPresenter;
import mac2015.lavit.ui.util.IntentUtil;
import mac2015.lavit.ui.view.LoginView;

/**
 * Created by dmacan on 22.9.2015..
 */
public class LoginFragment extends BaseTabFragment implements LoginView {

    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.etPassword)
    MaterialEditText etPassword;

    @Inject
    LoginPresenter loginPresenter;

    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginPresenter.initialize();
        loginPresenter.setView(this);
        progressDialog = new ProgressDialog(getActivity());
        loginPresenter.onViewCreate();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showEmailError(String error) {
        etEmail.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void clearEmailError() {
        etEmail.setError(null);
    }

    @Override
    public void clearPasswordError() {
        etPassword.setError(null);
    }

    @Override
    public void showLoading(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void proceed(User user) {
        startActivity(IntentUtil.startMainActivity(getActivity(), user));
        Toast.makeText(getActivity(), "Proceeding with:\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lock() {
        toggleEnabled(false);
    }

    @Override
    public void unlock() {
        toggleEnabled(true);
    }

    @Override
    public void requestAuthorization(Intent intent, int code) {
        startActivityForResult(intent, code);
    }

    @OnClick(R.id.btnSignIn)
    protected void onSignInClicked() {
        loginPresenter.attemptLogin();
        //proceed(Mock.mockUser());
    }

    @OnClick(R.id.btnGoogleLogin)
    protected void onGoogleLoginClicked() {
        loginPresenter.attemptGoogleLogin(getActivity());
    }

    private void toggleEnabled(boolean enabled) {
        etEmail.setEnabled(enabled);
        etEmail.setClickable(enabled);
        etPassword.setEnabled(enabled);
        etPassword.setClickable(enabled);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginPresenter.onAuthorizationResult(getActivity(), requestCode, resultCode, data);
    }


}

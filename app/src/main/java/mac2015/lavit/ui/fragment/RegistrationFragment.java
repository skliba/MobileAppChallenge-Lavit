package mac2015.lavit.ui.fragment;

import android.app.ProgressDialog;
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
import mac2015.lavit.ui.presenter.RegistrationPresenter;
import mac2015.lavit.ui.util.IntentUtil;
import mac2015.lavit.ui.view.RegistrationView;

/**
 * Created by dmacan on 22.9.2015..
 */
public class RegistrationFragment extends BaseTabFragment implements RegistrationView {

    @InjectView(R.id.etEmail)
    MaterialEditText etEmail;
    @InjectView(R.id.etPassword)
    MaterialEditText etPassword;
    @InjectView(R.id.etPasswordConfirm)
    MaterialEditText etPasswordConfirm;
    @InjectView(R.id.etFirstName)
    MaterialEditText etFirstName;
    @InjectView(R.id.etLastName)
    MaterialEditText etLastName;

    @Inject
    RegistrationPresenter registrationPresenter;

    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registrationPresenter.initialize();
        registrationPresenter.setView(this);
        progressDialog = new ProgressDialog(getActivity(), android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setCancelable(false);
        registrationPresenter.onViewCreate();
    }

    @OnClick(R.id.btnSignUp)
    protected void onSignUpClick() {
        registrationPresenter.attemptRegistration();
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
    public String getPasswordConfirm() {
        return etPasswordConfirm.getText().toString();
    }

    @Override
    public String getFirstName() {
        return etFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLastName.getText().toString();
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
    public void showPasswordConfirmError(String error) {
        etPasswordConfirm.setError(error);
    }

    @Override
    public void showFirstNameError(String error) {
        etFirstName.setError(error);
    }

    @Override
    public void showLastNameError(String error) {
        etLastName.setError(error);
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
    public void clearPasswordConfirmError() {
        etPasswordConfirm.setError(null);
    }

    @Override
    public void clearFirstNameError() {
        etFirstName.setError(null);
    }

    @Override
    public void clearLastNameError() {
        etLastName.setError(null);
    }

    @Override
    public void showLoading(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void proceed(User user) {
        startActivity(IntentUtil.startMainActivity(getActivity(), user));
    }

    @Override
    public void lock() {
        toggleEnabled(false);
    }

    @Override
    public void unlock() {
        toggleEnabled(true);
    }

    private void toggleEnabled(boolean enabled) {
        etEmail.setEnabled(enabled);
        etEmail.setClickable(enabled);
        etPassword.setEnabled(enabled);
        etPassword.setClickable(enabled);
        etPasswordConfirm.setEnabled(enabled);
        etPasswordConfirm.setClickable(enabled);
        etFirstName.setEnabled(enabled);
        etFirstName.setClickable(enabled);
        etLastName.setEnabled(enabled);
        etLastName.setClickable(enabled);
    }
}

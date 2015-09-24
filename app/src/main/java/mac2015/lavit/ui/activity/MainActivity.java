package mac2015.lavit.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.InjectView;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.MainPresenter;
import mac2015.lavit.ui.util.IntentUtil;
import mac2015.lavit.ui.view.MainView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class MainActivity extends BaseActivity implements MainView, ViewEventListener<ProjectModel> {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.imgAccountAvatar)
    ImageView imgAccountAvatar;
    @InjectView(R.id.txtAccountName)
    TextView txtAccountName;
    @InjectView(R.id.txtAccountEmail)
    TextView txtAccountEmail;
    @InjectView(R.id.listProjects)
    RecyclerView gridProjects;

    @Inject
    MainPresenter mainPresenter;
    @Inject
    @Named("projects")
    RecyclerMultiAdapter projectsAdapter;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        mainPresenter.initialize();
        mainPresenter.setView(this);
        mainPresenter.setUser(IntentUtil.fetchUser(this));
        setupGrid();
        mainPresenter.onViewCreate();
    }

    @Override
    public void showProfileInfo(User user) {
        Picasso.with(this).load("http://41.media.tumblr.com/18a5ac1fc70360ea1b2ef476ec9f15f2/tumblr_mgblfiaRi21qarlxmo1_400.png").into(imgAccountAvatar);
        txtAccountEmail.setText(user.getEmail());
        txtAccountName.setText(user.getFirstName());
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/LobsterTwo.otf");
        txtAccountEmail.setTypeface(myTypeface);
    }

    @Override
    public void showProjects(List<ProjectModel> projects) {
        projectsAdapter.setItems(projects);
    }

    @Override
    public void openProject(User user, ProjectModel project) {
        startActivity(IntentUtil.startProjectActivity(getBaseContext(), user, project));
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnToLogin() {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    private void setupGrid() {
        gridProjects.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        gridProjects.setAdapter(projectsAdapter);
        projectsAdapter.setViewEventListener(this);
    }

    @Override
    public void onViewEvent(int i, ProjectModel projectModel, int i1, View view) {
        mainPresenter.projectSelected(projectModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuLogout) {
            mainPresenter.onLogoutSelected();
            return true;
        }
        return false;
    }
}

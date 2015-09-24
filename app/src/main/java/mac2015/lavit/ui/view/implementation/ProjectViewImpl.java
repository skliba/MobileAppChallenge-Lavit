package mac2015.lavit.ui.view.implementation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.nlopez.smartadapters.views.BindableLayout;
import mac2015.lavit.R;
import mac2015.lavit.domain.models.ProjectModel;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectViewImpl extends BindableLayout<ProjectModel> {

    private static final String TAG = "DAM_VIEW_PROJECT";
    @InjectView(R.id.txtProjectName)
    TextView txtProjectName;
    @InjectView(R.id.txtProjectOwner)
    TextView txtProjectOwner;
    @InjectView(R.id.imgProjectCover)
    ImageView imgProjectCover;

    private ProjectModel project;

    public ProjectViewImpl(Context context) {
        super(context);
    }

    @Override
    public void onViewInflated() {
        super.onViewInflated();
        ButterKnife.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_project;
    }

    @Override
    public void bind(ProjectModel projectModel) {
        this.project = projectModel;
        Picasso.with(getContext()).load("http://blog.caranddriver.com/wp-content/uploads/2009/08/2009-Lamborghini-Gallardo-LP60-4.jpg").into(imgProjectCover);
        txtProjectName.setText(projectModel.getName());
        txtProjectOwner.setText(projectModel.getOwnerName());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemAction(Action.CLICK, project, ProjectViewImpl.this);
            }
        });
    }

    public static final class Action {
        public static int CLICK = 0;
    }
}

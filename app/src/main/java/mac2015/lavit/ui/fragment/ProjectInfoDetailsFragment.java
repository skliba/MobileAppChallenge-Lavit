package mac2015.lavit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.presenter.ProjectInfoDescriptionPresenter;
import mac2015.lavit.ui.view.ProjectInfoDescriptionView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectInfoDetailsFragment extends BaseTabFragment implements ProjectInfoDescriptionView {

    @InjectView(R.id.txtDescription)
    TextView txtDescription;
    @InjectView(R.id.txtProjectTags)
    TextView txtProjectOwner;
    @InjectView(R.id.txtTags)
    TextView txtTags;


    @Inject
    ProjectInfoDescriptionPresenter projectInfoDescriptionPresenter;

    private ProjectModel projectModel;

    public void setData(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projectdetails, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        projectInfoDescriptionPresenter.initialize();
        projectInfoDescriptionPresenter.setView(this);
        projectInfoDescriptionPresenter.setData(projectModel);
        projectInfoDescriptionPresenter.onViewCreate();
    }

    @Override
    public void showDescription(ProjectModel projectModel) {
        txtDescription.setText(projectModel.getDescription());
    }

    @Override
    public void showOwners(ProjectModel projectModel) {
        txtProjectOwner.setText(projectModel.getOwnerName());
    }

    @Override
    public void showOverallScore(ProjectModel model) {
        txtProjectOwner.setText(model.getFeedbackModel().getAverage() + "");
    }

    @Override
    public void showTags(List<ProjectModel.Tag> tags) {
        String tagLabel = "";
        for (ProjectModel.Tag tag : tags) {
            tagLabel += "#" + tag.getName() + "  ";
        }
        txtTags.setText(tagLabel);
    }
}

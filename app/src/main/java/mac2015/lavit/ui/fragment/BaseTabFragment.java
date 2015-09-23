package mac2015.lavit.ui.fragment;


import mac2015.lavit.app.BaseFragment;
import mac2015.lavit.ui.custom.tabs.TabFragment;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class BaseTabFragment extends BaseFragment implements TabFragment {

    private boolean focused;
    private String label;
    private boolean active;

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void onFocusChanged(boolean focused) {
        this.focused = focused;
    }

    protected boolean isFocused() {
        return focused;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

package mac2015.lavit.ui.custom.tabs;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface TabFragment {
    String getLabel();

    void setLabel(String label);

    void onFocusChanged(boolean focused);
}

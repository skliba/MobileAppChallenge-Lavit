package mac2015.lavit.ui.custom.tabs;

import android.app.FragmentManager;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;

import mac2015.lavit.R;


/**
 * Created by dmacan on 22.9.2015..
 */
public class IconTabAdapter extends TabAdapter {

    private Context context;

    public IconTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public View createView(Icon icon) {
        return createView(icon, R.layout.tab_iconic);
    }

    public View createView(Icon icon, int customLayout) {
        ImageView img = (ImageView) View.inflate(context, customLayout, null);
        IconDrawable drawable = new IconDrawable(context, icon)
                .colorRes(android.R.color.white)
                .actionBarSize();
        img.setImageDrawable(drawable);
        return img;
    }

    public View createView(int drawableRes) {
        ImageView img = (ImageView) View.inflate(context, R.layout.tab_iconic, null);
        img.setImageResource(drawableRes);
        return img;
    }

    public View createView(int drawableRes, int customLayout) {
        ImageView img = (ImageView) View.inflate(context, customLayout, null);
        img.setImageResource(drawableRes);
        return img;
    }
}

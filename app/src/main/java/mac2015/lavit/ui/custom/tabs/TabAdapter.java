package mac2015.lavit.ui.custom.tabs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmacan on 22.9.2015..
 */
public class TabAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;

    public TabAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
    }

    public <Tab extends Fragment & TabFragment> TabAdapter(FragmentManager fm, List<Tab> fragments) {
        this(fm);
        for (Tab t : fragments)
            this.fragments.add(t);
    }

    public <Tab extends Fragment & TabFragment> TabAdapter(FragmentManager fm, Tab... fragments) {
        this(fm);
        Collections.addAll(this.fragments, fragments);
    }

    /**
     * Adds given tab to the list of fragments and notifies the adapter to display that fragment aswell
     *
     * @param tab   Tab to be used as a single tab and it's content
     * @param <Tab> Fragment implementing TabFragment interface
     */
    public <Tab extends Fragment & TabFragment> void addTab(Tab tab) {
        this.fragments.add(tab);
        this.notifyDataSetChanged();
    }

    /**
     * Adds given list of tabs to the list of fragments and notifies the adapter to display the new tabs aswell
     *
     * @param tabs  List of tabs being added to the list of fragments within the adapter
     * @param <Tab> Fragment implementing TabFragment interface
     */
    public <Tab extends Fragment & TabFragment> void addTabs(List<Tab> tabs) {
        for (Tab t : tabs)
            this.fragments.add(t);
        this.notifyDataSetChanged();
    }

    /**
     * Removes given tab from the list of fragments and notifies the adapter that it should refresh the display
     *
     * @param tab   Tab to be removed
     * @param <Tab> Fragment implementing TabFragment interface
     */
    public <Tab extends Fragment & TabFragment> void removeTab(Tab tab) {
        this.fragments.remove(tab);
        this.notifyDataSetChanged();
    }

    /**
     * Removes all tabs from the list of fragments and notifies the adapter that it should refresh the display
     */
    public void clearTabs() {
        this.fragments.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        TabFragment tabFragment = (TabFragment) getItem(position);
        if (tabFragment != null && tabFragment.getLabel() != null)
            return tabFragment.getLabel();
        else
            return "NULL";
    }
}

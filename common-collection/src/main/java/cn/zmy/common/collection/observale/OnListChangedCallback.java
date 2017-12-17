package cn.zmy.common.collection.observale;

/**
 * Created by zmy on 2017/12/13.
 */

/**
 * The callback that is called by ObservableList when the list has changed.
 */
public interface OnListChangedCallback<T extends ObservableList>
{

    /**
     * Called whenever a change of unknown type has occurred, such as the entire list being
     * set to new values.
     *
     * @param sender The changing list.
     */
    public abstract void onChanged(T sender);

    /**
     * Called whenever one or more items in the list have changed.
     *
     * @param sender        The changing list.
     * @param positionStart The starting index that has changed.
     * @param itemCount     The number of items that have changed.
     */
    public abstract void onItemRangeChanged(T sender, int positionStart, int itemCount);

    /**
     * Called whenever items have been inserted into the list.
     *
     * @param sender        The changing list.
     * @param positionStart The insertion index
     * @param itemCount     The number of items that have been inserted
     */
    public abstract void onItemRangeInserted(T sender, int positionStart, int itemCount);

    /**
     * Called whenever items in the list have been moved.
     *
     * @param sender       The changing list.
     * @param fromPosition The position from which the items were moved
     * @param toPosition   The destination position of the items
     * @param itemCount    The number of items moved
     */
    public abstract void onItemRangeMoved(T sender, int fromPosition, int toPosition, int itemCount);

    /**
     * Called whenever items in the list have been deleted.
     *
     * @param sender        The changing list.
     * @param positionStart The starting index of the deleted items.
     * @param itemCount     The number of items removed.
     */
    public abstract void onItemRangeRemoved(T sender, int positionStart, int itemCount);
}

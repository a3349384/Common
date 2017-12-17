package cn.zmy.common.collection.observale;

import java.util.List;

/**
 * COPY FROM ANDROID DATABINDING LIBRARY
 * <p>
 * A {@link List} that notifies when changes are made. An ObservableList bound to the UI
 * will keep the it up-to-date when changes occur.
 * <p>
 * The ObservableList must notify its callbacks whenever a change to the list occurs, using
 * {@link OnListChangedCallback}.
 * <p>
 * ObservableArrayList implements ObservableList with an underlying ArrayList.
 * ListChangeRegistry can help in maintaining the callbacks of other implementations.
 */
public interface ObservableList<T> extends List<T>
{

    /**
     * Adds a callback to be notified when changes to the list occur.
     *
     * @param callback The callback to be notified on list changes
     */
    void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> callback);

    /**
     * Removes a callback previously added.
     *
     * @param callback The callback to remove.
     */
    void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<T>> callback);
}

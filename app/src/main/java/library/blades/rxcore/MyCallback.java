package library.blades.rxcore;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class MyCallback<T> extends DiffUtil.Callback {
    private List<T> old_students, new_students;

    MyCallback(List<T> data, List<T> students) {
        this.old_students = data;
        this.new_students = students;
    }

    @Override
    public int getOldListSize() {
        return old_students.size();
    }

    @Override
    public int getNewListSize() {
        return new_students.size();
    }

    // 判断Item是否已经存在
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old_students.get(oldItemPosition) == new_students.get(newItemPosition);
    }

    // 如果Item已经存在则会调用此方法，判断Item的内容是否一致
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old_students.get(oldItemPosition).equals(new_students.get(newItemPosition));
    }
}
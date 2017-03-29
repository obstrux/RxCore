package library.blades.rxcore

import android.support.v7.util.DiffUtil

class MyCallback<T> internal constructor(private val old_students: List<T>, private val new_students: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old_students.size
    }

    override fun getNewListSize(): Int {
        return new_students.size
    }

    // 判断Item是否已经存在
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old_students[oldItemPosition] === new_students[newItemPosition]
    }

    // 如果Item已经存在则会调用此方法，判断Item的内容是否一致
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old_students[oldItemPosition] == new_students[newItemPosition]
    }
}
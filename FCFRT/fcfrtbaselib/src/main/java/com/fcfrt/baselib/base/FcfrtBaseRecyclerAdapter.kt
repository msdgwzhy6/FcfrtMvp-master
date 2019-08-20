package com.fcfrt.baselib.base


import android.annotation.SuppressLint
import android.database.DataSetObservable
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


/**
 * 项目名称：RecyclerView适配器基类
 * 类名称：BaseFcfrtRecyclerAdapter
 * 类描述：
 * 作者：FCFRT
 * 创建时间： 2019/4/8-15:13
 * 邮箱：FCFRT_ADMIN@163.COM
 * 修改简介：
 */

abstract class FcfrtBaseRecyclerAdapter<T> : RecyclerView.Adapter<FcfrtBaseViewHolder> {
    private val mTAG = "BaseFcfrtRecyclerAdapter"
    private var mLayoutId: Int=0
    private var mList: MutableList<T>? = null
    private var mLastPosition = -1
    private var mOpenAnimationEnable = true
    private val mDataSetObservable = DataSetObservable()

    constructor(@LayoutRes layoutId:Int){
        this.setHasStableIds(false)
        this.mList =ArrayList()
        this.mLayoutId = layoutId
    }
    constructor(collection:List<T>,@LayoutRes layoutId:Int){
        this.setHasStableIds(false)
        this.mList =ArrayList(collection)
        this.mLayoutId = layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FcfrtBaseViewHolder {
        return FcfrtBaseViewHolder( LayoutInflater.from(parent.context).inflate( mLayoutId, parent, false )
        )
    }

    override fun onBindViewHolder(holder: FcfrtBaseViewHolder, position: Int) {
        onBindViewHolder(holder, if (position < mList!!.size) mList?.get(position) else null, position)
        holder.itemView.isClickable = true
        holder.itemView.setOnClickListener {
            this.onItemClicks(it, if (position<mList!!.size)mList?.get(position)else null,position)
        }
    }

    protected abstract fun onBindViewHolder(holder: FcfrtBaseViewHolder, model: T?, position: Int)

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onViewAttachedToWindow(holder: FcfrtBaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        addAnimate(holder, holder.layoutPosition)
    }

    private fun addAnimate(holder: FcfrtBaseViewHolder, postion: Int) {
        if (mOpenAnimationEnable && mLastPosition < postion) {
            holder.itemView.alpha = 0f
            holder.itemView.animate().alpha(1f).start()
            mLastPosition = postion
        }
    }

    open fun setOpenAnimationEnable(enable: Boolean) {
        this.mOpenAnimationEnable = enable
    }

    open fun getDatas(): List<T> {
        return mList!!
    }


    open fun refresh(collection: List<T>?): FcfrtBaseRecyclerAdapter<T> {
        if (collection != null) {
            mList?.clear()
            mList?.addAll(collection)
            notifyDataSetChanged()
            notifyListDataSetChanged()
            mLastPosition = -1
        }
        return this
    }

    open fun loadmore(collection: List<T>?): FcfrtBaseRecyclerAdapter<T> {
        if (collection != null) {
            mList?.addAll(collection)
            notifyDataSetChanged()
            notifyListDataSetChanged()
        }
        return this
    }

    open fun clear() {
        if (mList == null) {
            log("clear() mData is null")
            return
        }
        mList?.clear()
        notifyDataSetChanged()
        notifyListDataSetChanged()
    }


    private fun update(index: Int, value: T, tag: Any?) {
        mList?.set(index,value)
        notifyItemChanged(index, tag)
    }

    open fun update(index: Int, value: T) {
        update(index, value, null)
    }

    fun registerDataSetObserver(observer: DataSetObserver) {
        mDataSetObservable.registerObserver(observer)
    }

    fun unregisterDataSetObserver(observer: DataSetObserver) {
        mDataSetObservable.unregisterObserver(observer)
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    fun notifyListDataSetChanged() {
        mDataSetObservable.notifyChanged()
    }

    /**
     * Notifies the attached observers that the underlying data is no longer valid
     * or available. Once invoked this adapter is no longer valid and should
     * not report further data set changes.
     */
    fun notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated()
    }


    open fun onItemClicks(v: View, model: T?, position: Int) {}

    @SuppressLint("LongLogTag")
    fun log(content: String) {
        Log.d(mTAG, content)
    }
}

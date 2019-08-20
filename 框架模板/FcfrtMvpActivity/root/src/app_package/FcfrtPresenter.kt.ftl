package ${packageName}.mvp.${activityClassModule}.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import ${packageName}.mvp.${activityClassModule}.contract.${ContractName}
import ${packageName}.mvp.${activityClassModule}.model.${ModelName}
import com.fcfrt.baselib.base.FcfrtBasePresenter
/**
 * 项目名称：
 * 类名称：${PresenterName}
 * 类描述：${activityClass}Activity的Presenter
 * 作者：${activityClassAuthor}
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
class ${PresenterName}(mContext: Context, mView: ${ContractName}.${IViewName},mOwner: LifecycleOwner) : FcfrtBasePresenter<${ContractName}.${IViewName}, ${ModelName}>(mContext, mView, ${ModelName}(),mOwner),${ContractName}.${IPresenterName} {

}

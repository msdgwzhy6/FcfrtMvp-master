package ${packageName}.mvp.${activityClassModule}.presenter;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import ${packageName}.mvp.${activityClassModule}.contract.${ContractName};
import ${packageName}.mvp.${activityClassModule}.model.${ModelName};
import com.fcfrt.baselib.base.FcfrtBasePresenter;



/**
 * 项目名称：
 * 类名称：${PresenterName}
 * 类描述：${activityClass}Activity的Presenter
 * 作者：${activityClassAuthor}
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */
public  class ${PresenterName} extends FcfrtBasePresenter<${ContractName}.${IViewName}, ${ModelName}>implements ${ContractName}.${IPresenterName} {


    public ${PresenterName}(Context mContext, ${ContractName}.${IViewName} mView,LifecycleOwner mOwner) {
        super(mContext, mView, new ${ModelName}(),mOwner);
    }

}

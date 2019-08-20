package ${packageName}.mvp.${activityClassModule}.ui.activity;


import ${packageName}.R;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
<#if isFcfrtBaseAndBase>
    import com.fcfrt.baselib.base.FcfrtBaseActivity;
<#else>
    import ${packageName}.mvp.base.BaseActivity;
</#if>
import ${packageName}.mvp.${activityClassModule}.contract.${ContractName};
import ${packageName}.mvp.${activityClassModule}.presenter.${PresenterName};
<#if (includeCppSupport!false) && generateLayout>
import android.widget.TextView;
</#if>
/**
 * 项目名称：
 * 类名称：${activityClass}Activity
 * 类描述：${activityClassAnnotation}
 * 作者：${activityClassAuthor}
 * 创建时间：
 * 邮箱：
 * 修改简介：
 */

 <#if isFcfrtBaseAndBase>
    public class ${activityClass}Activity extends FcfrtBaseActivity<${PresenterName}>  implements ${ContractName}.${IViewName}{
 <#else>
    public class ${activityClass}Activity extends BaseActivity<${PresenterName}>  implements ${ContractName}.${IViewName}{
 </#if>



@Override
    public int getLayoutId() {
    <#if generateLayout>
            return R.layout.${layoutName};
           <#include "../../../../common/jni_code_usage.java.ftl">
    <#else>
            return 0;
            // Example of a call to a native method
            android.util.Log.d("${activityClass}", stringFromJNI());
    </#if>

    }
    /**
     * 创建presenter实例
     */
    @Override
       public ${PresenterName} createPresenter() {
           return new ${PresenterName}(this, this, this);
       }
    @Override
    public void onViewCreated() {

    }

    
    public static void launch(Context context){
           Intent intent = new Intent(context,${activityClass}Activity.class);
           context.startActivity(intent);
       }
    public static void launchFinish(Context context){
           Intent intent = new Intent(context,${activityClass}Activity.class);
           context.startActivity(intent);
           ((AppCompatActivity)context).finish();
       }
<#include "../../../../common/jni_code_snippet.java.ftl">
}

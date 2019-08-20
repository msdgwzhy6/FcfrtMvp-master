package ${escapeKotlinIdentifiers(packageName)}.mvp.${activityClassModule}.ui.activity


import ${packageName}.R
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
<#if isFcfrtBaseAndBase>
    import com.fcfrt.baselib.base.FcfrtBaseActivity
<#else>
    import ${packageName}.mvp.base.BaseActivity
</#if>
import ${packageName}.mvp.${activityClassModule}.contract.${ContractName}
import ${packageName}.mvp.${activityClassModule}.presenter.${PresenterName}
<#if (includeCppSupport!false) && generateLayout>
import kotlinx.android.synthetic.main.${layoutName}.*
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
    class ${activityClass}Activity : FcfrtBaseActivity<${PresenterName}>(), ${ContractName}.${IViewName}{
 <#else>
    class ${activityClass}Activity : BaseActivity<${PresenterName}>(), ${ContractName}.${IViewName}{
 </#if>



  override fun getLayoutId(): Int {
  <#if generateLayout>
          return R.layout.${layoutName}
           <#include "../../../../common/jni_code_usage.kt.ftl">
  <#else>
          return 0
          // Example of a call to a native method
          android.util.Log.d("${activityClass}", stringFromJNI());
  </#if>

   }


   /**
    * 创建presenter实例
    */
   override fun createPresenter(): ${PresenterName} {
       return ${PresenterName}(this, this, this)
   }

   override fun onViewCreated() {

   }

  

  companion object {
         //private const val FCFRT_ID = "data_id"
         fun launch(context: Context/*, id: Int*/) {
             val intent = Intent(context, ${activityClass}Activity::class.java)
             // intent.putExtra(FCFRT_ID, id)
             context.startActivity(intent)
         }

         fun launchFinish(context: Context) {
             val intent = Intent(context, ${activityClass}Activity::class.java)
             context.startActivity(intent)
             (context as AppCompatActivity).finish()

         }

     }
<#include "../../../../common/jni_code_snippet.kt.ftl">
}

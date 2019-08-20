<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <@kt.addAllKotlinDependencies />
    <#if needActivity>
        <merge from="root/AndroidManifest.xml.ftl"
               to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    </#if>
<#if generateLayout>
 <instantiate from="root/res/layout/fcfrt_layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
</#if>
    <instantiate from="root/src/app_package/FcfrtActivity.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/mvp/${activityClassModule}/ui/activity/${activityClass}Activity.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/FcfrtContract.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/mvp/${activityClassModule}/contract/${ContractName}.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/FcfrtModel.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/mvp/${activityClassModule}/model/${ModelName}.${ktOrJavaExt}" />
    <instantiate from="root/src/app_package/FcfrtPresenter.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/mvp/${activityClassModule}/presenter/${PresenterName}.${ktOrJavaExt}" />
    <open file="${escapeXmlAttribute(srcOut)}/mvp/${activityClassModule}/ui/activity/${activityClass}Activity.${ktOrJavaExt}" />

</recipe>

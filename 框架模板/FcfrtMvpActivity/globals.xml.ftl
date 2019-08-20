<?xml version="1.0"?>
<globals>
    <global id="hasNoActionBar" type="boolean" value="false" />
    <global id="parentActivityClass" value="" />
    <global id="simpleLayoutName" value="${layoutName}" />
    <global id="excludeMenu" type="boolean" value="true" />
    <global id="generateActivityTitle" type="boolean" value="false" />
    <#include "../common/common_globals.xml.ftl" />

    <global id="PresenterName" value="P${activityClass}Impl" />//作为presenter类名
    <global id="ModelName" value="M${activityClass}Impl" />//作为model类名
    <global id="ContractName" value="C${activityClass}" />//作为contract类名
	<global id="IViewName" value="IV${activityClass}" />//作为view层接口名
	<global id="IPresenterName" value="IP${activityClass}" />//作为presenter层接口名
</globals>

<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="${packageName}">

    <application>
		<#if isLauncher>
				  <activity android:name="${packageName}.mvp.${activityClassModule}.ui.activity.${activityClass}Activity">
					<intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
		 <#else>
		   <activity android:name="${packageName}.mvp.${activityClassModule}.ui.activity.${activityClass}Activity"/>
		</#if>


    </application>
</manifest>

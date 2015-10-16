## 1 AndroidTestApp项目
  入口UI类: [MainActivity.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidTestApp/src/com/lewi/ui/MainActivity.java)<br>
#### - sharedpreferences存储
  UI类：[sharedpreferences/SharedPreferencesDemoActivity.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidTestApp/src/com/lewi/sharedpreferences/SharedPreferencesDemoActivity.java)<br>
#### - ADIL Service的Client
  UI类：[services/ServiceActivity.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidTestApp/src/com/lewi/service/ServiceActivity.java)<br>
#### - Intent类
  UI类：[intent/IntentActivity.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidTestApp/src/com/lewi/intent/IntentActivity.java)<br>
#### - Content Provider
  UI类：[ContentProviderActivity.java](https://github.com/lewiyue/AndroidDev/tree/master/AndroidServicesDemo/src/com/lewi/contentprovider/ContentProviderActivity.java)<br>
  ContentProvder类(Robotium Repository中的RobotiumNotePad Project)：[NotePadProvider.java](https://github.com/lewiyue/Robotium/blob/master/RobotiumNotePad/src/com/example/android/notepad/NotePadProvider.java)<br>

## 2 AndroidServicesDemo项目实现了如下Services Demo
#### - Started Serviced： 扩展了IntentService类
    
  UI类：[MainActivity.java](https://github.com/lewiyue/AndroidDev/tree/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/MainActivity.java)<br>
  Service类：[services/StartedService.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/services/StartedService.java)<br>
#### - BoundService：LocalService
  UI类：[MainActivity.java](https://github.com/lewiyue/AndroidDev/tree/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/MainActivity.java)<br>
  Service类：[services/LocalService.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/services/LocalService.java)<br>
#### - BoundService：MessengerService
  UI类：[MainActivity.java](https://github.com/lewiyue/AndroidDev/tree/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/MainActivity.java)<br>
  Service类：[services/MessengerService.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/services/MessengerService.java)<br>
#### - BoundService：AIDLService
  此项目中只实现了Service部分。和此service交互UI部分在另一个项目中。<br>
    UI类：[AndroidTestApp](https://github.com/lewiyue/AndroidDev/tree/master/AndroidTestApp)项目下的[services/ServiceActivity.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidTestApp/src/com/lewi/service/ServiceActivity.java)<br>
  Service类：[services/aidlService.java](https://github.com/lewiyue/AndroidDev/blob/master/AndroidServicesDemo/src/com/lewi/androidservicesdemo/services/aidlService.java)<br>
  



package com.ds.etrip;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Created by 7YHong on 2015/10/29.
 */
public class MapActivity extends Activity implements RadarUploadInfoCallback,RadarSearchListener {
    private MapView mapView;
    private BaiduMap baiduMap;
    RadarSearchManager mManager;
    public LocationClient mLocationClient = null;

    private loC mLoc=new loC(new BDLocation());
    boolean isLoc =true;
    boolean isUpdate=false;
    boolean isZoom=true;

    Button btn_1;
    Button btn_2;
    Button btn_3;



    Handler mHandle=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mLoc= (loC) msg.obj;
            updatView();
            updatUpload();
            return true;
        }
    });



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapactivity);
        mapView= (MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        mManager = RadarSearchManager.getInstance();

        btn_1= (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Log.e("tag7", "oncreat");
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.e("tag7","onstart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        initLocation();
        if(isUpdate){
            updatView();
        }
        Log.e("tag7", "onresume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        isLoc=true;
        isUpdate=true;
        Log.e("tag7", "onpause");
    }

    @Override
    public void onStop() {
        super.onStop();
        isLoc=true;
        isUpdate=true;
        Log.e("tag7", "onstop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.stop();
        mManager.destroy();
        mManager=null;
        Log.e("tag7", "ondestroy");

    }


   private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=10000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
       mLocationClient.setLocOption(option);
       mLocationClient.registerLocationListener(myListener);    //注册监听函数
       mLocationClient.start();
    }
    protected void updatView(){
       /*LatLng point = new LatLng(mLoc.getX(), mLoc.getY());//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_mark1);//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);//在地图上添加Marker，并显示
        baiduMap.addOverlay(option);*/

        mapView.showZoomControls(false);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);


        baiduMap.setMyLocationEnabled(true);// 开启定位图层
        MyLocationData locData = new MyLocationData.Builder()// 构造定位数据
                .accuracy(mLoc.getLocation().getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(mLoc.getLocation().getLatitude())
                .longitude(mLoc.getLocation().getLongitude()).build();
        baiduMap.setMyLocationData(locData);// 设置定位数据
        isUpdate=true;
        animMap();

    }

    public void animMap() {
        if (isLoc) {
            isLoc = false;
            LatLng ll = new LatLng(mLoc.getX(),
                    mLoc.getY());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(u);
        }
    }

    protected void updatUpload(){
        //周边雷达设置用户身份标识，id为空默认是设备标识
        mManager.setUserID("123456");
        mManager.addNearbyInfoListener(this);
        //周边雷达设置监听
        //设置自动上传的callback和时间间隔
        mManager.startUploadAuto(this, 10000);

        //构造请求参数，其中centerPt是自己的位置坐标
        RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(new LatLng(mLoc.getX(), mLoc.getY())).pageNum(0).radius(20000);
//发起查询请求
        mManager.nearbyInfoRequest(option);

    }

    @Override
    public RadarUploadInfo OnUploadInfoCallback() {
        RadarUploadInfo info = new RadarUploadInfo();
        info.comments = "用户备注信息";
        info.pt = new LatLng(mLoc.getX(),mLoc.getY());//上传位置
        if(isZoom) {
            isZoom=false;
            MapStatusUpdate msu1 = MapStatusUpdateFactory.zoomTo(15);
            baiduMap.animateMapStatus(msu1);
        }
        return info;
    }

    @Override
    public void onGetNearbyInfoList(RadarNearbyResult radarNearbyResult, RadarSearchError radarSearchError) {

        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
            //获取成功，处理数据
            List<RadarNearbyInfo> myList=radarNearbyResult.infoList;
            int i=0;
            for (i=0;i<myList.size();i++){
                RadarNearbyInfo info= myList.get(i);
                //定义Maker坐标点

                Class drawable = R.drawable.class;
                Field field = null;
                int res_ID=R.drawable.icon_mark1;
                try {
                    field = drawable.getField("icon_mark" + i);
                    res_ID = field.getInt(field.getName());
                } catch (Exception e) {}

                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(res_ID);
//构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(info.pt)
                        .icon(bitmap);
//在地图上添加Marker，并显示
                baiduMap.addOverlay(option);


                Log.e("tag1",info.pt.latitude+"1");
            }
        } else {
            //获取失败
            Log.e("tag","位置获取失败");
        }

    }
    @Override
    public void onGetUploadState(RadarSearchError radarSearchError) {
        if (radarSearchError == RadarSearchError.RADAR_NO_ERROR) {
            //上传成功
            Log.e("tag","上传成功");
        } else {
            //上传失败
            Log.e("tag","上传失败");
        }

    }
    @Override
    public void onGetClearInfoState(RadarSearchError radarSearchError) {
        if(radarSearchError==RadarSearchError.RADAR_NO_ERROR){
            Log.e("tag1","成功清除");
        }else {
            Log.e("tag1","清除失败");
        }

    }

    public BDLocationListener myListener = new myLoC(mHandle);

    /**
     * Created by hymei on 2015/9/8.
     */
    public static class myLoC implements BDLocationListener {
        Handler mHandler;
        loC mLoC;
        public myLoC(Handler mHandler){
            this.mHandler=mHandler;
        }
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果

                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果

                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {;
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.e("tag", sb.toString());
            mLoC=new loC(location);
            mHandler.sendMessage(mHandler.obtainMessage(1,mLoC));
            Log.e("tag","成功获取位置");
        }
    }
    /**
     * Created by hymei on 2015/9/8.
     */
    public static class loC {
        private BDLocation location;
        public loC(BDLocation location) {
            this.location = location;
        }
        public BDLocation getLocation() {
            return location;
        }

        public void setLocation(BDLocation location) {
            this.location = location;
        }
        public double getX() {
            return location.getLatitude();
        }
        public double getY() {
            return location.getLongitude();
        }


    }
}

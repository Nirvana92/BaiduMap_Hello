package com.nirvana.activity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {
	MapView mMapView = null;
	BaiduMap baiduMap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);//关联布局文件应该放在SDKInitializer.initialize()方法下面
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		baiduMap = mMapView.getMap();
		//普通地图
		baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		//卫星地图
		//baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		
		//实时交通图(开启交通图)
		baiduMap.setTrafficEnabled(true);
		
		//百度城市热力图
		baiduMap.setBaiduHeatMapEnabled(true);
		
		//标注覆盖物
		//1.定义Maker坐标点
		LatLng point = new LatLng(39.963175, 116.400244);
		//2.构建Maker地图
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
		//3.构建MarkerOption,用于在地图上添加Marker
		OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
		//设置可拖拽
		option = new MarkerOptions()
				.position(point)//设置marker的位置
				.icon(bitmap)//设置图标
				.zIndex(9)//设置所在层级
				.draggable(true);//设置手势拖拽
		//文字覆盖物
		LatLng lat = new LatLng(39.86923, 116.397428);
		option = new TextOptions().bgColor(0xAAFFFF00).fontSize(24).fontColor(0xFFFF00FF)
				.text("Nirvana").rotate(-90).position(lat);
				
		//4.在地图上添加Marker，并显示
		baiduMap.addOverlay(option);
		
		//设置监听方法
		//调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
		baiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub
				//开始拖拽
			}
			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				//拖拽结束
			}
			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub
				//拖拽中
			}
		});
		
		//弹出窗覆盖物
		//创建InfoWindow展示的view
		Button button = new Button(getApplicationContext());
		button.setText("我是按钮");
		//button.setBackgroundResource(R.drawable.ic_launcher);
		//定义用于显示该InfoWindow的坐标点
		LatLng pt = new LatLng(39.86923, 116.397428);
		//创建InfoWindow,传入view，地理坐标，y轴偏移量
		InfoWindow infoWindow = new InfoWindow(button, pt, -47);
		//显示InfoWindow
		baiduMap.showInfoWindow(infoWindow);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
		super.onPause();
	}
}

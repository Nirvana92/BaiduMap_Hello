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
		
		// ��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext
		// ע��÷���Ҫ��setContentView����֮ǰʵ��
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);//���������ļ�Ӧ�÷���SDKInitializer.initialize()��������
		// ��ȡ��ͼ�ؼ�����
		mMapView = (MapView) findViewById(R.id.bmapView);
		baiduMap = mMapView.getMap();
		//��ͨ��ͼ
		baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		//���ǵ�ͼ
		//baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		
		//ʵʱ��ͨͼ(������ͨͼ)
		baiduMap.setTrafficEnabled(true);
		
		//�ٶȳ�������ͼ
		baiduMap.setBaiduHeatMapEnabled(true);
		
		//��ע������
		//1.����Maker�����
		LatLng point = new LatLng(39.963175, 116.400244);
		//2.����Maker��ͼ
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
		//3.����MarkerOption,�����ڵ�ͼ�����Marker
		OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
		//���ÿ���ק
		option = new MarkerOptions()
				.position(point)//����marker��λ��
				.icon(bitmap)//����ͼ��
				.zIndex(9)//�������ڲ㼶
				.draggable(true);//����������ק
		//���ָ�����
		LatLng lat = new LatLng(39.86923, 116.397428);
		option = new TextOptions().bgColor(0xAAFFFF00).fontSize(24).fontColor(0xFFFF00FF)
				.text("Nirvana").rotate(-90).position(lat);
				
		//4.�ڵ�ͼ�����Marker������ʾ
		baiduMap.addOverlay(option);
		
		//���ü�������
		//����BaiduMap�����setOnMarkerDragListener��������marker��ק�ļ���
		baiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			@Override
			public void onMarkerDragStart(Marker arg0) {
				// TODO Auto-generated method stub
				//��ʼ��ק
			}
			@Override
			public void onMarkerDragEnd(Marker arg0) {
				// TODO Auto-generated method stub
				//��ק����
			}
			@Override
			public void onMarkerDrag(Marker arg0) {
				// TODO Auto-generated method stub
				//��ק��
			}
		});
		
		//������������
		//����InfoWindowչʾ��view
		Button button = new Button(getApplicationContext());
		button.setText("���ǰ�ť");
		//button.setBackgroundResource(R.drawable.ic_launcher);
		//����������ʾ��InfoWindow�������
		LatLng pt = new LatLng(39.86923, 116.397428);
		//����InfoWindow,����view���������꣬y��ƫ����
		InfoWindow infoWindow = new InfoWindow(button, pt, -47);
		//��ʾInfoWindow
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
		// ��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// ��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// ��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���
		mMapView.onPause();
		super.onPause();
	}
}

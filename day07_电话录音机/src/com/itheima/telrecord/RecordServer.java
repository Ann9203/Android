package com.itheima.telrecord;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecordServer extends Service {

	//����¼������
	MediaRecorder record;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		//��ȡ�绰������
		TelephonyManager manager=(TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//�����绰״̬
		manager.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	//�̳е绰״̬������
	class MyListener extends PhoneStateListener
	{
		//��д�绰�ı�ķ���
		@Override
		public void onCallStateChanged(int state,String incomingNumber)
		{
			super.onCallStateChanged(state, incomingNumber);
			switch(state)
			{
			//����绰ʹ���е�״�尡
			   case TelephonyManager.CALL_STATE_IDLE:
				   //����״̬�ͽ�¼���ص�
				   	if(record!=null)
				   	{
				   		record.stop();
				   		//�ͷ���Դ
				   		record.release();
				   		record=null;
				   	}
				   	break;
				   	//���绰�����ʱ�򣬾Ϳ�ʼ����¼�� ����
			   case TelephonyManager.CALL_STATE_RINGING:
				   System.out.println("����");
				   //���¼��Ϊ�գ��Ϳ�ʼ����¼��
				   if(record==null)
				   {
					   record =new MediaRecorder();
					   //������Ƶ����Դ
					   record.setAudioSource(MediaRecorder.AudioSource.MIC);
					   //����¼���ı����ʽ
					   record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					   //���ñ����·�����ļ���
					   record.setOutputFile("sdcard/heihei.3gp");
					   //���ñ����ʽ
					   record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					   //�ȴ�
					   try {
						record.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
				   break;
			   case TelephonyManager.CALL_STATE_OFFHOOK:
				   //���绰�����ʱ��
				   System.out.println("���ڽ�ͨ�绰");
				   if(record!=null)
				   {
					   //���¼����Ϊ�յĻ����Ϳ�ʼ¼��
					   record.start();
				   }
				   break;
				   
			}
		}
	}

}

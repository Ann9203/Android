package com.jczb.checkpoint.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jczb.checkpoint.R;
import com.jczb.checkpoint.manager.AppAnBiaoRelationManager;
import com.jczb.checkpoint.manager.AppDownCongManager;
import com.jczb.checkpoint.manager.AppDownManager;
import com.jczb.checkpoint.model.AppAnBiaoRelation;
import com.jczb.checkpoint.model.AppDown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 安标信息详情页类 从查看安标证书信息列表跳转过来
 * 
 * @author wlc
 * @date 2015-4-3
 */
public class AnbiaoDetailShowActivity extends Activity implements OnClickListener {

	private AppDownManager anbiaoInfoManager;
	private AppAnBiaoRelationManager anBiaoRelationManager;
	private List<AppDown> anbiaoList;
	private List<AppAnBiaoRelation> relationList;
	private TextView tv_anbiao_code,tv_date,tv_valid_date,tv_hold_person,tv_register_addr,tv_produce_addr;
	private TextView tv_produce_unit, tv_product_name,tv_product_version,tv_standard_require,tv_scope,tv_remark,tv_state;
	private ListView lv_relation_anbiao;
	private ImageView ivGoback;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anbiao_detail_show);

		// 初始化控件
		tv_anbiao_code = (TextView) findViewById(R.id.anbiao_code_detail);
		tv_date = (TextView) findViewById(R.id.provide_date_detail);
		tv_valid_date = (TextView) findViewById(R.id.term_validity_detail);
		tv_hold_person = (TextView) findViewById(R.id.holder_person_detail);
		tv_register_addr = (TextView) findViewById(R.id.registered_address_detail);
		tv_produce_addr = (TextView) findViewById(R.id.production_address_detail);
		tv_produce_unit = (TextView) findViewById(R.id.production_unit_id_detail);
		tv_product_name = (TextView) findViewById(R.id.product_name_str_detail);
		tv_scope = (TextView) findViewById(R.id.scope_application_detail);
		tv_standard_require = (TextView) findViewById(R.id.standard_detail);
		tv_product_version = (TextView) findViewById(R.id.product_model_detail);
		tv_remark = (TextView) findViewById(R.id.remark_detail);
		tv_state = (TextView) findViewById(R.id.state_detail);
		lv_relation_anbiao = (ListView) findViewById(R.id.anbiao_relation_list_id_detail);
		ivGoback = (ImageView)findViewById(R.id.anbiao_detail_goback_img_id);
		ivGoback.setOnClickListener(this);
		// 接收传递过来的参数
		Intent intent = getIntent();
		String anbiaoId = intent.getStringExtra("params");

		// 从数据库获取记录
		// 获取安标信息
		anbiaoInfoManager = new AppDownManager(this);
		anbiaoList = anbiaoInfoManager.getOneAnbiaoInfo(anbiaoId);
		// 给页面控件赋值
		tv_standard_require.setText(anbiaoList.get(0).getStandard());
		tv_product_version.setText(anbiaoList.get(0).getProduct_model());
		tv_anbiao_code.setText(anbiaoList.get(0).getAnbiao_code());
		tv_hold_person.setText(anbiaoList.get(0).getHolder_person());
		tv_produce_addr.setText(anbiaoList.get(0).getProduction_address());
		tv_produce_unit.setText(anbiaoList.get(0).getProduction_unit_id());
		tv_product_name.setText(anbiaoList.get(0).getProduct_name());
		tv_register_addr.setText(anbiaoList.get(0).getRegistered_address());
		tv_valid_date.setText(anbiaoList.get(0).getTerm_validity());
		tv_date.setText(anbiaoList.get(0).getProvide_date());
		tv_state.setText(anbiaoList.get(0).getState());
		tv_scope.setText(anbiaoList.get(0).getScope_application());
		tv_remark.setText(anbiaoList.get(0).getRemark());
		// 获取安标关联表的信息
		anBiaoRelationManager = new AppAnBiaoRelationManager(this);
		relationList = anBiaoRelationManager.getRelationInfo(anbiaoId);

		// 使用simpleAdapter给ListView加载数据，注意参数对应的顺序
		List<Map<String, Object>> relationData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < relationList.size(); i++) {
			Map<String, Object> list = new HashMap<String, Object>();
			list.put("id", i + 1);
			list.put("viceId", relationList.get(i).getViceAnBiaoID());
			relationData.add(list);
		}
		SimpleAdapter anbiaoInfoAdapter = new SimpleAdapter(
				AnbiaoDetailShowActivity.this, relationData,
				R.layout.activity_anbiao_detail_show_item, new String[] { "id",
						"viceId" }, new int[] {
						R.id.anbiao_relation_name_id_detail,
						R.id.anbiao_relation_code_id_detail });
		lv_relation_anbiao.setAdapter(anbiaoInfoAdapter);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.anbiao_detail_goback_img_id:
			intent = new Intent(AnbiaoDetailShowActivity.this, QueryDownloadListActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
		
	}

}

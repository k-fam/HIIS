package myApp.service.rpt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import myApp.frame.service.ServiceRequest;
import myApp.frame.service.ServiceResult;
import myApp.frame.ui.AbstractDataModel;

public class DailyAccount { 
	
	String mapperName = "rpt01_DailyAccount"; 
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		System.out.println("rpt01_DailyAccount param: " + request.getLong("companyId") ); 

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", request.getLong("companyId"));
		param.put("beginDate", request.getDate("beginDate"));
		param.put("endDate", request.getDate("endDate"));
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", param);

		System.out.println("rpt01_DailyAccount size: " + list.size() );
		
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyIdProc(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
	
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", request.getLong("companyId"));
		param.put("beginDate", request.getDate("beginDate"));
		param.put("endDate", request.getDate("endDate"));

		sqlSession.selectList(mapperName + ".selectByCompanyIdProc", param);

		@SuppressWarnings("unchecked")
		List<AbstractDataModel> list = (List<AbstractDataModel>)param.get("result"); 

		System.out.println("rpt01_DailyAccount size list: " + list.size() );

		result.setRetrieveResult(1, "select ok", list);
	}
}

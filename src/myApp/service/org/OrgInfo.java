package myApp.service.org;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import myApp.client.org.model.OrgInfoModel;
import myApp.frame.service.ServiceRequest;
import myApp.frame.service.ServiceResult;
import myApp.frame.ui.AbstractDataModel;
import myApp.server.data.UpdateDataModel;

public class OrgInfo { 
	
	String mapperName = "org02_info"; 
	
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		Long companyId = request.getLong("companyId"); 
		Date baseDate = request.getDate("baseDate"); 
				
		Map<String, Object> param = new HashMap<String, Object>();  
		param.put("companyId", companyId); 
		param.put("baseDate", baseDate);
		param.put("parentId", Long.parseLong("0"));
		
		List<AbstractDataModel> orgList
			= sqlSession.selectList(mapperName + ".selectByParentId", param);

		for(AbstractDataModel child : orgList){
			OrgInfoModel orgInfoModel = (OrgInfoModel)child;
			List<AbstractDataModel> childList = getChildItem(sqlSession, companyId, baseDate, orgInfoModel.getInfoId());  
			orgInfoModel.setChildList(childList); 	
		}
		result.setRetrieveResult(orgList.size(), "하위메뉴 조회", orgList);
	}
	
	private List<AbstractDataModel> getChildItem(SqlSession sqlSession, Long companyId, Date baseDate, Long parentId){
		
		
		Map<String, Object> param = new HashMap<String, Object>();  
		param.put("companyId", companyId); 
		param.put("baseDate", baseDate);
		param.put("parentId", parentId);
		
		List<AbstractDataModel> orgList = sqlSession.selectList(mapperName + ".selectByParentId", param);

		for(AbstractDataModel child : orgList){
			OrgInfoModel orgInfoModel = (OrgInfoModel)child;
			
//			System.out.println("child menu is " + menuModel.getMenuName());
			
			List<AbstractDataModel> childList = getChildItem(sqlSession, companyId, baseDate, orgInfoModel.getInfoId());  
			orgInfoModel.setChildList(childList); 	
		}

		return orgList ; 
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<OrgInfoModel> updateModel = new UpdateDataModel<OrgInfoModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<OrgInfoModel> updateModel = new UpdateDataModel<OrgInfoModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}

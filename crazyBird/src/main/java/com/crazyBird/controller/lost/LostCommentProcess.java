package com.crazyBird.controller.lost;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.BaseProcess;
import com.crazyBird.controller.lost.model.LostCommentItem;
import com.crazyBird.controller.lost.model.LostCommentListItem;
import com.crazyBird.controller.lost.model.LostCommentPageModel;
import com.crazyBird.controller.lost.model.LostInputModel;
import com.crazyBird.controller.lost.param.LostCommentPageParam;
import com.crazyBird.controller.lost.param.LostCommentParam;
import com.crazyBird.dao.lost.dataobject.LostCommentDO;
import com.crazyBird.dao.lost.dataobject.LostDO;
import com.crazyBird.dao.lost.dataobject.LostReplyDO;
import com.crazyBird.dao.lost.dataobject.LostReplyPO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.lost.LostCommentService;
import com.crazyBird.utils.PageUtils;
import com.crazyBird.utils.TokenUtils;

@Component
public class LostCommentProcess extends BaseProcess{
	@Autowired
	private LostCommentService lostCommentService;
	
	public LostInputModel lostCommentIn(LostCommentParam param) {
		LostInputModel model=new LostInputModel();
		
		Long shoolNum = (long) 0;
		try {
			shoolNum = TokenUtils.getIdFromAesStr(getReqParam().getReqHead().getAccessToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long test=shoolNum;
		if(test==(long)0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("登录状态无效");
			return model;
		}
		LostCommentDO DO=new LostCommentDO();
		DO.setArticleId(param.getArticleId());
		DO.setComment(param.getComment());
		DO.setCommentId(param.getCommentId());
		DO.setReplyedId(param.getReplyedId());
		DO.setReplyId(param.getReplyId());
		ResponseDO<LostDO> response=lostCommentService.commentInput(DO);
		if(!response.isSuccess()) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}else{
			model.setCode(HttpCodeEnum.SUCCESS.getCode());
			model.setMessage(response.getMessage());
		}
		return model;
	}

	public LostCommentPageModel getLostCommentList(LostCommentPageParam param) {
		LostCommentPageModel model=new LostCommentPageModel();
		if(param.getArticleId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("请重新打开页面");
			return model;
		}
		LostReplyPO po=new LostReplyPO();
		po.setPageIndex(param.getPageNo().intValue() - 1);
		po.setPageSize(param.getPageSize().intValue());
		po.setArticleId(param.getArticleId());
		ResponsePageQueryDO<List<LostReplyDO>> response=lostCommentService.getCommentList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setItems(convertDemands(response.getDataResult(),po.getPageIndex(),po.getPageSize()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
		}
		model.setMessage(response.getMessage());
		return model;
	}

	private List<LostCommentItem> convertDemands(List<LostReplyDO> dataResults, int pageIndex, int pageSize) {
		List<LostCommentItem> items=new ArrayList<>();
		for(LostReplyDO dataResult:dataResults) {
			List<LostCommentListItem> llcli=new ArrayList<>();
			LostCommentItem item=new LostCommentItem();
			
			LostReplyPO po=new LostReplyPO();
			po.setPageIndex(pageIndex);
			po.setPageSize(pageSize);
			po.setCommentId(dataResult.getId());
			ResponsePageQueryDO<List<LostReplyDO>> response=lostCommentService.getCommentItem(po);
			
			List<LostReplyDO> data=response.getDataResult();
			if(data!=null) {
				for(LostReplyDO responsed:data) {
					LostCommentListItem lcli=new LostCommentListItem();
					lcli.setArticleId(responsed.getArticleId());
					lcli.setComment(responsed.getComment());
					lcli.setCommentId(responsed.getCommentId());
					lcli.setGmtCreated(responsed.getGmtCreated());
					lcli.setId(responsed.getId());
					lcli.setReplyedId(responsed.getReplyedId());
					lcli.setReplyId(responsed.getReplyId());
					llcli.add(lcli);
				}
			}
			item.setItem(llcli);
			item.setArticleId(dataResult.getArticleId());
			item.setComment(dataResult.getComment());
			item.setGmtCreated(dataResult.getGmtCreated());
			item.setId(dataResult.getId());
			items.add(item);
		}
		return items;
	}

	

}

package com.mornsun.app.core.service.product.base.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.orm.mybatis.service.page.impl.BasePageHelperApiServiceImpl;
import com.common.util.ExceptionUtil;
import com.common.util.SessionUtil;
import com.mornsun.app.core.service.brand.IBrandService;
import com.mornsun.app.core.service.catalog.ICatalogService;
import com.mornsun.app.core.service.product.applyarea.IProductApplyAreaService;
import com.mornsun.app.core.service.product.atta.IProductAttaService;
import com.mornsun.app.core.service.product.base.IProductSearchService;
import com.mornsun.app.core.service.product.param.IProductParamService;
import com.mornsun.app.core.service.product.replace.IProductReplaceService;
import com.mornsun.app.core.service.product.tag.IProductTagService;
import com.mornsun.app.core.service.search.record.ISearchRecordService;
import com.mornsun.app.core.service.user.course.IUserCourseService;
import com.mornsun.app.core.service.user.favorite.IUserFavoriteService;
import com.mornsun.jsw.api.api.product.base.IProductSearchApi;
import com.mornsun.jsw.api.vo.brand.BrandVo;
import com.mornsun.jsw.api.vo.catalog.CatalogVo;
import com.mornsun.jsw.api.vo.product.applyarea.ProductApplyAreaVo;
import com.mornsun.jsw.api.vo.product.atta.ProductAttaVo;
import com.mornsun.jsw.api.vo.product.base.SearchVo;
import com.mornsun.jsw.api.vo.product.param.ProductParamVo;
import com.mornsun.jsw.api.vo.product.replace.ProductReplaceVo;
import com.mornsun.jsw.api.vo.product.tag.ProductTagVo;
import com.mornsun.jsw.api.vo.search.record.SearchRecordVo;
import com.mornsun.jsw.api.vo.user.base.UserVo;
import com.mornsun.jsw.api.vo.user.course.UserCourseVo;
import com.mornsun.jsw.api.vo.user.favorite.UserFavoriteVo;

/**
 * 产品Service
 *
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class ProductSearchServiceImpl extends BasePageHelperApiServiceImpl<SearchVo, IProductSearchApi>
		implements IProductSearchService {

	@Autowired
	private IProductAttaService productAttaService;

	@Autowired
	private IProductApplyAreaService productApplyAreaService;

	@Autowired
	private IProductTagService productTagService;

	@Autowired
	private IProductReplaceService productReplaceService;

	@Autowired
	private IProductParamService productParamService;

	@Autowired
	private ISearchRecordService searchRecordService;

	@Autowired
	private IUserCourseService userCourseService;

	@Autowired
	private IMemcacheService memcacheService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private ICatalogService catalogService;

	@Autowired
	private IUserFavoriteService userFavoriteService;

	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<SearchVo> list(SearchVo obj, PageListVo<SearchVo> pageListVo, HttpServletRequest request)
			throws Exception {
		ResultVo<SearchVo> resultVo = new ResultVo<SearchVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			resultVo = super.list(obj, pageListVo, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {

				List<SearchVo> searchVos = resultVo.getPageListVo().getRows();
				for (SearchVo searchVoTmp : searchVos) {
					// 查询标签
					ProductTagVo productTagVo = new ProductTagVo();
					productTagVo.setProductId(searchVoTmp.getId());
					productTagVo.setIsavailable(STATUS_YES);
					searchVoTmp.setProductTagVos(productTagService.getAll(productTagVo));
				}

				if (StringUtils.isNotEmpty(obj.getKeyword())) {
					// 记录搜索记录
					SearchRecordVo searchRecordVo = new SearchRecordVo();
					searchRecordVo.setKeyword(obj.getKeyword());
					searchRecordVo.setResultCount((int) resultVo.getPageListVo().getTotal());
					searchRecordVo.setCreateBy("sys");
					searchRecordVo.setIsavailable(STATUS_YES);
					ResultVo<SearchRecordVo> resultVoTmp = searchRecordService.save(searchRecordVo, request);
					res = resultVoTmp.getRes();
					if (res == RESULT_SUCCESS) {
						SearchVo searchVo = new SearchVo();
						searchVo.setSearchRecordId(resultVoTmp.getObj().getId());
						resultVo.setObj(searchVo);
					}
				}
			}

		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 查询详情
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<SearchVo> detail(String id, HttpServletRequest request) throws Exception {
		ResultVo<SearchVo> resultVo = new ResultVo<SearchVo>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			resultVo = super.detail(id, request);
			res = resultVo.getRes();
			if (resultVo.getRes() == RESULT_SUCCESS) {

				SearchVo searchVo = resultVo.getObj();

				// 查询产品秒懂
				UserCourseVo userCourseVo = new UserCourseVo();
				userCourseVo.setProductId(searchVo.getId());
				PageListVo<UserCourseVo> userCoursePageListVo = userCourseService.getPage(userCourseVo,
						new PageListVo<>());
				searchVo.setUserCourseVos(userCoursePageListVo.getRows());

				// 查询产品问答

				// 查询产品品牌
				BrandVo brandVo = brandService.getOneById(searchVo.getBrandId());
				searchVo.setBrandVo(brandVo);

				// 查询产品分类
				CatalogVo catalogVo = catalogService.getOneById(searchVo.getCatalogId());
				searchVo.setCatalogVo(catalogVo);

				// 查询应用领域
				ProductApplyAreaVo productApplyAreaVo = new ProductApplyAreaVo();
				productApplyAreaVo.setProductId(searchVo.getId());
				searchVo.setProductApplyAreaVos(productApplyAreaService.getAll(productApplyAreaVo));

				// 查询标签
				ProductTagVo productTagVo = new ProductTagVo();
				productTagVo.setProductId(searchVo.getId());
				searchVo.setProductTagVos(productTagService.getAll(productTagVo));

				// 查询替换产品
				ProductReplaceVo productReplaceVo = new ProductReplaceVo();
				productReplaceVo.setProductId(searchVo.getId());
				searchVo.setProductReplaceCount(productReplaceService.getCount(productReplaceVo));

				// 查询产品参数
				ProductParamVo productParamVo = new ProductParamVo();
				productParamVo.setProductId(searchVo.getId());
				searchVo.setProductParamVos(productParamService.getAll(productParamVo));

				// 查询附件
				ProductAttaVo productAttaVo = new ProductAttaVo();
				productAttaVo.setProductId(searchVo.getId());
				searchVo.setProductAttaVos(productAttaService.getAll(productAttaVo));

				// 获取登录信息
				searchVo.setIsFavorite(STATUS_NO);
				UserVo userVo = (UserVo) memcacheService.get(SessionUtil.getInstance().getSessionId(request));
				if (userVo != null) {
					// 判断用户是否收藏
					UserFavoriteVo userFavoriteVo = new UserFavoriteVo();
					userFavoriteVo.setUserId(userVo.getId());
					userFavoriteVo.setSourceId(id);
					userFavoriteVo = userFavoriteService.getOne(userFavoriteVo);
					if (userFavoriteVo != null) {
						searchVo.setIsFavorite(STATUS_YES);
					}
				}

				resultVo.setObj(searchVo);
			}

		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

}

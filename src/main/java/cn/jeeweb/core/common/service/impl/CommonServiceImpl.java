package cn.jeeweb.core.common.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.jeeweb.core.common.dao.ICommonDao;
import cn.jeeweb.core.common.dao.support.OrderHelper;
import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.parse.CriteriaParse;
import cn.jeeweb.core.query.parse.QueryParse;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.utils.ReflectionUtils;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
public class CommonServiceImpl<T extends Serializable> implements ICommonService<T> {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ICommonDao commonDao;
	protected Class<T> entityClass;

	public CommonServiceImpl() {
		this.entityClass = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void save(T entity) {
		commonDao.save(entity);
	}

	@Override
	public void batchSave(List<T> entitys) {
		commonDao.batchSave(entitys);
	}

	@Override
	public void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		commonDao.update(entity);
	}

	@Override
	public void merge(T entity) {
		commonDao.merge(entity);
	}

	@Override
	public T load(Serializable id) {
		return commonDao.load(id, entityClass);
	}

	@Override
	public T get(Serializable id) {
		return commonDao.get(id, entityClass);
	}

	@Override
	public T get(String propertyName, Object value) {
		return commonDao.get(propertyName, value, entityClass);
	}

	@Override
	public void delete(T entity) {
		commonDao.delete(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		commonDao.deleteById(id, entityClass);
	}

	@Override
	public void batchDelete(List<T> entitys) {
		commonDao.batchDelete(entitys);
	}

	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object id : ids) {
			commonDao.deleteById((Serializable) id, entityClass);
		}
	}

	@Override
	public void batchDeleteByProperty(String propertyName, Object value) {
		commonDao.batchDeleteByProperty(propertyName, value, entityClass);
	}

	@Override
	public int count() {
		return commonDao.count(entityClass);
	}

	@Override
	public List<T> list() {
		return commonDao.list(entityClass);
	}

	@Override
	public List<T> list(OrderHelper orderHelper) {
		return commonDao.list(entityClass, orderHelper);
	}

	@Override
	public List<T> list(int page, int rows, DetachedCriteria detachedCriteria) {
		return commonDao.list(page, rows, detachedCriteria);
	}

	@Override
	public Page<T> list(Pageable pageable, DetachedCriteria detachedCriteria) {
		Long total = commonDao.count(detachedCriteria);
		List<T> content = list(pageable.getPageNumber(), pageable.getPageSize(), detachedCriteria);
		return new PageImpl<T>(content, pageable, total);
	}

	@Override
	public Page<T> list(Queryable queryable, DetachedCriteria detachedCriteria) {
		QueryParse<DetachedCriteria> queryParse = new CriteriaParse();
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		queryParse.parseCondition(detachedCriteria, queryable);
		Long total = commonDao.count(detachedCriteria);
		//排序问题
		queryParse.parseSort(detachedCriteria, queryable);
		Pageable pageable = queryable.getPageable();
		List<T> content = list(pageable.getPageNumber(), pageable.getPageSize(), detachedCriteria);
		return new PageImpl<T>(content, queryable.getPageable(), total);
	}

	@Override
	public List<T> listWithNoPage(Queryable queryable, DetachedCriteria detachedCriteria) {
		QueryParse<DetachedCriteria> queryParse = new CriteriaParse();
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		queryParse.parseCondition(detachedCriteria, queryable);
		queryParse.parseSort(detachedCriteria, queryable);
		return commonDao.list(detachedCriteria);
	}

	@Override
	public int updateByHql(String hql, Object... params) {
		return commonDao.updateByHql(hql, params);
	}

	@Override
	public int updateByIndexHql(String hql, Object... params) {
		Map<String, Object> alias = new HashMap<String, Object>();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				alias.put((i + 1) + "", params[i]);
			}
		}
		return updateByAliasHql(hql, alias);
	}

	@Override
	public int updateByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.updateByAliasHql(hql, alias);
	}

	@Override
	public int count(Criterion... criterions) {
		return commonDao.count(entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(Criterion... criterions) {
		return commonDao.listByCriterion(entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(OrderHelper orderHelper, Criterion... criterions) {
		return commonDao.listByCriterion(entityClass, orderHelper, criterions);
	}

	@Override
	public List<T> listByCriterion(int page, int rows, Criterion... criterions) {
		return commonDao.listByCriterion(page, rows, entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(OrderHelper orderHelper, int page, int rows, Criterion... criterions) {
		return commonDao.listByCriterion(page, rows, entityClass, orderHelper, criterions);
	}

	@Override
	public Long countByHql(String hql, Object... params) {
		return commonDao.countByHql(hql, params);
	}

	@Override
	public Long countByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.countByAliasHql(hql, alias);
	}

	@Override
	public List<T> listByHql(String hql, Object... params) {
		return commonDao.listByHql(hql, params);
	}

	@Override
	public List<T> listByHql(String hql, int page, int rows, Object... params) {
		return commonDao.listByHql(hql, page, rows, params);
	}

	@Override
	public List<T> listByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.listByAliasHql(hql, alias);
	}

	@Override
	public List<Map<String, Object>> listMapByHql(String hql, Object... params) {
		return commonDao.listMapByHql(hql, params);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.listMapByAliasHql(hql, alias);
	}

	@Override
	public List<T> list(String propertyName, Object value) {
		return commonDao.list(propertyName, value, entityClass);
	}

	@Override
	public void executeSql(String sql, Object... params) {
		commonDao.executeSql(sql, params);
	}

	@Override
	public void executeAliasSql(String sql, Map<String, Object> alias) {
		commonDao.executeAliasSql(sql, alias);
	}

	@Override
	public Integer countBySql(String sql, Object... params) {
		return commonDao.countBySql(sql, params);
	}

	@Override
	public Integer countByAliasSql(String sql, Map<String, Object> alias) {
		return commonDao.countByAliasSql(sql, alias);
	}

	@Override
	public List<Map<String, Object>> listBySql(String sql, Object... params) {
		return commonDao.listBySql(sql, params);
	}

	@Override
	public List<Map<String, Object>> listByAliasSql(String sql, Map<String, Object> alias) {
		return commonDao.listByAliasSql(sql, alias);
	}

	@Override
	public List<Map<String, Object>> listPageBySql(String sql, int page, int rows, Object... params) {
		return commonDao.listPageBySql(sql, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSql(String sql, int page, int rows, Map<String, Object> alias) {
		return commonDao.listPageByAliasSql(sql, page, rows, alias);
	}

	@Override
	public List<T> listEntityBySql(String sql, Object... params) {
		return commonDao.listEntityBySql(sql, entityClass, params);
	}

	@Override
	public List<T> listEntityByAliasSql(String sql, Map<String, Object> alias) {
		return commonDao.listEntityByAliasSql(sql, entityClass, alias);
	}

	@Override
	public List<T> listPageEntityBySql(String sql, int page, int rows, Object... params) {
		return commonDao.listPageEntityBySql(sql, page, rows, entityClass, params);
	}

	@Override
	public List<T> listPageEntityByAliasSql(String sql, int page, int rows, Map<String, Object> alias) {
		return commonDao.listPageEntityByAliasSql(sql, page, rows, entityClass, alias);
	}

	@Override
	public int updateByHqlQueryId(String queryId, Object... params) {
		return commonDao.updateByHqlQueryId(queryId, params);
	}

	@Override
	public int updateByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.updateByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public Long countByHqlQueryId(String queryId, Object... params) {
		return commonDao.countByHqlQueryId(queryId, params);
	}

	@Override
	public Long countByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.countByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public List<T> listByHqlQueryId(String queryId, Object... params) {
		return commonDao.listByHqlQueryId(queryId, params);
	}

	@Override
	public List<T> listByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.listByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public List<T> listByHqlQueryId(String queryId, int page, int rows, Object... params) {
		return commonDao.listByHqlQueryId(queryId, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, Object... params) {
		return commonDao.listMapByHqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, int page, int rows, Object... params) {
		return commonDao.listMapByHqlQueryId(queryId, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.listMapByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		return commonDao.listMapByAliasHqlQueryId(queryId, page, rows, alias);
	}

	@Override
	public void executeSqlQueryId(String queryId, Object... params) {
		commonDao.executeSqlQueryId(queryId, params);
	}

	@Override
	public Integer countBySqlQueryId(String queryId, Object... params) {
		return commonDao.countBySqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listBySqlQueryId(String queryId, Object... params) {
		return commonDao.listBySqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listByAliasSqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.listByAliasSqlQueryId(queryId, alias);
	}

	@Override
	public List<Map<String, Object>> listPageBySqlQueryId(String queryId, int page, int rows, Object... params) {
		return commonDao.listPageBySqlQueryId(queryId, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		return commonDao.listPageByAliasSqlQueryId(queryId, page, rows, alias);
	}

	@Override
	public List<T> listEntityBySqlQueryId(String queryId, Object... params) {
		return commonDao.listEntityBySqlQueryId(queryId, entityClass, params);
	}

	@Override
	public List<T> listEntityByAliasSqlQueryId(String queryId, Map<String, Object> alias) {
		return commonDao.listEntityByAliasSqlQueryId(queryId, entityClass, alias);
	}

	@Override
	public List<T> listPageEntityBySqlQueryId(String queryId, int page, int rows, Object... params) {
		return commonDao.listPageEntityBySqlQueryId(queryId, page, rows, entityClass, params);
	}

	@Override
	public List<T> listPageEntityByAliasSqlQueryId(String queryId, int page, int rows, Map<String, Object> alias) {
		return commonDao.listPageEntityByAliasSqlQueryId(queryId, page, rows, entityClass, alias);
	}

	@Override
	public Boolean doValid(DuplicateValid duplicateValid) {
		Boolean valid = Boolean.FALSE;
		String queryType = duplicateValid.getQueryType();
		if (StringUtils.isEmpty(queryType)) {
			queryType = "table";
		}
		if (queryType.equals("table")) {
			valid = validTable(duplicateValid);
		}
		return valid;
	}

	private Boolean validTable(DuplicateValid duplicateValid) {
		Long num = null;
		String hql = "";
		String extendName = duplicateValid.getExtendName();
		String extendParam = duplicateValid.getExtendParam();
		if (!StringUtils.isEmpty(extendParam)) {
			// [2].编辑页面校验
			hql = "SELECT count(*) FROM " + entityClass.getName() + " as t WHERE t." + duplicateValid.getName() + " ='"
					+ duplicateValid.getParam() + "' and t." + extendName + " != '" + extendParam + "'";
			num = countByHql(hql);
		} else {
			// [1].添加页面校验
			hql = "SELECT count(*) FROM " + entityClass.getName() + " as t WHERE t." + duplicateValid.getName() + " ='"
					+ duplicateValid.getParam() + "'";
			num = countByHql(hql);
		}

		if (num == null || num == 0) {
			// 该值可用
			return true;
		} else {
			// 该值不可用
			return false;
		}
	}
}
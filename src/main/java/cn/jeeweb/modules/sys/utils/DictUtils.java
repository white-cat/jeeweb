package cn.jeeweb.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;

import cn.jeeweb.core.utils.CacheUtils;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.sys.Constants;
import cn.jeeweb.modules.sys.entity.DictEntity;
import cn.jeeweb.modules.sys.service.IDictService;

/**
 * 字典工具类
 * 
 * @author 王存见
 * @version 2017-02-09
 */
@SuppressWarnings("unchecked")
public class DictUtils {

	private static IDictService dictService = SpringContextHolder.getBean(IDictService.class);

	public static String getDictLabel(String value, String code, String defaultValue) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(value)) {
			for (DictEntity dict : getDictList(code)) {
				if (code.equals(dict.getCode()) && value.equals(dict.getValue())) {
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictLabels(String values, String code, String defaultValue) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(values)) {
			List<String> valueList = new ArrayList<String>();
			for (String value : StringUtils.split(values, ",")) {
				valueList.add(getDictLabel(value, code, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String code, String defaultLabel) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(label)) {
			for (DictEntity dict : getDictList(code)) {
				if (code.equals(dict.getCode()) && label.equals(dict.getLabel())) {
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	public static List<DictEntity> getDictList(String code) {
		Map<String, List<DictEntity>> dictMap = (Map<String, List<DictEntity>>) CacheUtils
				.get(Constants.CACHE_DICT_MAP);
		if (dictMap == null) {
			dictMap = new HashMap<String, List<DictEntity>>();
			for (DictEntity dict : dictService.list()) {
				List<DictEntity> dictList = dictMap.get(dict.getCode());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getCode(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(Constants.CACHE_DICT_MAP, dictMap);
		}
		List<DictEntity> dictList = dictMap.get(code);
		if (dictList == null) {
			dictList = new ArrayList<DictEntity>();
		}
		return dictList;
	}

	/*
	 * 清除换成
	 */
	public static void clear() {
		CacheUtils.remove(Constants.CACHE_DICT_MAP);
	}

}

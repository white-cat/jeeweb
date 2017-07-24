package cn.jeeweb.core.common.entity;

/**
 * 为了简化开发 约定删除标识列名为delflag，
 * 
 * @author 王存见
 *
 */
public interface MarkDeleteable {
	/**
	 * 删除标记（0：正常；1：删除 ）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";

	public String getDelFlag();

	public void setDelFlag(String delFlag);

	/**
	 * 这表数据是否需要标记删除
	 * 
	 * @param delflag
	 */
	public void markDelete(Boolean isDelete);

	public Boolean markStatus();

}

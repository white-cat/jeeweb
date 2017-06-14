package cn.jeeweb.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Utils {
	public final static int DateType_START = 1;
	public final static int DateType_CURRENT = 2;
	public final static int DateType_END = 3;

	/**
	 * 
	 * 
	 * /** 获取JOSONObject对象的PropertyName属性值
	 * 
	 * @param obj
	 *            JSONObject 对象
	 * @param propertyName
	 *            属性
	 * @return
	 */
	public static Object getJSONValue(JSONObject obj, String propertyName) {
		Object rv = null;
		if (obj == null)
			return null;
		if (!obj.has(propertyName))
			return null;
		try {
			rv = obj.get(propertyName);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rv;
	}

	public static String readWord(StringBuffer Str, String split) {
		String rv = "";
		int index = Str.indexOf(split);
		if (index > -1) {
			rv = Str.substring(0, index);
			Str.delete(0, index + split.length());
		} else {
			rv = Str.toString();
			Str.delete(0, Str.length());
		}
		return rv;
	}

	public static String readWord(String Str, String b_flag, String e_flag) {
		String rv = "";
		int b_index = Str.indexOf(b_flag);
		int e_index = Str.indexOf(e_flag);
		if (b_index > -1 && e_index > b_index) {
			rv = Str.substring(b_index + b_flag.length(), e_index);

		} else {
			rv = "";

		}
		return rv;
	}

	public static boolean isNumeric(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*([Ee]{1}[0-9]+)?");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isEoN(Object testStr) {
		return (testStr == null || testStr.toString().trim().equals("") || testStr.toString().trim().equals("null"));
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str) || "null".equals(str);
	}

	/**
	 * @param date日期
	 * @return日期格式
	 */
	private static String analyseDateFormat(String date) {
		String result = null;
		String input = date.trim();
		if (Pattern.matches("\\d{2,4}-\\d{1,2}-\\d{1,2}.*", input)) {
			result = "yyyy-MM-dd";
		} else if (Pattern.matches("\\d{1,2}-\\d{1,2}-\\d{2,4}.*", input)) {
			result = "MM-dd-yyyy";
		} else if (Pattern.matches("\\d{2,4}/\\d{1,2}/\\d{1,2}.*", input)) {
			result = "yyyy/MM/dd";
		} else if (Pattern.matches("\\d{1,2}/\\d{1,2}/\\d{2,4}.*", input)) {
			result = "MM/dd/yyyy";
		} else if (Pattern.matches("\\d{6,8}.*", input)) {
			result = "yyyyMMdd";
		}
		if (date.trim().length() > 12) {
			result = result + " HH:mm:ss";
		}
		return result;
	}

	public static Date parseDate(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		if (date != null && date.indexOf("CST") != -1) {
			Date dateLocal = parse(date, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			try {
				return df.parse(df.format(dateLocal));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			return df.parse(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Date parse(String str, String pattern, Locale locale) {
		if (str == null || pattern == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern, locale).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return null;
		int mYear = date.getYear() + 1900;
		int mMonth = date.getMonth();
		int mDay = date.getDate();
		StringBuilder sb = new StringBuilder();
		sb.append(mYear).append("-").append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
				.append((mDay < 10) ? "0" + mDay : mDay);
		return sb.toString();
	}

	public static String formatDate(Date date, int DateType) {
		SimpleDateFormat sdf;
		switch (DateType) {
		case DateType_START:
			sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			break;
		case DateType_END:
			sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			break;
		default:
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		}
		return sdf.format(date);
	}

	public static Date parseDateN(Object date) {
		if (date != null) {
			return parseDate(date.toString());
		} else {
			return parseDate("1900-01-01");
		}
	}

	public static double parseDoubleN(Object data) {
		if (data != null) {
			try {
				return Double.parseDouble(data.toString());
			} catch (Exception ex) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static float parseFloatN(Object data) {
		if (data != null) {
			try {
				return Float.parseFloat(data.toString());
			} catch (Exception ex) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static int parseIntN(Object data) {
		return parseIntN(data, 0);

	}

	public static int parseIntN(Object data, int def) {
		if (data != null) {
			try {
				return Integer.parseInt(data.toString());
			} catch (Exception ex) {
				return def;
			}
		} else {
			return def;
		}
	}

	public static String parseStringN(Object data) {
		if (data == null || data.toString().toLowerCase().equals("null")) {
			return "";
		} else {
			return data.toString();
		}
	}

	public static Date parseDate(String date) {
		try {
			if (!Utils.isEoN(date) && date.indexOf(".") != -1) {
				date = date.replaceAll("\\.", "-");
			}
			String formatStr = analyseDateFormat(date);
			if (formatStr == null) {
				DateFormat sd = SimpleDateFormat.getDateTimeInstance();
				return sd.parse(date);// .parse(date);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
				return sdf.parse(date);
			}
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> toMap(JSONObject json) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject
		// SONObject jsonObject = JSONObject.fromObject(object);
		Iterator it = json.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value;
			try {
				value = json.get(key);
				data.put(key, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return data;
	}

	public static String toString(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		DOMSource source = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(source, result);
		return (writer.getBuffer().toString());
	}

	public static Long getTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 得到 4 位验证码
	 * 
	 * @return
	 */
	public static String getMobileCode() {
		Random rd = new Random();
		String checkCode = "";
		for (int i = 0; i < 4; i++) {
			checkCode += rd.nextInt(10);
		}
		checkCode = checkCode.trim();
		return checkCode;
	}

	/**
	 * 获取json内部对象的属性值，即：json对象.json内部对象.属性
	 * 
	 * @param json
	 * @param innerObjName
	 * @return 返回值分三种类型：1）返回内部对象属性值; 2）返回null,代表该属性未定义，一般后续不对其及所属内部对象做任何操作; 3)
	 *         返回 "",代表该属性置空，如属性为id，一般指要设置该对象为空：set 对象.内部对象=null，
	 *         当属性值为以下值时：null,"","null",返回该值 一对多关系操作时，多方将一方成员变量置空就是要设置成该值
	 * 
	 */
	public static String getInnerObjectId(JSONObject json, String innerObjName, String fieldName) {
		Object innerObj = json.get(innerObjName);
		if (innerObj == null)
			return null; // 内部对象不存在，返回null
		if (!(innerObj instanceof JSONObject))
			return null; // 内部对象不是标准对象，返回null
		if (!(((JSONObject) innerObj).containsKey(fieldName)))
			return null;// 内部对象不包括指定属性，返回null

		if (((JSONObject) innerObj).get(fieldName) == null)
			return "";
		String result = ((JSONObject) innerObj).get(fieldName).toString();
		if (result.trim().toLowerCase().equals("") || result.trim().toLowerCase().equals("null")) {
			return "";
		}

		return ((JSONObject) innerObj).get(fieldName).toString();

	}

	/**
	 * 将字符串object 用ch字符填充,总长度为length 如果object 长度超过length,则返回object
	 * 
	 * @param object
	 * @param trim
	 * @param length
	 * @return
	 */
	public static String trim(String object, char ch, int length) {
		String result = object;
		if (object.length() >= length)
			return object;
		else {
			for (int i = object.length(); i <= length; i++) {
				result = String.valueOf(ch).concat(result);
			}
			return result;
		}
	}

	public static String json2Str(JSONObject propertys, String propertyName) {

		return json2Str(propertys, propertyName, null);
	}

	public static String json2Str(JSONObject propertys, String propertyName, String defValue) {

		try {
			if (propertys.containsKey(propertyName)) {
				if (emptyStr2Null(propertys.get(propertyName).toString()) == null) {
					return defValue;
				} else {
					return propertys.get(propertyName).toString();
				}
			} else {
				return defValue;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defValue;
		}
	}

	public static String[] json2StrArray(JSONObject propertys, String propertyName) {

		return json2StrArray(propertys, propertyName, null);
	}

	public static String[] json2StrArray(JSONObject propertys, String propertyName, String[] defValue) {

		try {
			if (propertys.containsKey(propertyName)) {
				return emptyStrArray2Null(propertys.get(propertyName).toString().split(","));
			} else {
				return defValue;
			}

		} catch (Exception ex) {
			return defValue;
		}
	}

	public static int json2Int(JSONObject propertys, String propertyName) {
		return json2Int(propertys, propertyName, 0);
	}

	public static int json2Int(JSONObject propertys, String propertyName, int defaultValue) {
		try {
			if (propertys.containsKey(propertyName)) {
				return propertys.getInt(propertyName);
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultValue;
		}
	}

	public static boolean json2Boolean(JSONObject propertys, String propertyName) {
		return json2Boolean(propertys, propertyName, false);
	}

	public static boolean json2Boolean(JSONObject propertys, String propertyName, boolean defaultValue) {
		try {
			if (propertys.containsKey(propertyName)) {

				return propertys.getBoolean(propertyName);

			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultValue;
		}

	}

	/**
	 * 将空字符串转成null
	 * 
	 * @param oriString
	 * @return
	 */
	public static String emptyStr2Null(String oriString) {

		if (oriString != null && oriString.trim().equals("")) {
			return null;
		}
		return oriString;
	}

	/**
	 * 将空字符串转成null
	 * 
	 * @param oriString
	 * @return
	 */
	public static String Null2EmptyStr(Object value) {

		if (value == null)
			return "";
		return value.toString();
	}

	public static String[] emptyStrArray2Null(String[] oStringArray) {

		if (oStringArray == null || oStringArray.length == 0
				|| (oStringArray.length == 1 && oStringArray[0].trim().equals(""))) {

			return null;
		}
		for (int i = 0; i < oStringArray.length; i++) {
			if (oStringArray[i].trim().equals("")) {

				oStringArray[i] = null;
			}
		}
		return oStringArray;

	}

	// 得到目录中文件名
	public static List<String> getAllFilePathByDir(String dirpath) {
		List<String> result = new ArrayList<String>();
		File dir = new File(dirpath);
		if (dir == null && !dir.isDirectory()) {
			return null;
		} else {
			File[] files = dir.listFiles();
			if (files == null)
				return null;
			List<File> fileList = new ArrayList<File>();
			for (File f : files) {
				fileList.add(f);
			}
			Collections.sort(fileList);
			for (File f : fileList) {
				result.add(f.getName());
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Date date = Utils.parseDate("1988.01.01");
		System.out.println(date);
	}

	// Object转换成control中返回jason的string
	public static String ReturnJasonArray(Object obj) {
		JSONArray jsondata = JSONArray.fromObject(obj);
		JSONObject result = new JSONObject();
		result.put("data", jsondata);
		return result.toString();
	}

	public static String ReturnSuccess(Object success) {
		JSONObject result = new JSONObject();
		result.put("success", success);
		return result.toString();
	}

	// 导出excel
	public static void expExcelBy(HttpServletRequest request, HttpServletResponse response, String tableName,
			String[] columnName, String[] columnField, JSONArray jsonArray) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建HSSFSheet对象(excel的表单对象)
		HSSFSheet sheet = wb.createSheet(tableName);
		// 创建样式对象（HSSFCellStyle ）
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 创建字体对象
		HSSFFont font = wb.createFont();
		// 设置粗体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 将字体对象赋给单元格样式对象
		cellStyle.setFont(font);

		// 在sheet里创建第一行，参数为行索引
		HSSFRow row1 = sheet.createRow(0);
		HSSFRow row;
		for (int i = 0; i < columnName.length; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(columnName[i]);
		}

		for (int j = 1; j < jsonArray.size() + 1; j++) {
			row = sheet.createRow(j);
			try {
				JSONObject job = jsonArray.getJSONObject(j - 1);
				for (int s = 0; s < columnField.length; s++) {
					String mycell = columnField[s];
					if (job.containsKey(mycell)) {
						row.createCell(s).setCellValue(job.getString(mycell));
					}
				}
			} catch (Exception e) {
				System.out.println("expExcelBy-报错");
			}
		}

		// 输出excel文件
		OutputStream out = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=deal.xls");
		response.setContentType("application/msexcel");
		wb.write(out);
		out.close();
	}

	// 生成随机数
	public static String GetNumber(int len) {
		Random ran = new Random();
		String code = "";
		for (int i = 0; i < len; i++) {
			String s = ran.nextInt(10) + "";
			code += s;
		}
		return code;
	}

	/**
	 * 转义特殊字符
	 * 
	 * @param content
	 * @return
	 */
	public static String convertFormatString(String content) {
		if (content != null) {
			content = content.replaceAll("&", "&amp;");
			content = content.replaceAll("<", "&lt;");
			content = content.replaceAll(">", "&gt;");
			content = content.replaceAll("\n", "<w:br/>");
		} else {
			content = "";
		}
		return content;
	}

	// 根据请求获取ip
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String convertNumberToSimpleNumber(int d) {

		// String[] str = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		// String ss[] = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
		// "亿" };
		String ss[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String s = String.valueOf(d);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			String index = String.valueOf(s.charAt(i));
			sb = sb.append(str[Integer.parseInt(index)]);
		}
		String sss = String.valueOf(sb);
		int i = 0;
		for (int j = sss.length(); j > 0; j--) {
			sb = sb.insert(j, ss[i++]);
		}
		return sb.toString();
	}
}

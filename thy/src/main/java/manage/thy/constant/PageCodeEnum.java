package manage.thy.constant;

/**
 * 统一规定返回码
 * @author ASUS
 * 创建时间  2017年12月2日 下午7:26:59
 *
 */
public enum PageCodeEnum {

	ADD_SUCCESS(1000 , "新增成功"),
	ADD_FAIL(1001 , "新增失败"),
	MODIFY_SUCCESS(1100 , "修改成功"),
	MODIFY_FAIL(1101 , "修改失败"),
	REMOVE_SUCCESS(1200 ,"移除成功"),
	REMOVE_FAIL(1201 ,"移除失败"),
	
	
	
	
	
	
	
	;
	
	
	
	private Integer  code ;
	private String msg ;

	public static final String KEY = "pageCode";
	
	PageCodeEnum(Integer code , String msg ) {
		this.code = code ;
		this.msg = msg ;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	
	
	
	
	
}

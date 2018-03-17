package manage.thy.dto.imooc;

import manage.thy.constant.PageCodeEnum;

/**
 * 编码信息 code
 * @author ASUS
 * 创建时间  2017年12月22日 下午9:11:50
 *
 */
public class PageCodeDto {
	
	private Integer code ;
	
	/**
	 * 消息内容
	 */
	private String msg ;


	public PageCodeDto (PageCodeEnum  pageCodeEnum) {
		this.code = pageCodeEnum.getCode();
		this.msg = pageCodeEnum.getMsg() ;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	
}

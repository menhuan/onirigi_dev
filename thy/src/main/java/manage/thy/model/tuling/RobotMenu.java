package manage.thy.model.tuling;

/**
 * 菜谱系列
 * @author dell
 */
public class RobotMenu extends RobotText {

	/**
	 * 菜名
	 */
	private  String name ;
	
	/**
	 * 菜谱信息
	 */
	private  String info ;

	/**
	 * 详情连接
	 */
	private  String detailurl ;

	/**
	 * 图标
	 */
	private  String icon ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}

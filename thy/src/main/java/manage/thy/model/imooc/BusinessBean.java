package manage.thy.model.imooc;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class BusinessBean {
	
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 文件名字
	 */
	private String imgFileName;
	
	/**
	 * 主标题
	 */
	private String title;
	/**
	 * 副标题
	 */
	private String subtitle;
	/**
	 * 价格	是否需要改成 BigDecimal
	 */
	private Double price;
	/**
	 * 距离 单位 米
	 */
	private Integer distance;
	
	/**
	 * 已售数量
	 */
	private Integer number;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 类别
	 */
	private String category;
	
	/**
	 * 评论星星数量
	 */
	private Long starTotalNum;
	
	/**
	 * 评论总次数
	 */
	private Long commentTotalNum;

	private DicBean  cityDic;

	private DicBean categoryDic;
	
	
	public DicBean getCityDic() {
		return cityDic;
	}

	public void setCityDic(DicBean cityDic) {
		this.cityDic = cityDic;
	}

	public DicBean getCategoryDic() {
		return categoryDic;
	}

	public void setCategoryDic(DicBean categoryDic) {
		this.categoryDic = categoryDic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getStarTotalNum() {
		return starTotalNum;
	}

	public void setStarTotalNum(Long starTotalNum) {
		this.starTotalNum = starTotalNum;
	}

	public Long getCommentTotalNum() {
		return commentTotalNum;
	}

	public void setCommentTotalNum(Long commentTotalNum) {
		this.commentTotalNum = commentTotalNum;
	}
	
	
	

}

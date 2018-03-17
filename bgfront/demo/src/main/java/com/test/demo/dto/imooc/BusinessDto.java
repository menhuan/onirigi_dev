package com.test.demo.dto.imooc;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.test.demo.bean.imooc.BusinessBean;

@JsonInclude(Include.NON_NULL)
public class BusinessDto extends BusinessBean {
	
	
	private String  img;
	/**
	 * 文件
	 */
	private MultipartFile  imgFile;
	
	private String keyword ;
	
	private Integer mumber;
	
	private Integer star;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getMumber() {
		return mumber;
	}

	public void setMumber(Integer mumber) {
		this.mumber = mumber;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	
	
}

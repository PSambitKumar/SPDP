package com.spdp.bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author sanghamitra.beura
 *
 */



public class SubDivisionBean {
	private Long subdivionId;
	private String subdivisionName;
	private String notiNo;
	//@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate notiDate;
	private Date notificationDate;
	private Long districtId;
	private Long blockId;
	private String blockName;
	private Date creationDate;
	private List<BlockBean> blockObj;
	private List<String> BlockListForView;
	
	private String notifDate;
	private String cDate;
	
	
	
	
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getNotifDate() {
		return notifDate;
	}
	public void setNotifDate(String notifDate) {
		this.notifDate = notifDate;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public List<String> getBlockListForView() {
		return BlockListForView;
	}
	public void setBlockListForView(List<String> blockListForView) {
		BlockListForView = blockListForView;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	private List<Integer> dist;
	private List<Long> distL;
	private List<String> distList;
	
	private List<Integer> block;
	private List<Long> blockL;
	private List<String> blockList;
	private String status;
	
	private String [] distLists;
	private String [] blockLists;
	private Object[] formData;
	private String districtName;
	
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getSubdivionId() {
		return subdivionId;
	}
	public void setSubdivionId(Long subdivionId) {
		this.subdivionId = subdivionId;
	}
	public String getSubdivisionName() {
		return subdivisionName;
	}
	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}
	
	public LocalDate getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(LocalDate notiDate) {
		this.notiDate = notiDate;
	}
	public List<Integer> getDist() {
		return dist;
	}
	public void setDist(List<Integer> dist) {
		this.dist = dist;
	}
	public List<Long> getDistL() {
		return distL;
	}
	public void setDistL(List<Long> distL) {
		this.distL = distL;
	}
	public List<String> getDistList() {
		return distList;
	}
	public void setDistList(List<String> distList) {
		this.distList = distList;
	}
	public List<Integer> getBlock() {
		return block;
	}
	public void setBlock(List<Integer> block) {
		this.block = block;
	}
	public List<Long> getBlockL() {
		return blockL;
	}
	public void setBlockL(List<Long> blockL) {
		this.blockL = blockL;
	}
	public List<String> getBlockList() {
		return blockList;
	}
	public void setBlockList(List<String> blockList) {
		this.blockList = blockList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getDistLists() {
		return distLists;
	}
	public void setDistLists(String[] distLists) {
		this.distLists = distLists;
	}
	public String[] getBlockLists() {
		return blockLists;
	}
	public void setBlockLists(String[] blockLists) {
		this.blockLists = blockLists;
	}
	public Object[] getFormData() {
		return formData;
	}
	public void setFormData(Object[] formData) {
		this.formData = formData;
	}
	public List<BlockBean> getBlockObj() {
		return blockObj;
	}
	public void setBlockObj(List<BlockBean> blockObj) {
		this.blockObj = blockObj;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	
	
	
}
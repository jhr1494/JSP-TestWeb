package com.testweb.util;

public class PageVO {
	
	private int start;
	private int end;
	private boolean prev, next;
	
	private int pNum = 1;
	private int amount = 5;
	private int total;
	public PageVO(int pNum, int amount, int total) {
		
		this.pNum = pNum;
		this.amount = amount;
		this.total = total;
		
		this.end = (int)Math.ceil(this.pNum / (double)5) * 5;
		this.start = this.end - 5 + 1;
		int realEnd = (int)Math.ceil(this.total / (double)this.amount);
		this.prev = this.start > 1;
		this.next = realEnd > this.end;
		
		
		if(end > realEnd) {
			this.end = realEnd;
		}
		
		//확인
		System.out.println("시작페이지 : " + this.start + ", 끝페이지 : " + this.end);
	}
	
	//getter setter
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
	

}

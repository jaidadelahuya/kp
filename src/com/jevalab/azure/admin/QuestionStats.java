package com.jevalab.azure.admin;

import java.io.Serializable;

public class QuestionStats implements Serializable, Comparable<QuestionStats> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2569949973080360933L;
	private String year, vendor, subject;
	private int noQ;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNoQ() {
		return noQ;
	}
	public void setNoQ(int noQ) {
		this.noQ = noQ;
	}
	@Override
	public String toString() {
		return "QuestionStats [year=" + year + ", vendor=" + vendor
				+ ", subject=" + subject + ", noQ=" + noQ + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionStats other = (QuestionStats) obj;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	@Override
	public int compareTo(QuestionStats o) {
		
		return this.year.compareTo(o.year);
	}
	
	

}

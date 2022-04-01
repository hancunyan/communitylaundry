package com.computer.community_laundry.support.entity.laundry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaundryOrder {
    private Integer id;

    private String ordernum;

    private Integer laundryuserid;

    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reservationtaketime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reservationgivetime;

    private String reservationstatus;

    private String appointmentresults;

    private String remarks;

    private Date createtime;

    private Date updatetime;

    private String requirement;

    private String workingprocedure;

    private String technologicalprocess;

    private String clothespic;

    private String paymentstatus;

    private List<LaundryClothes> clothesList;

    private String username;

    private String password;

    private String address;

    private String mobiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public Integer getLaundryuserid() {
        return laundryuserid;
    }

    public void setLaundryuserid(Integer laundryuserid) {
        this.laundryuserid = laundryuserid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReservationtaketime() {
        return reservationtaketime;
    }

    public void setReservationtaketime(Date reservationtaketime) {
        this.reservationtaketime = reservationtaketime;
    }

    public Date getReservationgivetime() {
        return reservationgivetime;
    }

    public void setReservationgivetime(Date reservationgivetime) {
        this.reservationgivetime = reservationgivetime;
    }

    public String getReservationstatus() {
        return reservationstatus;
    }

    public void setReservationstatus(String reservationstatus) {
        this.reservationstatus = reservationstatus == null ? null : reservationstatus.trim();
    }

    public String getAppointmentresults() {
        return appointmentresults;
    }

    public void setAppointmentresults(String appointmentresults) {
        this.appointmentresults = appointmentresults == null ? null : appointmentresults.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }

    public String getWorkingprocedure() {
        return workingprocedure;
    }

    public void setWorkingprocedure(String workingprocedure) {
        this.workingprocedure = workingprocedure == null ? null : workingprocedure.trim();
    }

    public String getTechnologicalprocess() {
        return technologicalprocess;
    }

    public void setTechnologicalprocess(String technologicalprocess) {
        this.technologicalprocess = technologicalprocess == null ? null : technologicalprocess.trim();
    }

    public String getClothespic() {
        return clothespic;
    }

    public void setClothespic(String clothespic) {
        this.clothespic = clothespic == null ? null : clothespic.trim();
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus == null ? null : paymentstatus.trim();
    }
}
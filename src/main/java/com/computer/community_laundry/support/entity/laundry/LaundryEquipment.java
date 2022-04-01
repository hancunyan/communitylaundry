package com.computer.community_laundry.support.entity.laundry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaundryEquipment {
    private Integer id;

    private String equipmentname;

    private String manufactor;

    private String model;

    private String capacity;

    private String workinglimit;

    private String washingobjec;

    private String characteristic;

    private String status;

    private Date createtime;

    private Date updatetime;

    private Integer clothescount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public void setEquipmentname(String equipmentname) {
        this.equipmentname = equipmentname == null ? null : equipmentname.trim();
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor == null ? null : manufactor.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    public String getWorkinglimit() {
        return workinglimit;
    }

    public void setWorkinglimit(String workinglimit) {
        this.workinglimit = workinglimit == null ? null : workinglimit.trim();
    }

    public String getWashingobjec() {
        return washingobjec;
    }

    public void setWashingobjec(String washingobjec) {
        this.washingobjec = washingobjec == null ? null : washingobjec.trim();
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic == null ? null : characteristic.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
}
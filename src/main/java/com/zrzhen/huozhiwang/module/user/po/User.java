package com.zrzhen.huozhiwang.module.user.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 用户
 * 
 * @author chenanlian
 * @email a@zrzhen.com
 * @date 2020/06/10
 */
 
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

    //主键
    private Long id;

    //手机，加唯一索引，可用作登陆
    private String phone;
    //用户名，加唯一索引，可用作登陆
    private String userName;
    //密码
    private String password;
    //密码加盐
    private String salt;
    //状态
    private Integer status;
    //真实姓名
    private String real_name;
    //昵称
    private String nick_name;
    //身份证号
    private Integer id_no;
    //性别
    private Integer sex;
    //用户头像
    private String head_img;
    //国家
    private String country;
    //省份
    private String province;
    //市级
    private String city;
    //县
    private String county;
    //详细地址
    private String detail;
    //版本
    @JsonIgnore
    private Integer version;
    //创建者
    @JsonIgnore
    private Long creator;
    //更新者
    @JsonIgnore
    private Long updator;
    //创建时间
    @JsonIgnore
    private Date createTime;
    //更新时间
    @JsonIgnore
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Integer getId_no() {
        return id_no;
    }

    public void setId_no(Integer id_no) {
        this.id_no = id_no;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

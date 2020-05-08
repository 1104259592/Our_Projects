package com.hq.app.entities;

import java.util.Date;

/**
 * @Class: ThingsEntity
 * @Description: 物品实体类
 * @author: zhangqi
 * @Date: 2020/4/20
 */
public class ThingsEntity {
    private long id;//物品id
    private String title;//物品标题
    private int[] imageIds;//物品图片id数组
    private String eventContent;//物品描述内容
    private double beforePrice;//物品原价格
    private double afterPrice;//活动物品元折价格
    private int num;//物品数量
    private String barcode;//二维码
    private long cid;//所属类目
    private int status;//物品状态 1正常，2下架，3删除，4，暂停5，准备
    private Date created;//发布时间
    private Date update;//更新时间
//    private String position;//物品位置
//    private String belongUser;//所属人
//    private String likeStar;//喜欢度
//    private String appraise;//评价数

}

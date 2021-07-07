package com.live.zbproject.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 庄科炜
 * @className ToubangInfo
 * @description 头帮平台的主播信息
 * @create 2021/7/7 11:18
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToubangInfo {
    private Long id;
    private Integer rid;
    private Date createTime;
    private Date updateTime;
    private Integer roomId;
    private Long anchorId;
    private Integer gameId;
    private Integer fans;
    private Integer fansChange;
    private Integer applyCard;
    private Integer applyCardChange;
    private Integer giftAllWorth;
    private Integer giftAllWorthCompare;
    private Integer giftWorth;
    private Integer giftWorthCompare;
    private Integer avgGiftUserNum;
    private Integer avgGiftUserNumCompare;
    private Integer avgUserGiftWorth;
    private Integer avgHourGiftWorth;
    private Integer avgGiftWorth;
    private Integer offLineGiftWorth;
    private Integer richGiftWorth;
    private Integer avgGiftWorthConver;
    private Integer avgRichNum;
    private Integer msgNum;
    private Integer msgNumCompare;
    private Integer avgMsgUserNum;
    private Integer avgMsgUserNumCompare;
    private Integer msgExceptNum;
    private Integer avgMsgExceptUserNum;
    private Integer avgUserMsgNum;
    private Integer avgHourMsgNum;
    private Integer avgMsgNum;
    private Integer offLineMsgNum;
    private Integer avgInteractNum;
    private Integer avgInteractNumCompare;
    private Integer avgOnline;
    private Integer avgOnlineCompare;
    private Integer maxOnline;
    private Integer avgNoble;
    private Integer avgNobleCompare;
    private Integer maxNoble;
    private Integer liveTime;
    private Integer liveTimeCompare;
    private Integer playNum;
    private Integer liveDay;
    private Integer goldAbility;
    private Integer potential;
    private Integer charmIndex;
    private Integer audVis;
    private Integer richStrength;
    private Integer score;
    private String date;
    private Integer avgInteractApplyCard;
}

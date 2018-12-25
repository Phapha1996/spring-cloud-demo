package org.fage.springbootsocketio.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Caizhf
 * @version 1.0
 * @date 下午4:43 2018/8/13
 * @description
 **/
@Data
public class TransInfoEntity{
    private BigDecimal dataId;
    private Integer transType;
    private Integer state;
    private Integer assetType;
    private String transId;
    private String amount;
    private Integer transState;
    private Date transTime;
    private String srcAccount;
    private String dstAccount;
    private String transHash;
}

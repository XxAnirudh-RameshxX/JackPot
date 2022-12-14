package com.fidelity.jackpot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistoryDto {
    private Long tradeHistoryId;

    private String fundName;

    private BigDecimal quantity;

    private BigDecimal price;

    private Date transactionDate;

    private Long accountTypeId;

    private Long assetClassId;

    private Long userId;

}

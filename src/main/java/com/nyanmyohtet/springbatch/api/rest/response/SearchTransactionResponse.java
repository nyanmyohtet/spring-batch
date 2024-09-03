package com.nyanmyohtet.springbatch.api.rest.response;

import com.nyanmyohtet.springbatch.persistence.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTransactionResponse {
    List<Transaction> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

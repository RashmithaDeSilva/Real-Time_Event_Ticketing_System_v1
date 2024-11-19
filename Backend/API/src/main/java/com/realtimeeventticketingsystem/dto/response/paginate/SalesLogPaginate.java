package com.realtimeeventticketingsystem.dto.response.paginate;

import com.realtimeeventticketingsystem.dto.response.ResponseSalesLogDto;
import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalesLogPaginate {
    private long count;
    private List<ResponseSalesLogDto> data_list;
}

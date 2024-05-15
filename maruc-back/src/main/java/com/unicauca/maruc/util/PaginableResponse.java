package com.unicauca.maruc.util;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PaginableResponse<T> {

  private int pageSize;
  private int total;
  private List<T> result;
}

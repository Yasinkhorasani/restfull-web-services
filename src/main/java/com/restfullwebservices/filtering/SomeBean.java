package com.restfullwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//STATIC filtering for a Bean across different REST API
//class ->@JsonIgnoreProperties/ field -> @JsonIgnore
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties("field2")
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
   // @JsonIgnore
    private String field2;

    private String field3;
}

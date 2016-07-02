package com.elangzhi.ssm.model.base;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import java.io.Serializable;
import java.util.List;


/**
 * 基本Model
 * @author GaoXiang
 * @version 1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BaseModel implements Serializable {

}

package com.virjar.superappium.xpath.function.axis;

import com.virjar.superappium.ViewImage;
import com.virjar.superappium.xpath.function.NameAware;

import java.util.List;

public interface AxisFunction extends NameAware {
    //TODO 是否允许轴函数存在参数
    List<ViewImage> call(ViewImage viewImage, List<String> args);
}

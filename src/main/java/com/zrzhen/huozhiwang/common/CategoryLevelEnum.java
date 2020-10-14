package com.zrzhen.huozhiwang.common;

public enum CategoryLevelEnum {
    /*枚举*/
    DEFAULT(0,"error"),
    LEVEL_ONE(1,"一级分类"),
    LEVEL_TWO(2,"二级分类"),
    LEVEL_THREE(3,"三级分类");

    /*两个变量*/
    private int level;
    private String name;

    /*定义枚举类的类型，需要两个变量*/
    CategoryLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }
//定义一个外部直接访问的方法。
    public static CategoryLevelEnum getMallOrderStatusEnumByLevel(int level) {
        for (CategoryLevelEnum mallCategoryLevelEnum : CategoryLevelEnum.values()) {
            if (mallCategoryLevelEnum.getLevel() == level) {
                return mallCategoryLevelEnum;
            }
        }
        return DEFAULT;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

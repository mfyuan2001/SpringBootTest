package cn.com.springday3jdbc.pojo;

/**
 * @author yuanmengfan
 * @date 2022/2/28 11:23 下午
 * @description
 */
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");

    public static SexEnum getEnumById(int id){
        for (SexEnum value : SexEnum.values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package enums;

import java.io.Serializable;

public enum FontType implements Serializable {

    REGULAR (0),
    MEDIUM  (1) ,
    BOLD  (2) ;

    private int code;

    FontType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


    public static FontType getType(Integer numeral){
        for(FontType ds : values()){
            if(ds.code == numeral){
                return ds;
            }
        }
        return null;
    }

    public static Integer getTypeInt(FontType status){

        if(status != null)
            return status.code;

        return  null;
    }

}

package com.niniconi.plugin.api.res;

/**
 * 用于插件在注册自己所控制的资源类型对应表中的字段类型及属性
 */
public class FieldEntity {
    private String fieldLabel;
    private Type type;
    private int size;
    private boolean allowNull;
    private String defaultVar;
    public enum Type{
        VARCHAR,
        INT,
        BIGINT,
        DATETIME
    }

    public FieldEntity(String fieldLabel, Type type, int size, boolean allowNull, String defaultVar) {
        this.fieldLabel = fieldLabel;
        this.type = type;
        this.size = size;
        this.allowNull = allowNull;
        this.defaultVar = defaultVar;
    }

    public FieldEntity(String fieldLabel){
       this(fieldLabel,Type.VARCHAR,64,true,null);
    }

    public FieldEntity(String fieldLabel,Type type,int size){
        this(fieldLabel,type,size,true,null);
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public String getDefaultVar() {
        return defaultVar;
    }

    public void setDefaultVar(String defaultVar) {
        this.defaultVar = defaultVar;
    }

    /**
     * 这个用于生成部分SQL语句，用于表示建表时的字段及其属性
     * @return 生成的SQL语句
     */
    @Override
    public String toString() {
        String field  = '`'+fieldLabel+'`';
        switch (type){
            case INT:
            case BIGINT:
            case DATETIME:
                field+=' '+type.toString();
                break;
            case VARCHAR:
                field+=' '+type.toString()+'('+size+')';
        }
        if (!allowNull)field+=" NOT NULL";
        if (defaultVar!=null)field+=" DEFAULT "+defaultVar;
        return field;
    }
}

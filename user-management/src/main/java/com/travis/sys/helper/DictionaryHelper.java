package com.travis.sys.helper;

import com.travis.sys.domain.SysDictionary;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class DictionaryHelper {
    /**
     * @param tableName  String
     *                   表名称
     * @param columnName String
     *                   列名称
     * @return list List
     */
    public static List getDictionaryList(String tableName, String columnName) {
        SysDictionary sysDictionary = new SysDictionary();
        if (null != tableName)
            sysDictionary.setTableName(tableName);
        if (null != columnName)
            sysDictionary.setColumnName(columnName);
        return sysDictionary.selectEqual();
    }


    public static String getContent(String tableName, String columnName,
                                    String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }
        SysDictionary sysDictionary = getDictionaryDomainByValue(
                tableName, columnName, value);
        if (sysDictionary != null && sysDictionary.getContent() != null) {
            return sysDictionary.getContent();
        }
        return "";
    }

    public static String getValue(String tableName, String columnName,
                                  String content) {
        if (content == null || content.trim().equals("")) {
            return "";
        }
        SysDictionary sysDictionary = getDictionaryDomainByContent(
                tableName, columnName, content);
        if (sysDictionary != null && sysDictionary.getValue() != null) {
            return sysDictionary.getValue();
        }
        return "";
    }

    public static String getValue(String tableName, String columnName) {
        List result = getDictionaryList(tableName, columnName);
        if (result != null && result.size() > 0) {
            SysDictionary sysDictionary = (SysDictionary) result.get(0);
            return sysDictionary.getValue();
        } else {
            return null;
        }
    }

    public static String getOptions(String tableName, String columnName) {
        return getOptions(tableName, columnName, null);
    }

    public static String getOptions(String tableName, String columnName,
                                    boolean hasNullOption) {
        return getOptions(tableName, columnName, null, hasNullOption);
    }

    public static String getOptions(String tableName, String columnName,
                                    String value) {
        return getOptions(tableName, columnName, value, false);
    }

    public static String getOptions(String tableName, String columnName,
                                    String value, boolean hasNullOption) {
        List dictionaryList = getDictionaryList(tableName, columnName);
        if (dictionaryList != null && dictionaryList.size() > 0) {
            return getOptions(dictionaryList, value, hasNullOption);
        }
        return "";
    }

    /**
     * 显示下拉框的内容
     *
     * @param checkValue    ,key表示显示的名称，value表示要选择的值
     * @param checkValue    ,指定的下拉框的值
     * @param hasNullOption ,是否需要显示没有值的一个框
     * @return
     */
    private static String getOptions(List dictionaryList, String checkValue,
                                     boolean hasNullOption) {
        if (dictionaryList == null || dictionaryList.size() == 0) {
            return "";
        }
        String htmlOptions = "";
        if (hasNullOption) {
            htmlOptions = "<option value=\'\'>----</option>";
        }

        for (int i = 0; i < dictionaryList.size(); i++) {
            SysDictionary sysDictionary = (SysDictionary) dictionaryList.get(i);
            String name = sysDictionary.getContent();
            String value = sysDictionary.getValue();
            if ("NULL".equals(value)) {
                continue;
            }
            if (checkValue != null && checkValue.equals(value)) {
                htmlOptions += "<option value=\'" + value + "\'"
                        + " selected=\'selected\'>";
            } else {
                htmlOptions += "<option value=\'" + value + "\'>";
            }
            htmlOptions += name + "</option>";
        }
        return htmlOptions;
    }

    public static String getRadios(String tableName, String columnName,
                                   String radioName) {
        return getRadios(tableName, columnName, radioName, "");
    }

    public static String getRadios(String tableName, String columnName,
                                   String radioName, String value) {
        return getRadios(tableName, columnName, radioName, value, false,
                false);
    }

    public static String getRadios(String tableName, String columnName,
                                   String value, boolean isDisabled) {
        return getRadios(tableName, columnName, "", value, false, isDisabled);
    }

    public static String getRadios(String tableName, String columnName,
                                   String radioName, String value, boolean isAutoSelected) {
        return getRadios(tableName, columnName, radioName, value,
                isAutoSelected, false);
    }

    public static String getRadios(String tableName, String columnName,
                                   String radioName, String value, boolean isAutoSelected,
                                   boolean isDisabled) {
        List dictionaryList = getDictionaryList(tableName, columnName);
        if (dictionaryList != null && dictionaryList.size() > 0) {
            return getRadios(dictionaryList, radioName, value, isAutoSelected,
                    isDisabled);
        }
        return "";
    }

    /**
     * 显示复选框的内容
     *
     * @param tableName         ,表名
     * @param columnName        ,列名
     * @param checkboxName      ,复选框名称
     * @param checkedDomainList ,选中值对象列表
     * @param propertyName      ,选中值存在哪个属性中
     * @return
     */
    public static String getCheckboxs(String tableName, String columnName,
                                      String checkboxName, List checkedDomainList, String propertyName) {

        String prefix = checkboxName;
        String postfix = "";

        if (checkboxName.indexOf("[]") != -1 && checkboxName.indexOf(".") != -1) {
            prefix = checkboxName.substring(0, checkboxName.indexOf("["));
            postfix = checkboxName.substring(checkboxName.indexOf("."));

        }

        List dictionaryList = getDictionaryList(tableName, columnName);
        if (dictionaryList == null || dictionaryList.size() == 0) {
            return "";
        }
        String htmlCheckboxs = "";
        boolean flag = false;
        if (!"null".equals(checkedDomainList) && null != checkedDomainList && checkedDomainList.size() > 0 && checkedDomainList instanceof List) {
            flag = true;
        }
        int count = 0;
        for (int i = 0; i < dictionaryList.size(); i++) {
            SysDictionary sysDictionary = (SysDictionary) dictionaryList
                    .get(i);
            String name = sysDictionary.getContent();
            String value = sysDictionary.getValue();
            if ("NULL".equals(value)) {
                continue;
            }
            String checked = "";
            if (flag) {
                for (int j = 0; j < checkedDomainList.size(); j++) {
                    Object o = checkedDomainList.get(j);
                    String propertyValue = "";
                    if (!"".equals(propertyName)) {
                        try {
                            propertyValue = BeanUtils.getProperty(o, propertyName);
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (value.equals(propertyValue)) {
                        checked = "checked='checked'";
                        break;
                    }
                }
            }
            htmlCheckboxs += "<input type='checkbox' name=" + prefix
                    + "[" + count + "]" + postfix + "  value='" + value + "'" + checked + ">";
            count++;
            htmlCheckboxs += name + "&nbsp;&nbsp;";
            if ((i + 1) % 5 == 0) {
                htmlCheckboxs += "<br>";
            }
        }

        return htmlCheckboxs;
    }

    public static String getCheckboxss(String tableName, String columnName,
                                       String checkboxName, List<String> checkedList) {

        String prefix = checkboxName;
        String postfix = "";

        if (checkboxName.indexOf("[]") != -1 && checkboxName.indexOf(".") != -1) {
            prefix = checkboxName.substring(0, checkboxName.indexOf("["));
            postfix = checkboxName.substring(checkboxName.indexOf("."));

        }

        List dictionaryList = getDictionaryList(tableName, columnName);
        if (dictionaryList == null || dictionaryList.size() == 0) {
            return "";
        }
        String htmlCheckboxs = "";
        boolean flag = false;
        if (!"null".equals(checkedList) && null != checkedList && checkedList.size() > 0 && checkedList instanceof List) {
            flag = true;
        }
        int count = 0;
        for (int i = 0; i < dictionaryList.size(); i++) {
            SysDictionary sysDictionary = (SysDictionary) dictionaryList.get(i);
            String name = sysDictionary.getContent();
            String value = sysDictionary.getValue();
            if ("NULL".equals(value)) {
                continue;
            }
            String checked = "";
            if (flag) {
                for (int j = 0; j < checkedList.size(); j++) {
                    String o = checkedList.get(j);
                    if (value.equals(o)) {
                        checked = "checked='checked'";
                        break;
                    }
                }
            }
            htmlCheckboxs += "<input type='checkbox' name=" + prefix
                    + "[" + count + "]" + postfix + "  value='" + value + "'" + checked + ">";
            count++;
            htmlCheckboxs += name + "&nbsp;&nbsp;";
            if ((i + 1) % 5 == 0) {
                htmlCheckboxs += "<br>";
            }
        }

        return htmlCheckboxs;
    }

    /**
     * 显示单选框的内容
     *
     * @param radioName  表示显示的名称，value表示要选择的值
     * @param checkValue ,指定的单选框的值
     * @return
     */
    private static String getRadios(List dictionaryList, String radioName,
                                    String checkValue, boolean isAutoSelected, boolean isDisabled) {
        if (dictionaryList == null || dictionaryList.size() == 0) {
            return "";
        }
        String htmlRadios = "";
        String htmlSelected = "";

        int index = 0;
        boolean isChecked = false;
        for (int i = 0; i < dictionaryList.size(); i++) {
            SysDictionary sysDictionary = (SysDictionary) dictionaryList.get(i);
            String name = sysDictionary.getContent();
            String value = sysDictionary.getValue();
            if ("NULL".equals(value)) {
                continue;
            }
            if (checkValue != null && checkValue.equals(value)) {
                isChecked = true;
                htmlRadios += "<input name='" + radioName
                        + "' type='radio' value='" + value + "' "
                        + (isDisabled ? "disabled='disabled'" : "")
                        + (index == 0 ? "#htmlSelected#" : "")
                        + " checked='checked'>";
            } else {
                htmlRadios += "<input name='" + radioName
                        + "' type='radio' value='" + value + "' "
                        + (isDisabled ? "disabled='disabled'" : "")
                        + (index == 0 ? "#htmlSelected#" : "") + ">";
            }
            index++;
            htmlRadios += name + "&nbsp;&nbsp;";
            if (index % 7 == 0) {
                htmlRadios += "<br>";
            }
        }

        if (!isChecked && isAutoSelected) {
            htmlSelected = "checked='checked'";
        }
        htmlRadios = htmlRadios.replaceAll("#htmlSelected#", htmlSelected);
        return htmlRadios;
    }

    public static SysDictionary getDictionaryDomainByContent(
            String tableName, String columnName, String content) {
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setTableName(tableName);
        sysDictionary.setColumnName(columnName);
        sysDictionary.setContent(content);
        try {
            return (SysDictionary) sysDictionary.loadEqual();
        } catch (Exception e) {
            return null;
        }
    }

    public static SysDictionary getDictionaryDomainByValue(String tableName, String columnName, String value) {
        SysDictionary sysDictionary = new SysDictionary();
        sysDictionary.setTableName(tableName);
        sysDictionary.setColumnName(columnName);
        sysDictionary.setValue(value);
        try {
            sysDictionary.loadEqual();
        } catch (Exception e) {
            return null;
        }
        return sysDictionary;
    }

    public static String getOptionsByList(List dataList, String keyName, String displayName, String selectedValue) throws Exception {
        String htmlStr = "";
        String keyMethodName = "get" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
        String displayMethodName = "get" + displayName.substring(0, 1).toUpperCase() + displayName.substring(1);

        for (int i = 0; i < dataList.size(); i++) {

            String keyValue = "";
            String displayValue = "";
            Object object = dataList.get(i);
            Class domain = object.getClass();
            Method keyMethod = domain.getDeclaredMethod(keyMethodName, null);
            Method displayMethod = domain.getDeclaredMethod(displayMethodName, null);
            keyValue = (String) keyMethod.invoke(object, null);
            displayValue = (String) displayMethod.invoke(object, null);

            if (!selectedValue.equals("") && selectedValue.equals(keyValue)) {
                htmlStr += "<option value=" + keyValue + " selected>" + displayValue + "</option>";
            } else {
                htmlStr += "<option value=" + keyValue + ">" + displayValue + "</option>";
            }
        }

        return htmlStr;
    }
}

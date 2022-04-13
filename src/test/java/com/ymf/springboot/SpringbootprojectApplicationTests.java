package com.ymf.springboot;

import cn.hutool.core.thread.ThreadUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@SpringBootTest
class SpringbootprojectApplicationTests {

    @Test
    void contextLoads() {
//        String json = "{\"indepOrganize_Name\":\"党委办公室、校长办公室\",\"organize_Codes\":\"32601121\\n14450\\n32601\",\"organizeName\":\"维护稳定工作办公室、信访办公室\",\"indepOrganize_Code\":\"32601\",\"positions\":\"32601121:GDWFGWHJZFZR:2002010100\\n14450:a7d9df1cf23444e6b931e667c41eadbe:2002010100\\n14450:SJZT-YXGLY:2002010100\\n32601:3:2002010100\",\"indepOrganize_Names\":\"党委办公室、校长办公室\\n新闻与信息传播学院\",\"userCode\":\"2002010100\",\"formalPositions\":\"32601121:GDWFGWHJZFZR:2002010100\\n14450:a7d9df1cf23444e6b931e667c41eadbe:2002010100\\n14450:SJZT-YXGLY:2002010100\\n32601:3:2002010100\",\"organize_Names\":\"维护稳定工作办公室、信访办公室\\n新闻与信息传播学院\\n党委办公室、校长办公室\",\"indepOrganize_Codes\":\"32601\\n14450\",\"userCodes\":\"2002010100\",\"phone\":\"15926324056\",\"organizeCode\":\"32601121\",\"email\":\"myz@hust.edu.cn\",\"organizeFiltered_Codes\":\"32601121\\n32601\",\"organizeFiltered_Names\":\"维护稳定工作办公室、信访办公室\\n党委办公室、校长办公室\",\"indepOrganizeFiltered_Codes\":\"32601\",\"indepOrganizeFiltered_Names\":\"党委办公室、校长办公室\",\"userCodesFiltered\":\"2002010100\",\"_parent\":\"32601\"}";
////        json = "32601121";
//        String result = "";
//        try {
//            JSONObject jsonObject = JSONObject.fromObject(json);
//            result = jsonObject.getString("userCode");
//        }catch (Exception e){
//            result = json;
//        }
//        System.out.println(result);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(new Date().getTime() + 47100000);
        System.out.println(date);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10000; j++) {
                String tempI =i+"";
                String  tempJ =j+"";
                ThreadUtil.execAsync(() -> send(tempI,tempJ) );
            }
        }
    }

    public static void send(String... a){
        System.out.println(Arrays.asList(a));
    }

    /**
     * 读取Excel测试程序入口
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        // 待读取文件的路径
//        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream( new File("/Users/yuanmengfan/Desktop/行政办公用房全校台账汇总表20210429（最新版）.xls")));
//        HSSFSheet sheet = workbook.getSheetAt(0);
//        HSSFRow row =sheet.getRow(6);
//        HSSFCell cell= row.getCell(0);
//        CellAddress address = cell.getAddress();
//        System.out.println(address);
//    }
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
            throws FileNotFoundException, IOException {
        List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // 设置编码格式
//                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    Date date = cell.getDateCellValue();
                                    if (date != null) {
                                        value = new SimpleDateFormat("yyyy-MM-dd")
                                                .format(date);
                                    } else {
                                        value = "";
                                    }
                                } else {
                                    value = new DecimalFormat("0").format(cell
                                            .getNumericCellValue());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                // 导入时如果为公式生成的数据则无值
                                if (!cell.getStringCellValue().equals("")) {
                                    value = cell.getStringCellValue();
                                } else {
                                    value = cell.getNumericCellValue() + "";
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                value = (cell.getBooleanCellValue() == true ? "Y"
                                        : "N");
                                break;
                            default:
                                value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = rightTrim(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }
}

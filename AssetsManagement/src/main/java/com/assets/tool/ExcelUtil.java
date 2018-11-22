package com.assets.tool;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.assets.model.Device;
import com.assets.model.ExcelBean;

public class ExcelUtil {
    private final static String excel2003L =".xls";    //2003- �汾��excel
    private final static String excel2007U =".xlsx";   //2007+ �汾��excel
    /**
     * Excel����
     */
    public static  List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;
        //����Excel������
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("����Excel������Ϊ�գ�");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //����Excel�����е�sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}
            //������ǰsheet�е�������
            //����ͷ��������ҪС�ڵ������һ����,����Ҳ�����ڳ�ʼֵ����ͷ���������Ա�����ͷ��
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                //��ȡһ��
                row = sheet.getRow(j);
                //ȥ�����кͱ�ͷ
                if(row==null||row.getFirstCellNum()==j){continue;}
                //�������е���
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }
    /**
     * �����������ļ���׺������Ӧ�ϴ��ļ��İ汾
     */
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        System.err.println(fileType);
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("�������ļ���ʽ����");
        }
        return wb;
    }
    /**
     * �������Ա������ֵ���и�ʽ��
     */
    public static  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //��ʽ���ַ����͵�����
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //���ڸ�ʽ��
        DecimalFormat df2 = new DecimalFormat("0.00");  //��ʽ������
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
    /**
     * ����Excel�����
     * ����Excel��ʼ
     * @param sheetName ����������
     * @param clazz  ����Դmodel����
     * @param objs   excel�������Լ���Ӧmodel�ֶ���
     * @param map  �����������Լ�cell������ʽ
     */
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map, String sheetName) throws 
IllegalArgumentException,IllegalAccessException,InvocationTargetException,
ClassNotFoundException, IntrospectionException, ParseException {
        // �����µ�Excel������
        XSSFWorkbook workbook = new XSSFWorkbook();
        // ��Excel�������н�һ����������Ϊȱʡֵ, Ҳ����ָ��Sheet����
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // ����Ϊexcel��������ʽ�Լ�excel�ı��������ݵĴ����������������;
        createFont(workbook); //������ʽ
        createTableHeader(sheet, map); //�������⣨ͷ��
        createTableRows(sheet, map, objs, clazz); //��������
        return workbook;
    }
    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;
    public static void createFont(XSSFWorkbook workbook) {
        // ��ͷ
        fontStyle = workbook.createCellStyle();
        XSSFFont font1 = workbook.createFont();
        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font1.setFontName("����");
        font1.setFontHeightInPoints((short) 14);// ���������С
        fontStyle.setFont(font1);
        fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // �±߿�
        fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// ��߿�
        fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// �ϱ߿�
        fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// �ұ߿�
        fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // ����
        // ����
        fontStyle2=workbook.createCellStyle();
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("����");
        font2.setFontHeightInPoints((short) 10);// ���������С
        fontStyle2.setFont(font2);
        fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // �±߿�
        fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// ��߿�
        fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// �ϱ߿�
        fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// �ұ߿�
        fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // ����
    }
    /**
     * ����ExcelMapping ������ͷ��������ͷ��
     *
     * @param sheet ������
     * @param map ÿ��ÿ����Ԫ���Ӧ����ͷ��Ϣ
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        int startIndex=0;//cell��ʼλ��
        int endIndex=0;//cell��ֹλ��
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                //�ϲ���Ԫ��
                if(excels.get(x).getCols()>1){
                    if(x==0){
                        endIndex+=excels.get(x).getCols()-1;
                        CellRangeAddress range=new CellRangeAddress(0,0,startIndex,endIndex);
                        sheet.addMergedRegion(range);
                        startIndex+=excels.get(x).getCols();
                    }else{
                        endIndex+=excels.get(x).getCols();
                        CellRangeAddress range=new CellRangeAddress(0,0,startIndex,endIndex);
                        sheet.addMergedRegion(range);
                        startIndex+=excels.get(x).getCols();
                    }
                    XSSFCell cell = row.createCell(startIndex-excels.get(x).getCols());
                    cell.setCellValue(excels.get(x).getHeadTextName());// ��������
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// ���ø�ʽ
                    }
                    cell.setCellStyle(fontStyle);
                }else{
                    XSSFCell cell = row.createCell(x);
                    cell.setCellValue(excels.get(x).getHeadTextName());// ��������
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle(excels.get(x).getCellStyle());// ���ø�ʽ
                    }
                    cell.setCellStyle(fontStyle);
                }
            }
        }
    }
    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException,
            ClassNotFoundException, ParseException {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = (ExcelBean) ems.get(i);
                // ���get����
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // ������������ͽ���ת��
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        value = DateUtils.formatDate((Date)rtn,"yyyy-MM-dd");
                    } else if(rtn instanceof BigDecimal){
                        NumberFormat nf = new DecimalFormat("#,##0.00");
                        value=nf.format((BigDecimal)rtn).toString();
                    } else if((rtn instanceof Integer) && (Integer.valueOf(rtn.toString())<0 )){
                        value="--";
                    }else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(fontStyle2);
                // �������п�
                int width = value.getBytes().length * 300;
                // ��δ���ã����õ�ǰ
                if (widths.size() <= i) {
                    widths.add(width);
                    continue;
                }
                // ��ԭ���󣬸�������
                if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            rowindex++;
        }
        // �����п�
        for (int index = 0; index < widths.size(); index++) {
            Integer width = widths.get(index);
            width = width < 2500 ? 2500 : width + 300;
            width = width > 10000 ? 10000 + 300 : width + 300;
            sheet.setColumnWidth(index, width);
        }
    }
}
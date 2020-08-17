/*********************************************************************************
 *                                                                               *
 * The MIT License (MIT)                                                         *
 *                                                                               *
 * Copyright (c) 2015-2020 aoju.org and other contributors.                      *
 *                                                                               *
 * Permission is hereby granted, free of charge, to any person obtaining a copy  *
 * of this software and associated documentation files (the "Software"), to deal *
 * in the Software without restriction, including without limitation the rights  *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell     *
 * copies of the Software, and to permit persons to whom the Software is         *
 * furnished to do so, subject to the following conditions:                      *
 *                                                                               *
 * The above copyright notice and this permission notice shall be included in    *
 * all copies or substantial portions of the Software.                           *
 *                                                                               *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR    *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,      *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE   *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER        *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN     *
 * THE SOFTWARE.                                                                 *
 ********************************************************************************/
package org.aoju.bus.office.support.excel;

import org.aoju.bus.core.lang.Assert;
import org.aoju.bus.core.toolkit.IoKit;
import org.aoju.bus.office.support.excel.cell.CellLocation;
import org.apache.poi.ss.usermodel.*;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel基础类,用于抽象ExcelWriter和ExcelReader中共用部分的对象和方法
 *
 * @param <T> 子类类型,用于返回this
 * @author Kimi Liu
 * @version 6.0.6
 * @since JDK 1.8+
 */
public class ExcelBase<T extends ExcelBase<T>> implements Closeable {

    /**
     * 是否被关闭
     */
    protected boolean isClosed;
    /**
     * 工作簿
     */
    protected Workbook workbook;
    /**
     * Excel中对应的Sheet
     */
    protected Sheet sheet;

    /**
     * 构造
     *
     * @param sheet Excel中的sheet
     */
    public ExcelBase(Sheet sheet) {
        Assert.notNull(sheet, "No Sheet provided.");
        this.sheet = sheet;
        this.workbook = sheet.getWorkbook();
    }

    /**
     * 获取Workbook
     *
     * @return Workbook
     */
    public Workbook getWorkbook() {
        return this.workbook;
    }

    /**
     * 返回工作簿表格数
     *
     * @return 工作簿表格数
     */
    public int getSheetCount() {
        return this.workbook.getNumberOfSheets();
    }

    /**
     * 获取此工作簿所有Sheet表
     *
     * @return sheet表列表
     */
    public List<Sheet> getSheets() {
        final int totalSheet = getSheetCount();
        final List<Sheet> result = new ArrayList<>(totalSheet);
        for (int i = 0; i < totalSheet; i++) {
            result.add(this.workbook.getSheetAt(i));
        }
        return result;
    }

    /**
     * 获取表名列表
     *
     * @return 表名列表
     */
    public List<String> getSheetNames() {
        final int totalSheet = workbook.getNumberOfSheets();
        List<String> result = new ArrayList<>(totalSheet);
        for (int i = 0; i < totalSheet; i++) {
            result.add(this.workbook.getSheetAt(i).getSheetName());
        }
        return result;
    }

    /**
     * 获取当前Sheet
     *
     * @return {@link Sheet}
     */
    public Sheet getSheet() {
        return this.sheet;
    }

    /**
     * 自定义需要读取或写出的Sheet，如果给定的sheet不存在，创建之
     * 在读取中，此方法用于切换读取的sheet，在写出时，此方法用于新建或者切换sheet
     *
     * @param sheetName sheet名
     * @return this
     */
    public T setSheet(String sheetName) {
        return setSheet(WorksKit.getOrCreateSheet(this.workbook, sheetName));
    }

    /**
     * 自定义需要读取或写出的Sheet，如果给定的sheet不存在，创建之(命名为默认)
     * 在读取中，此方法用于切换读取的sheet，在写出时，此方法用于新建或者切换sheet
     *
     * @param sheetIndex sheet序号，从0开始计数
     * @return this
     */
    public T setSheet(int sheetIndex) {
        return setSheet(WorksKit.getOrCreateSheet(this.workbook, sheetIndex));
    }

    /**
     * 设置自定义Sheet
     *
     * @param sheet 自定义sheet，可以通过{@link WorksKit#getOrCreateSheet(Workbook, String)} 创建
     * @return this
     */
    public T setSheet(Sheet sheet) {
        this.sheet = sheet;
        return (T) this;
    }

    /**
     * 获取指定坐标单元格,单元格不存在时返回<code>null</code>
     *
     * @param x X坐标,从0计数,既列号
     * @param y Y坐标,从0计数,既行号
     * @return {@link Cell}
     */
    public Cell getCell(int x, int y) {
        return getCell(x, y, false);
    }

    /**
     * 获取指定坐标单元格，单元格不存在时返回<code>null</code>
     *
     * @param locationRef 单元格地址标识符，例如A11，B5
     * @return {@link Cell}
     */
    public Cell getCell(String locationRef) {
        final CellLocation cellLocation = ExcelKit.toLocation(locationRef);
        return getCell(cellLocation.getX(), cellLocation.getY());
    }

    /**
     * 获取或创建指定坐标单元格
     *
     * @param x X坐标,从0计数,既列号
     * @param y Y坐标,从0计数,既行号
     * @return {@link Cell}
     */
    public Cell getOrCreateCell(int x, int y) {
        return getCell(x, y, true);
    }

    /**
     * 获取或创建指定坐标单元格
     *
     * @param locationRef 单元格地址标识符，例如A11，B5
     * @return {@link Cell}
     */
    public Cell getOrCreateCell(String locationRef) {
        final CellLocation cellLocation = ExcelKit.toLocation(locationRef);
        return getOrCreateCell(cellLocation.getX(), cellLocation.getY());
    }

    /**
     * 获取指定坐标单元格,如果isCreateIfNotExist为false,则在单元格不存在时返回<code>null</code>
     *
     * @param x                  X坐标,从0计数,既列号
     * @param y                  Y坐标,从0计数,既行号
     * @param isCreateIfNotExist 单元格不存在时是否创建
     * @return {@link Cell}
     */
    public Cell getCell(int x, int y, boolean isCreateIfNotExist) {
        final Row row = isCreateIfNotExist ? RowKit.getOrCreateRow(this.sheet, y) : this.sheet.getRow(y);
        if (null != row) {
            return isCreateIfNotExist ? CellKit.getOrCreateCell(row, x) : row.getCell(x);
        }
        return null;
    }

    /**
     * 获取指定坐标单元格，如果isCreateIfNotExist为false，则在单元格不存在时返回<code>null</code>
     *
     * @param locationRef        单元格地址标识符，例如A11，B5
     * @param isCreateIfNotExist 单元格不存在时是否创建
     * @return {@link Cell}
     */
    public Cell getCell(String locationRef, boolean isCreateIfNotExist) {
        final CellLocation cellLocation = ExcelKit.toLocation(locationRef);
        return getCell(cellLocation.getX(), cellLocation.getY(), isCreateIfNotExist);
    }

    /**
     * 获取或者创建行
     *
     * @param y Y坐标,从0计数,既行号
     * @return {@link Row}
     */
    public Row getOrCreateRow(int y) {
        return RowKit.getOrCreateRow(this.sheet, y);
    }

    /**
     * 为指定单元格获取或者创建样式,返回样式后可以设置样式内容
     *
     * @param x X坐标,从0计数,既列号
     * @param y Y坐标,从0计数,既行号
     * @return {@link CellStyle}
     */
    public CellStyle getOrCreateCellStyle(int x, int y) {
        final Cell cell = getOrCreateCell(x, y);
        CellStyle cellStyle = cell.getCellStyle();
        if (null == cellStyle) {
            cellStyle = this.workbook.createCellStyle();
            cell.setCellStyle(cellStyle);
        }
        return cellStyle;
    }

    /**
     * 获取或创建某一行的样式,返回样式后可以设置样式内容
     *
     * @param y Y坐标,从0计数,既行号
     * @return {@link CellStyle}
     */
    public CellStyle getOrCreateRowStyle(int y) {
        final Row row = getOrCreateRow(y);
        CellStyle rowStyle = row.getRowStyle();
        if (null == rowStyle) {
            rowStyle = this.workbook.createCellStyle();
            row.setRowStyle(rowStyle);
        }
        return rowStyle;
    }

    /**
     * 为指定单元格获取或者创建样式，返回样式后可以设置样式内容
     *
     * @param locationRef 单元格地址标识符，例如A11，B5
     * @return {@link CellStyle}
     */
    public CellStyle getOrCreateCellStyle(String locationRef) {
        final CellLocation cellLocation = ExcelKit.toLocation(locationRef);
        return getOrCreateCellStyle(cellLocation.getX(), cellLocation.getY());
    }

    /**
     * 获取或创建某一行的样式,返回样式后可以设置样式内容
     *
     * @param x X坐标,从0计数,既列号
     * @return {@link CellStyle}
     */
    public CellStyle getOrCreateColumnStyle(int x) {
        CellStyle columnStyle = this.sheet.getColumnStyle(x);
        if (null == columnStyle) {
            columnStyle = this.workbook.createCellStyle();
            this.sheet.setDefaultColumnStyle(x, columnStyle);
        }
        return columnStyle;
    }

    /**
     * 为指定单元格创建样式，返回样式后可以设置样式内容
     *
     * @param x X坐标，从0计数，即列号
     * @param y Y坐标，从0计数，即行号
     * @return {@link CellStyle}
     */
    public CellStyle createCellStyle(int x, int y) {
        final Cell cell = getOrCreateCell(x, y);
        final CellStyle cellStyle = this.workbook.createCellStyle();
        cell.setCellStyle(cellStyle);
        return cellStyle;
    }

    /**
     * 为指定单元格创建样式，返回样式后可以设置样式内容
     *
     * @param locationRef 单元格地址标识符，例如A11，B5
     * @return {@link CellStyle}
     */
    public CellStyle createCellStyle(String locationRef) {
        final CellLocation cellLocation = ExcelKit.toLocation(locationRef);
        return createCellStyle(cellLocation.getX(), cellLocation.getY());
    }

    /**
     * 创建单元格样式
     *
     * @return {@link CellStyle}
     * @see Workbook#createCellStyle()
     */
    public CellStyle createCellStyle() {
        return StyleKit.createCellStyle(this.workbook);
    }

    /**
     * 获取总行数,计算方法为：
     *
     * <pre>
     * 最后一行序号 + 1
     * </pre>
     *
     * @return 行数
     */
    public int getRowCount() {
        return this.sheet.getLastRowNum() + 1;
    }

    /**
     * 获取有记录的行数,计算方法为：
     *
     * <pre>
     * 最后一行序号 - 第一行序号 + 1
     * </pre>
     *
     * @return 行数
     */
    public int getPhysicalRowCount() {
        return this.sheet.getPhysicalNumberOfRows();
    }

    /**
     * 获取第一行总列数,计算方法为：
     *
     * <pre>
     * 最后一列序号 + 1
     * </pre>
     *
     * @return 列数
     */
    public int getColumnCount() {
        return getColumnCount(0);
    }

    /**
     * 获取总列数,计算方法为：
     *
     * <pre>
     * 最后一列序号 + 1
     * </pre>
     *
     * @param rowNum 行号
     * @return 列数
     */
    public int getColumnCount(int rowNum) {
        return this.sheet.getRow(rowNum).getLastCellNum();
    }

    /**
     * 关闭工作簿
     * 如果用户设定了目标文件,先写出目标文件后给关闭工作簿
     */
    @Override
    public void close() {
        IoKit.close(this.workbook);
        this.sheet = null;
        this.workbook = null;
        this.isClosed = true;
    }

}

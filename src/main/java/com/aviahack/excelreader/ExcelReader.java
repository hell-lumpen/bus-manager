package com.aviahack.excelreader;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class ExcelReader {

    String basePath = "src/main/resources/xlsx/";
    public @Nullable Map<String, Integer> ReadPointsTable() {
        Map<String, Integer> map = new TreeMap<String,Integer>();
        try {
            Workbook wb = new XSSFWorkbook(new FileInputStream(basePath + "Distance.xlsx"));
            for (int row = 1; row < 859; row++) {
                int point_id = (int) wb.getSheetAt(0).getRow(row).getCell(0).getNumericCellValue();
                String location_id;
                if (wb.getSheetAt(0).getRow(row).getCell(1).getCellType().equals(CellType.NUMERIC))
                    location_id = String.valueOf(wb.getSheetAt(0).getRow(row)
                            .getCell(1).getNumericCellValue());
                else
                    location_id = wb.getSheetAt(0).getRow(row).getCell(1).getStringCellValue();

                map.put(location_id, point_id);
            }
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
        return map;
    }
    public @Nullable Map<Pair, Integer> ReadRoadsTable() {
        Map<Pair, Integer> map = new HashMap<>();
        try {
            Workbook wb = new XSSFWorkbook(new FileInputStream(basePath + "Distance.xlsx"));
            for (int row = 1; row <= 2022; row++) {
                int point1   = (int) wb.getSheetAt(1).getRow(row).getCell(1).getNumericCellValue();
                int point2   = (int) wb.getSheetAt(1).getRow(row).getCell(2).getNumericCellValue();
                int distance = (int) wb.getSheetAt(1).getRow(row).getCell(3).getNumericCellValue();
                map.put(new Pair(point1, point2), distance);
            }
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }
        return map;
    }
    public @Nullable List<Task> ReadFlightsTable(Map<String, Integer> points) {
        List<Task> list = new ArrayList<>();
        try {
            Workbook wb = new XSSFWorkbook(new FileInputStream(basePath + "Flights.xlsx"));
            for (int row = 1; row < 466; row++) {
                String data = wb.getSheetAt(0).getRow(row).getCell(0).getStringCellValue();
                LocalDateTime time = wb.getSheetAt(0).getRow(row).getCell(5).getLocalDateTimeCellValue();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                LocalDateTime dateTime = convertToLocalDateTimeViaInstant(formatter.parse(data));
                time = time.withYear(dateTime.getYear())
                        .withMonth(dateTime.getMonth().getValue())
                        .withDayOfMonth(dateTime.getDayOfMonth());

                String plane = String.valueOf(wb.getSheetAt(0).getRow(row).getCell(9).getNumericCellValue());
                String gate = wb.getSheetAt(0).getRow(row).getCell(10).getStringCellValue();
                int passengers = (int) wb.getSheetAt(0).getRow(row).getCell(11).getNumericCellValue();

                String type = wb.getSheetAt(0).getRow(row).getCell(1).getStringCellValue();
                if (type.equals("D")) {
                    time = time.minusMinutes(30);
                    list.add(new Task(time, points.get(gate), points.get(plane), passengers));
                }
                list.add(new Task(time,points.get(plane),  points.get(gate), passengers));
            }
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.HiredCandidate;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.repository.HiredCandidateRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateServiceImpl implements HiredCandidateService {

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    HiredCandidate hiredCandidate = new HiredCandidate();

    @Override
    public List getHiredCandidate(String filePath) throws IOException {
        List sheetData = new ArrayList();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                List data = new ArrayList();
                while (cells.hasNext()) {
                    XSSFCell cell = (XSSFCell) cells.next();
                    data.add(cell);
                }
                sheetData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetData;
    }

    @Override
    public void saveCandidateDetails(List sheetData) {
        XSSFCell cell;
        for (int i = 1; i < sheetData.size(); i++) {
            int j = 0;
            List list = (List) sheetData.get(i);
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setId((long) cell.getNumericCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setFirst_name(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setMiddle_name(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setLast_name(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setEmail(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setHired_city(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setDegree(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setHired_date(cell.getDateCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setMobile_number((long) cell.getNumericCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setPermanent_pincode((long) cell.getNumericCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setHired_lab(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setAttitude(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setCommunication_remark(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setKnowledge_remark(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setAggregate_remark(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setStatus(cell.getStringCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setCreator_stamp(cell.getDateCellValue());
            cell = (XSSFCell) list.get(j++);
            hiredCandidate.setCreator_user(cell.getStringCellValue());
            HiredCandidateModel hiredCandidateModel = modelMapper.map(hiredCandidate, HiredCandidateModel.class);
            hiredCandidateRepository.save(hiredCandidateModel);
        }
    }
}

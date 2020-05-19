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
import org.springframework.web.bind.annotation.RequestParam;

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

    @Override
    public void getHiredCandidate(@RequestParam(value = "path") String filePath) throws IOException {
        boolean flag = true;
        List sheetData = new ArrayList();
        HiredCandidate hiredCandidate = new HiredCandidate();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            XSSFCell cell;
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                if (flag == false) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setId((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setFirst_name(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setMiddle_name(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setLast_name(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setEmail(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setHired_city(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setDegree(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setHired_date(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setMobile_number((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setPermanent_pincode((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setHired_lab(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setAttitude(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setCommunication_remark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setKnowledge_remark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setAggregate_remark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setStatus(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setCreator_stamp(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidate.setCreator_user(cell.getStringCellValue());
                        save(hiredCandidate);
                    }
                }
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(HiredCandidate hiredCandidate) {
        HiredCandidateModel hiredCandidateModel = modelMapper.map(hiredCandidate, HiredCandidateModel.class);
        hiredCandidateRepository.save(hiredCandidateModel);
    }

    @Override
    public List getHiredCandidates() {
        return hiredCandidateRepository.findAll();
    }

    @Override
    public HiredCandidateModel findByFirst_name(String name) {
        HiredCandidateModel hiredCandidateModel = hiredCandidateRepository.findByFirst_name(name);
        return hiredCandidateModel;
    }
}

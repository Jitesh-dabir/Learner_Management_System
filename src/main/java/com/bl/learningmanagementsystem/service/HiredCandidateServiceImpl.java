package com.bl.learningmanagementsystem.service;

import com.bl.learningmanagementsystem.dto.HiredCandidateDto;
import com.bl.learningmanagementsystem.exception.LmsAppServiceException;
import com.bl.learningmanagementsystem.model.HiredCandidateModel;
import com.bl.learningmanagementsystem.repository.HiredCandidateRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Service
public class HiredCandidateServiceImpl implements IHiredCandidateService {

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender sender;

    //Method to read excel file and store data to database
    @Override
    public boolean getHiredCandidate(MultipartFile filePath) throws IOException {
        boolean flag = true;
        HiredCandidateDto hiredCandidateDto = new HiredCandidateDto();
        try (InputStream fis = filePath.getInputStream()) {
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator rows = sheet.rowIterator();
            XSSFCell cell;
            //For each row, iterate through all the columns
            while (rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                if (!flag) {
                    while (cells.hasNext()) {
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setId((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setFirstName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setMiddleName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setLastName(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setEmail(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredCity(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setDegree(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredDate(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setMobileNumber((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setPermanentPincode((long) cell.getNumericCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setHiredLab(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setAttitude(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCommunicationRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setKnowledgeRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setAggregateRemark(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setStatus(cell.getStringCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCreatorStamp(cell.getDateCellValue());
                        cell = (XSSFCell) cells.next();
                        hiredCandidateDto.setCreatorUser(cell.getStringCellValue());
                        save(hiredCandidateDto);
                        sentEmail(hiredCandidateDto);
                    }
                }
                flag = false;
            }
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void save(HiredCandidateDto hiredCandidateDto) {
        HiredCandidateModel hiredCandidateModel = modelMapper.map(hiredCandidateDto, HiredCandidateModel.class);
        if (hiredCandidateModel.equals(null))
            throw new LmsAppServiceException(LmsAppServiceException.exceptionType
                    .DATA_NOT_FOUND, "Null Values found");
        hiredCandidateRepository.save(hiredCandidateModel);
    }

    @Override
    public List getHiredCandidates() {
        List<HiredCandidateModel> list = hiredCandidateRepository.findAll();
        if (list.equals(null))
            throw new LmsAppServiceException(LmsAppServiceException.exceptionType.DATA_NOT_FOUND, "Null Values found");
        return list;
    }

    @Override
    public HiredCandidateModel findById(long userId) {
        return hiredCandidateRepository.findById(userId)
                .orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType
                        .INVALID_ID, "User not found with this id"));
    }

    @Override
    public HiredCandidateModel setStatusResponse(String email, String status) {
        return hiredCandidateRepository.findByEmail(email)
                .map(hiredCandidateModel -> {
                    hiredCandidateModel.setStatus(status);
                    return hiredCandidateRepository.save(hiredCandidateModel);
                }).orElseThrow(() -> new LmsAppServiceException(LmsAppServiceException.exceptionType.DATA_NOT_FOUND, "Data not found"));
    }

    //Method to send email
    @Override
    public void sentEmail(HiredCandidateDto hiredCandidateDto) throws MessagingException {
        String recipientAddress = hiredCandidateDto.getEmail();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(recipientAddress);
        String accept = "http://localhost:8084/hiredcandidated/changestatus?email=" + hiredCandidateDto.getEmail() + "" + "&status=Accept";
        String reject = "http://localhost:8084/hiredcandidated/changestatus?email=" + hiredCandidateDto.getEmail() + "" + "&status=Reject";
        helper.setText("Hii " + hiredCandidateDto.getFirstName() + "\n" + "You are selected for BridgeLabz fellowship program," +
                " if You want to join click on below link (ACCEPT) \n" + accept + "\n otherwise (REJECT)\n" + reject);
        helper.setSubject("Invitation to join BridgeLabz Fellowship Program");
        sender.send(message);
    }
}

package com.cardanonft.api.service;

import com.cardanonft.api.constants.AWS_INFO;
import com.cardanonft.api.constants.CommonConstants;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.CustomBadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class FileUploadService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    AwsUtilService awsUtilService;

    /// 파일 업로드 -> S3
    public String uploadImageFile(MultipartFile multipartFile, String serviceName, boolean publicYn) throws Exception {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            String orig_name = multipartFile.getOriginalFilename();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            file = new File(CommonConstants.EXCEL_FILE_TMP + dateFormatter.format(Calendar.getInstance().getTime()));
            file.deleteOnExit();
            byte[] bytes = multipartFile.getBytes();
            stream = new BufferedOutputStream(new FileOutputStream(file));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomBadRequestException(RETURN_CODE.ERROR);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        String keyStr = awsUtilService.uploadFileByDate(AWS_INFO.BUCKET_NAME, serviceName, file, publicYn);
        return AWS_INFO.BUCKET_IMG_BASE_URL+keyStr;
    }
}

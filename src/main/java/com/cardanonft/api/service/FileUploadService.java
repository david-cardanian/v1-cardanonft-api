package com.cardanonft.api.service;

import com.cardanonft.api.constants.AWS_INFO;
import com.cardanonft.api.constants.CommonConstants;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.CustomBadRequestException;
import org.imgscalr.Scalr;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
    /// 파일 업로드 -> S3
    public String uploadImageFileWithThumb(MultipartFile multipartFile, String serviceName, boolean publicYn) throws Exception {
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
        String[] keys = keyStr.split("/");
        File thumbnail = makeThumbnail(file,CommonConstants.EXCEL_FILE_TMP+""+keys[keys.length-1],64,64);
        awsUtilService.uploadFileByKey(AWS_INFO.BUCKET_NAME, keyStr+"_thumb", thumbnail, publicYn);
        thumbnail.deleteOnExit();
        return AWS_INFO.BUCKET_IMG_BASE_URL+keyStr;
    }
    /** * 썸네일을 생성합니다. * 250 x 150 크기의 썸네일을 만듭니다. */
    private File makeThumbnail(File file, String fileName, int width, int height) throws Exception {
        // 저장된 원본파일로부터 BufferedImage 객체를 생성합니다.
        BufferedImage srcImg = ImageIO.read(file);
        // 썸네일의 너비와 높이 입니다. 
        int dw = width, dh = height;
        // 원본 이미지의 너비와 높이 입니다. 
        int ow = srcImg.getWidth();
        int oh = srcImg.getHeight();
        // 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다. 
        int nw = ow; int nh = (ow * dh) / dw;
        // 계산된 높이가 원본보다 높다면 crop이 안되므로
        // 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다. 
        if(nh > oh) { nw = (oh * dw) / dh; nh = oh; }
        // 계산된 크기로 원본이미지를 가운데에서 crop 합니다. 
        BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
        // crop된 이미지로 썸네일을 생성합니다.
        BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
        // 썸네일을 저장합니다. 이미지 이름 앞에 "THUMB_" 를 붙여 표시했습니다.
        String thumbName = fileName+"_thumb";
        File thumbFile = new File(thumbName);
        ImageIO.write(destImg,"png", thumbFile);
        return thumbFile;
    }
}

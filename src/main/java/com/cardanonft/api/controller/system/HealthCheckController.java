package com.cardanonft.api.controller.system;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.entity.MapParcelEntity;
import com.cardanonft.api.repository.MapParcelRepository;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/v1/health/")
public class HealthCheckController {

    @Autowired
    MapParcelRepository mapParcelRepository;

    private static Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @RequestMapping(value = "serverStatus", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "서버 상태 API", response = CardanoNftDefaultResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse checkServer() throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    @ApiOperation(httpMethod = "GET", value = "서버 상태 API", response = CardanoNftDefaultResponse.class)
//    @ResponseBody
//    public CardanoNftDefaultResponse test() throws Exception {
//        try {
////            FileInputStream file = new FileInputStream("/Users/cruxe/Downloads/test1.xlsx");
////            XSSFWorkbook workbook = new XSSFWorkbook(file);
////
//            int rowindex=0;
//            int columnindex=0;
////            //시트 수 (첫번째에만 존재하므로 0을 준다)
////            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
////            XSSFSheet sheet=workbook.getSheetAt(0);
////            //행의 수
////            byte[] blueRGB = {0,-80,-16};
////            byte[] greenRGB = {112,-83,71};
////            byte[] redRGB = {-1,0,0};
////            int rows=sheet.getPhysicalNumberOfRows();
//////            for(int y=32;y>=1;y--){
//////                for(int x=1;x<=256;x++) {
//////                    MapParcelEntity mapParcelEntity = new MapParcelEntity();
//////                    mapParcelEntity.setContinentId("Byron");
//////                    mapParcelEntity.setVillageId("Charles");
//////                    mapParcelEntity.setParcelX2d(x);
//////                    mapParcelEntity.setParcelY2d(1-y);
//////                    mapParcelEntity.setParcelX3d(x);
//////                    mapParcelEntity.setParcelY3d(33-y);
//////                    mapParcelEntity.setLandType(2);
//////                    mapParcelRepository.save(mapParcelEntity);
//////                }
//////            }
////            for(rowindex=0;rowindex<rows;rowindex++){
////                //행을읽는다
////                XSSFRow row=sheet.getRow(rowindex);
////                if(row !=null){
////                    //셀의 수
////                    int cells=row.getPhysicalNumberOfCells();
////                    for(columnindex=0; columnindex<=255; columnindex++){
////                        if(rowindex < 193){
////                            continue;
////                        }
////                        if(columnindex < 122){
////                            continue;
////                        }
////                        //셀값을 읽는다
////                        XSSFCell cell=row.getCell(columnindex);
////                        if(cell != null && cell.getCellStyle() != null && cell.getCellStyle().getFillForegroundColorColor() != null) {
////                            if(cell.getCellStyle().getFillForegroundColorColor().getRGB()[0] == blueRGB[0]
////                            && cell.getCellStyle().getFillForegroundColorColor().getRGB()[1] == blueRGB[1]
////                            && cell.getCellStyle().getFillForegroundColorColor().getRGB()[2] == blueRGB[2] ) {
////                                MapParcelEntity mapParcelEntity = new MapParcelEntity();
////                                mapParcelEntity.setContinentId("Byron");
////                                mapParcelEntity.setVillageId("Charles");
////                                mapParcelEntity.setParcelX2d(columnindex+1);
////                                mapParcelEntity.setParcelY2d(rowindex+1);
////                                mapParcelEntity.setParcelX3d(columnindex+1);
////                                mapParcelEntity.setParcelY3d(rowindex+33);
////                                mapParcelEntity.setLandType(2);
////                                mapParcelRepository.save(mapParcelEntity);
////                            }else if(cell.getCellStyle().getFillForegroundColorColor().getRGB()[0] == redRGB[0]
////                                    && cell.getCellStyle().getFillForegroundColorColor().getRGB()[1] == redRGB[1]
////                                    && cell.getCellStyle().getFillForegroundColorColor().getRGB()[2] == redRGB[2] ) {
////                                MapParcelEntity mapParcelEntity = new MapParcelEntity();
////                                mapParcelEntity.setContinentId("Byron");
////                                mapParcelEntity.setVillageId("Charles");
////                                mapParcelEntity.setParcelX2d(columnindex+1);
////                                mapParcelEntity.setParcelY2d(rowindex+1);
////                                mapParcelEntity.setParcelX3d(columnindex+1);
////                                mapParcelEntity.setParcelY3d(rowindex+33);
////                                mapParcelEntity.setLandType(3);
////                                mapParcelRepository.save(mapParcelEntity);
////                            }else if(cell.getCellStyle().getFillForegroundColorColor().getRGB()[0] == greenRGB[0]
////                                    && cell.getCellStyle().getFillForegroundColorColor().getRGB()[1] == greenRGB[1]
////                                    && cell.getCellStyle().getFillForegroundColorColor().getRGB()[2] == greenRGB[2] ) {
////                                MapParcelEntity mapParcelEntity = new MapParcelEntity();
////                                mapParcelEntity.setContinentId("Byron");
////                                mapParcelEntity.setVillageId("Charles");
////                                mapParcelEntity.setParcelX2d(columnindex+1);
////                                mapParcelEntity.setParcelY2d(rowindex+1);
////                                mapParcelEntity.setParcelX3d(columnindex+1);
////                                mapParcelEntity.setParcelY3d(rowindex+33);
////                                mapParcelEntity.setLandType(1);
////                                mapParcelRepository.save(mapParcelEntity);
////                            }else{
////                                System.out.println(rowindex + "번 행 : " + columnindex + "번 열 값은: " + cell.getCellStyle().getFillForegroundColorColor().getRGBWithTint());
////                            }
////                        }
////                    }
////                }
////            }
//            for(int y=1;y<=32;y++){
//                for(int x=1;x<=256;x++) {
//                    MapParcelEntity mapParcelEntity = new MapParcelEntity();
//                    mapParcelEntity.setContinentId("Byron");
//                    mapParcelEntity.setVillageId("Charles");
//                    mapParcelEntity.setParcelX2d(x);
//                    mapParcelEntity.setParcelY2d(192+y);
//                    mapParcelEntity.setParcelX3d(x);
//                    mapParcelEntity.setParcelY3d(192+32+y);
//                    mapParcelEntity.setLandType(2);
//                    mapParcelRepository.save(mapParcelEntity);
//                }
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
//    }
}

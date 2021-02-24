import com.maigechuang.MonitorApplication;
import com.maigechuang.entity.NewsSource;
import com.maigechuang.mapper.caiji.NewsSourceMapper;
import com.maigechuang.service.NewsSourceService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mgc on 2020/12/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MonitorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class addNewSourceTest {

    private SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");

    private NewsSourceService newsSourceService;
    @Autowired
    NewsSourceMapper newsSourceMapper;
    @Test
    public void test() throws IOException {









    }
}

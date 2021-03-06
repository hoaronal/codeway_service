package com.codeway.base.service.backstage;

import com.codeway.model.QueryVO;
import com.codeway.model.dto.base.OptLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OptLogServiceTest {

    @Autowired
    private OptLogService optLogService;

    @Test
    public void findOptLogByCondition() {
	    QueryVO queryVO = new QueryVO();
	    Page<OptLogDto> dictByCondition = optLogService.findOptLogByCondition(new OptLogDto(), null);
        Assert.assertTrue(dictByCondition.getTotalElements() > 0);
    }

    @Test
    public void findOptLogByPrimaryKey() {
	    OptLogDto byId = optLogService.findById("1157967746300456962");
        Assert.assertNotNull(byId);
    }

    @Test
    public void insertOptLog() {
	    OptLogDto optLog = new OptLogDto();
        optLog.setId("1200629646627049472");
        optLog.setBrowser("maxxxxx");
        optLogService.insertOptLog(optLog);
    }

    @Test
    public void deleteBatch() {
        optLogService.deleteBatch(Arrays.asList("1157660486592466945", "1157672932308783105"));
    }
}

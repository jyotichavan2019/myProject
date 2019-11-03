package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.ProcessingFee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoTest {
    @Autowired
    ProcessingFeeDao processingFeeDao;

    private ProcessingFee processingFee;
    @Before
    public void setUp() throws Exception {

        }


    @Test
    public void getProcessingFeeByProductType(){
        ProcessingFee processingFee = new ProcessingFee();

        processingFee.setFee(BigDecimal.valueOf(14.99));
        processingFee.setProduct_type("Consoles");

        ProcessingFee processingFeeOut = processingFeeDao.getProcessingFeeByProductType("Consoles");

        assertEquals(processingFeeOut,processingFee );

    }

    }


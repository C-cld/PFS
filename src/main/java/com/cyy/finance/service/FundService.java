package com.cyy.finance.service;

import com.cyy.finance.dao.FundMapper;
import com.cyy.finance.domain.Fund;
import com.cyy.finance.domain.FundInvestmentRecord;
import com.cyy.finance.domain.FundNet;
import com.cyy.util.HttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FundService {

    @Autowired
    FundMapper fundMapper;

    public List<FundInvestmentRecord> searchInvestmentRecord(String fundCode) throws ParseException {

        // TODO: 2019/10/30 各种过滤条件
        List<FundInvestmentRecord> fundInvestmentRecordList = fundMapper.searchInvestmentRecord(fundCode);
        return fundInvestmentRecordList;
    }

    public List<Fund> searchFund() throws DocumentException {
        List<Fund> fundList = fundMapper.searchFund();
        int fundListSize = fundList.size();
        for (int i = 0; i < fundListSize; i ++) {
            Fund fund = fundList.get(i);
            addFundNet(fund);
        }
        return fundList;
    }

    public void insertFund(String fundCode) {
        Fund fund = new Fund();
        fund.setId("324234");
        fund.setName("234234234234");
        fund.setType(1);
        fundMapper.insertFund(fund);
    }

    /**
     * 给查出来的fund添加历史净值数据
     * @param fund
     * @return
     * @throws DocumentException
     */
    private Fund addFundNet(Fund fund) throws DocumentException {
        List<FundNet> fundNetList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -3);

        String startDate = simpleDateFormat.format(calendar.getTime());
        String endDate = simpleDateFormat.format(date);
        String url = "http://data.funds.hexun.com/outxml/detail/openfundnetvalue.aspx?fundcode=" + fund.getId() + "&startdate=" + startDate + "&enddate=" + endDate;
        String result = HttpUtil.sendGetRequest(url).replace("\r\n", "");
        SAXReader reader = new SAXReader();
        Document document = DocumentHelper.parseText(result);
        Element rootElement = document.getRootElement();
        String fundName = rootElement.element("fundname").getStringValue();
        Iterator iterator = rootElement.elementIterator("Data");
        while (iterator.hasNext()) {
            Element dataElement = (Element) iterator.next();
            Float net = Float.parseFloat(dataElement.element("fld_unitnetvalue").getStringValue());
            String ndate = dataElement.element("fld_enddate").getStringValue();
            FundNet fundNet = new FundNet();
            fundNet.ndate = ndate;
            fundNet.net = net;
            fundNetList.add(fundNet);
        }
        fund.setFundNet(fundNetList);
        return fund;
    }
}

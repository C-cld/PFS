package com.cyy.finance.service;

import com.cyy.finance.dao.FundMapper;
import com.cyy.finance.domain.Fund;
import com.cyy.finance.domain.Record;
import com.cyy.finance.domain.FundNet;
import com.cyy.util.HttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FundService {

    @Autowired
    FundMapper fundMapper;

    public List<Record> searchInvestmentRecord(String fundCode) throws ParseException {
        // TODO: 2019/10/30 各种过滤条件
        List<Record> recordList = fundMapper.searchInvestmentRecord(fundCode);
        return recordList;
    }

    public List<Fund> searchFund() {
        List<Fund> fundList = fundMapper.searchFund();
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
     * 从和讯接口查历史净值数据
     * @param fundCode
     * @return
     * @throws DocumentException
     */
    public List<FundNet> getFundNet(String fundCode) throws DocumentException {
        List<FundNet> fundNetList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -3);

        String startDate = simpleDateFormat.format(calendar.getTime());
        String endDate = simpleDateFormat.format(date);
        String url = "http://data.funds.hexun.com/outxml/detail/openfundnetvalue.aspx?fundcode=" + fundCode + "&startdate=" + startDate + "&enddate=" + endDate;
        String result = HttpUtil.sendGetRequest(url).replace("\r\n", "");
        Document document = DocumentHelper.parseText(result);
        Element rootElement = document.getRootElement();
        // String fundName = rootElement.element("fundname").getStringValue();
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
        return fundNetList;
    }
}
